import java.util.Random;

public class Test {
	static void printAll(Object... args) {
		for (Object tmp : args) {
			System.out.println(tmp);
		}
	}
	public static void main(String[] args) {
		printAll(new String("abac"), new Integer(2), new Double(2));
		
		//System.out.println("Everything is OK :)");
	}
}
