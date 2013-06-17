import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 21.03.13
 * Time: 10:40
 */
public class LinkedBag extends AbstractCollection<Object> {
    private ArrayList<Node> nodesList = null;
    private int size = 0;
    private Node listLast = null;
    private Node listFirst = null;
    private int changesCount = 0;
    private Map<Object, List<Node>> indexBag = null;

    public LinkedBag() {
        nodesList = new ArrayList<Node>();
        indexBag = new HashMap<Object, List<Node>>();
    }

    @Override
    public Iterator<Object> iterator() {
        return new BagIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        return indexBag.containsKey(o);
    }

    @Override
    public boolean add(Object item) {
        Node currentNode = new Node(listLast, null, item, nodesList.size());
        if (!indexBag.containsKey(item)) {
            indexBag.put(item, new ArrayList<Node>());
        }
        indexBag.get(item).add(currentNode);
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
        if (!indexBag.containsKey(o) || indexBag.get(o).size() == 0) {
            return false;
        }
        Node node = indexBag.get(o).get(0);
        if (node == null) {
            return false;
        }
        Node last = nodesList.get(nodesList.size() - 1);
        last.indexInList = node.indexInList;
        nodesList.set(node.indexInList, last);
        nodesList.remove(nodesList.size() - 1);
        size--;
        changesCount++;
        return true;
    }

    @Override
    public boolean addAll(Collection<?> objects) {
        boolean ans;
        if (objects == this) {
            ans = this.addAll(Arrays.asList(objects.toArray()));
        } else {
            ans = super.addAll(objects);
        }
        changesCount++;
        return ans;
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        boolean ans = true;
        if (objects == this) {
            nodesList.clear();
            size = 0;
        } else {
            ans = super.removeAll(objects);
        }
        changesCount++;
        return ans;
    }

    private class Node implements Comparable<Node>{
        private Node left = null;
        private Node right = null;
        private Object object = null;
        private int indexInList = -1;

        public Node(Node left, Node right, Object object, int indexInList) {
            this.left = left;
            this.right = right;
            this.object = object;
            this.indexInList = indexInList;
        }

        @Override
        public int compareTo(Node node) {
            return this.object.hashCode() - node.object.hashCode();
        }
    }

    private class BagIterator implements Iterator<Object> {
        private int expectedCount = 0;
        private Node currentNode = null;


        public BagIterator() {
            expectedCount = changesCount;
            currentNode = listFirst;
        }

        @Override
        public boolean hasNext() {
            return nodesList.size() != 0 && currentNode != null;
        }

        @Override
        public Object next() {
            checkForModificationCountErrors();
            if (!hasNext()) {
                throw new NoSuchElementException("don't have next element");
            }
            Object ans = nodesList.get(currentNode.indexInList).object;
            currentNode = currentNode.right;
            return ans;
        }

        @Override
        public void remove() {
            checkForModificationCountErrors();
            Node removeNode;
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

            Node last = nodesList.get(nodesList.size() - 1);
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
