import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 20.03.13
 * Time: 21:53
 */
public class Bag<T> extends AbstractCollection<T> {
    private Map<T, List<T>> bagMap;
    private int size = 0;
    private int changesCount = 0;

    public Bag() {
        bagMap = new HashMap<T, List<T>>();
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
        return bagMap.containsKey(o);
    }

    @Override
    public boolean add(T item) {
        if (!bagMap.containsKey(item)) {
            bagMap.put(item, new ArrayList<T>());
        }
        List<T> itemList = bagMap.get(item);
        itemList.add(item);
        size++;
        changesCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!bagMap.containsKey(o)) {
            return false;
        }
        List<T> itemList = bagMap.get(o);
        if (itemList.size() == 1) {
            bagMap.remove(o);
        } else {
            itemList.remove(itemList.size() - 1);
        }
        changesCount++;
        size--;
        return true;
    }

    private class BagIterator implements Iterator<T> {
        private int expectedCount = 0;
        private int listSize = 0;
        private ListIterator<T> listIterator = null;
        private Iterator<Map.Entry<T, List<T>>> mapIterator = null;

        public BagIterator() {
            expectedCount = changesCount;
            mapIterator = bagMap.entrySet().iterator();
            listIterator = getNextIterator();
        }

        @Override
        public boolean hasNext() {
            return mapIterator.hasNext() || listIterator != null && listIterator.hasNext();
        }

        @Override
        public T next() {
            checkForModificationCountErrors();
            if (!hasNext()) {
                throw new NoSuchElementException("don't have next element");
            }
            if (!listIterator.hasNext()) {
                listIterator = getNextIterator();
            }
            return listIterator.next();
        }

        @Override
        public void remove() {
            checkForModificationCountErrors();
            size--;
            if (listSize == 1) {
                listIterator.remove();
                mapIterator.remove();
                listSize--;
                return;
            }
            listSize--;
            listIterator.remove();

        }

        private void checkForModificationCountErrors() {
            if (expectedCount != changesCount) {
                throw new ConcurrentModificationException("iterate by modificated bag");
            }
        }

        private ListIterator<T> getNextIterator() {
            while (true) {
                Map.Entry<T, List<T>> entry = mapIterator.next();
                if (entry.getValue().size() == 0) {
                    mapIterator.remove();
                } else {
                    listSize = entry.getValue().size();
                    return entry.getValue().listIterator();
                }
            }
        }
    }
}
