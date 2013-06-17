import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 20.03.13
 * Time: 21:53
 */
public class Bag extends AbstractCollection<Object> {

    private Map<Object, List<Object>> hashMap = null;
    private int size = 0;
    private int changesCount = 0;

    public Bag() {
        hashMap = new HashMap<Object, List<Object>>();
    }

    @Override
    public Iterator<Object> iterator() {
        return new BagIterator();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(Object item) {
        if (!hashMap.containsKey(item)) {
            hashMap.put(item, new ArrayList<Object>());
        }
        List<Object> itemList = hashMap.get(item);
        itemList.add(item);
        size++;
        changesCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!hashMap.containsKey(o)) {
            return false;
        }
        List<Object> itemList = hashMap.get(o);
        if (itemList.size() == 1) {
            hashMap.remove(o);
        } else {
            itemList.remove(0);
        }
        changesCount++;
        size--;
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
            hashMap.clear();
            size = 0;
        } else {
            ans = super.removeAll(objects);
        }
        changesCount++;
        return ans;
    }

    private class BagIterator implements Iterator<Object> {
        private int expectedCount = 0;
        private int listSize = 0;
        private ListIterator<Object> listIterator = null;
        private Iterator<Map.Entry<Object, List<Object>>> mapIterator = null;

        public BagIterator() {
            expectedCount = changesCount;
            mapIterator = hashMap.entrySet().iterator();
            listIterator = getNextIterator();
        }

        @Override
        public boolean hasNext() {
            if (mapIterator.hasNext()) {
                return true;
            }
            if (listIterator == null) {
                return false;
            }
            return listIterator.hasNext();
        }

        @Override
        public Object next() {
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

        private ListIterator<Object> getNextIterator() {
            while (true) {
                if (!mapIterator.hasNext()) {
                    return null;
                }
                Map.Entry<Object, List<Object>> entry = mapIterator.next();
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
