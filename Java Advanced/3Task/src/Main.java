import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 20.03.13
 * Time: 21:53
 */
public class Main {
    public static void main(String[] args) {
        Bag bag = new Bag();
        for (int i = 0; i < 1000000; i++) {
            bag.add(0);
        }
        for (int i = 0; i < 1000000; i++) {
            bag.remove(0);
        }
        LinkedBag linkedBag = new LinkedBag();

        bag.add("d");
        bag.add("d");

        linkedBag.add("d");
        bag.removeAll(linkedBag);





        System.out.println(bag.size());

        Iterator iterator = bag.iterator();

        while (iterator.hasNext()) {
            Object cur = iterator.next();
            System.out.println(cur);
        }
//        iterator.remove();
//        System.out.println(bag.size());
//        iterator = bag.iterator();
//        while (iterator.hasNext()) {
//            Object cur = iterator.next();
//            System.out.println(cur);
//        }
    }
}
