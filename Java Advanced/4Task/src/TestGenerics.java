import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 04.05.13
 * Time: 2:41
 */
public class TestGenerics {
    public static void test() {
        List<String> list = new ArrayList<String>() {{
            add("A");
            add("B");
            add("C");
        }};
        List<String> list1 = new
                ArrayList<String>() {{
                    add("D");
                    add("E");
                    add("F");
                }};

        String[] item = {"1", "2", "3"};
        add(item, list);
        addCol(list, list1);
//        almostSwap(list, 0, 1);
        print(list);
//        System.out.println(max(list));
    }

    private static void print(Collection<?> c) {
        for (Object item : c) {
            System.out.println(item);
        }
    }

    private static <T> void add(T[] a, Collection<T> c) {
        for (T obj : a) {
            c.add(obj);
        }
    }

    private static <T> void addCol(Collection<T> c1, Collection<? extends T> c2) {
        for (T obj : c2) {
            c1.add(obj);
        }
    }

    private static <T extends Comparable<? super T>> T max(Collection<T> c) {
        T res = c.iterator().next();
        for (Iterator<T> i = c.iterator(); i.hasNext(); ) {
            T cur = i.next();
            if (cur.compareTo(res) > 0) {
                res = cur;
            }
        }
        return res;
    }

    private static <T> void almostSwap(List<T> c, int i, int j) {
        c.set(i, c.get(j));
    }
}
