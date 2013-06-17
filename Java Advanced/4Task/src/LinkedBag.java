import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 21.03.13
 * Time: 10:40
 */
public class LinkedBag<T> extends AbstractCollection<T> {
    private List<Node<T>> nodesList;
    private int size = 0;
    private Node<T> listLast;
    private Node<T> listFirst;
    private int changesCount = 0;
    private Map<T, List<Node>> indexMap;

    public LinkedBag() {
        nodesList = new ArrayList<Node<T>>();
        indexMap = new HashMap<T, List<Node>>();
    }

    @Override
    public Iterator<T> iterator() {
        return new BagIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        return indexMap.containsKey(o);
    }

    @Override
    public boolean add(T item) {
        Node<T> currentNode = new Node<T>(listLast, null, item, nodesList.size());
        if (!indexMap.containsKey(item)) {
            indexMap.put(item, new ArrayList<Node>());
        }
        indexMap.get(item).add(currentNode);
        nodesList.add(currentNode);
        if (listLast != null) {
            listLast.right = currentNode;
        }
        listLast = currentNode;
        if (listFirst == null) {
            listFirst = currentNode;
        }
        size++;
        changesCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!indexMap.containsKey(o) || indexMap.get(o).size() == 0) {
            return false;
        }
        Node node = indexMap.get(o).get(0);
        if (node == null) {
            return false;
        }
        Node<T> last = nodesList.get(nodesList.size() - 1);
        last.indexInList = node.indexInList;
        nodesList.set(node.indexInList, last);
        nodesList.remove(nodesList.size() - 1);
        size--;
        changesCount++;
        return true;
    }

    private class Node<S> {
        private Node<S> left = null;
        private Node<S> right = null;
        private S object = null;
        private int indexInList = -1;

        public Node(Node<S> left, Node<S> right, S object, int indexInList) {
            this.left = left;
            this.right = right;
            this.object = object;
            this.indexInList = indexInList;
        }
    }

    private class BagIterator implements Iterator<T> {
        private int expectedCount = 0;
        private Node<T> currentNode = null;

        public BagIterator() {
            expectedCount = changesCount;
            currentNode = listFirst;
        }

        @Override
        public boolean hasNext() {
            return nodesList.size() != 0 && currentNode != null;
        }

        @Override
        public T next() {
            checkForModificationCountErrors();
            if (!hasNext()) {
                throw new NoSuchElementException("don't have next element");
            }
            T ans = nodesList.get(currentNode.indexInList).object;
            currentNode = currentNode.right;
            return ans;
        }

        @Override
        public void remove() {
            checkForModificationCountErrors();
            Node<T> removeNode;
            if (currentNode == null) {
                if (listLast != null) {
                    removeNode = listLast;
                } else {
                    throw new IllegalStateException("remove null element");
                }
            } else {
                removeNode = currentNode.left;
            }

            if (removeNode == null) {
                throw new IllegalStateException("remove null element");
            }
            if (removeNode.left != null) {
                removeNode.left.right = removeNode.right;
            }
            if (removeNode.right != null) {
                removeNode.right.left = removeNode.left;
            }
            if (removeNode == listLast) {
                listLast = removeNode.left;
            }

            if (removeNode == listFirst) {
                listFirst = removeNode.right;
            }

            Node<T> last = nodesList.get(nodesList.size() - 1);
            last.indexInList = removeNode.indexInList;
            nodesList.set(removeNode.indexInList, last);
            nodesList.remove(nodesList.size() - 1);
            size--;
        }

        private void checkForModificationCountErrors() {
            if (expectedCount != changesCount) {
                throw new ConcurrentModificationException("iterate by modificated bag");
            }
        }

    }
}
