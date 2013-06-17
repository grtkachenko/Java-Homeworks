import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 20.03.13
 * Time: 21:53
 */
public class Main {
    private static final int MAX_VALUE = 31;
    public static void main(String[] args) {
        // bag
        Bag<Integer > bag = new Bag<Integer>();

        for (int i = 0; i < MAX_VALUE; i++) {
            bag.add(i);
        }
        Iterator<Integer> iterator = bag.iterator();

        while (iterator.hasNext()) {
            Integer cur = iterator.next();
            System.out.println(cur);
        }
        // linked bag
        LinkedBag<Integer > linkedBag = new LinkedBag<Integer>();

        for (int i = 0; i < MAX_VALUE; i++) {
            linkedBag.add(i);
        }
        iterator = linkedBag.iterator();

        while (iterator.hasNext()) {
            Integer cur = iterator.next();
            System.out.println(cur);
        }
    }




}
