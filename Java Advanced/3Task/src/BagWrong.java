import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 20.03.13
 * Time: 21:53
 */
public class BagWrong extends AbstractCollection<Object> {

    private Map<Object, List<Object>> hashMap = null;
    private int size = 0;
    private int changesCount = 0;

    public BagWrong() {
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
        boolean ans = super.remove(o);
        changesCount++;
        return ans;
    }

    @Override
    public boolean addAll(Collection<?> objects) {
        boolean ans = super.removeAll(objects);
        changesCount++;
        return ans;
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        boolean ans = super.removeAll(objects);
        changesCount++;
        return ans;
    }

    private class BagIterator implements Iterator<Object> {
        private int expectedCount = 0;
        private int currentPositionInList = 0;
        private Map.Entry<Object, List<Object>> currentEntry = null;
        private Map.Entry<Object, List<Object>> prevEntry = null;
        private Iterator<Map.Entry<Object, List<Object>>> mapIterator = null;

        public BagIterator() {
            expectedCount = changesCount;
            mapIterator = hashMap.entrySet().iterator();
            currentEntry = getNextEntry();
        }

        @Override
        public boolean hasNext() {
            if (mapIterator.hasNext()) {
                return true;
            }
            if (currentEntry == null) {
                return false;
            }
            return (currentEntry.getValue().size() > currentPositionInList);
        }

        @Override
        public Object next() {
            checkForModificationCountErrors();
            if (!hasNext()) {
                throw new NoSuchElementException("don't have next element");
            }
            Object ans = currentEntry.getValue().get(currentPositionInList++);
            if (currentEntry.getValue().size() <= currentPositionInList) {
                prevEntry = currentEntry;
                currentEntry = getNextEntry();
                currentPositionInList = 0;
            }
            return ans;
        }

        @Override
        public void remove() {
            checkForModificationCountErrors();
            if (currentPositionInList == 0 && prevEntry == null) {
                throw new IllegalStateException("remove null element");
            }
            if (currentPositionInList == 0) {
                prevEntry.getValue().remove(prevEntry.getValue().size() - 1);
                size--;
                return;
            }
            size--;
            currentEntry.getValue().remove(currentPositionInList--);
        }

        private void checkForModificationCountErrors() {
            if (expectedCount != changesCount) {
                throw new ConcurrentModificationException("iterate by modificated bag");
            }
        }

        private Map.Entry<Object, List<Object>> getNextEntry() {
            while (true) {
                if (!mapIterator.hasNext()) {
                    return null;
                }
                Map.Entry<Object, List<Object>> res = mapIterator.next();
                if (res.getValue().size() == 0) {
                    mapIterator.remove();
                } else {
                    return res;
                }
            }
        }
    }
}
