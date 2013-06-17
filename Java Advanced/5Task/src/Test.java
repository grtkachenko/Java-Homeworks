/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 16.05.13
 * Time: 1:15
 */
public class Test {
    public static void main(String[] args) {
        Object o = new Object();
        o.getClass();
        try {
            Class cl = Class.forName("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
