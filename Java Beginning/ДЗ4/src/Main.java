public class Main {
	public static void main(String[] args) {
		int x = Integer.parseInt(args[0]);
		// x ^ 2 - 2 * x + 1
		int y = new Plus(
					new Minus(
							new Times(
									new Variable("x"),
									new Variable("x")
							),
							new Times(
									new Const(2),
									new Variable("x")
							)
					),
					new Const(1)
				).evaluate(x);
		System.out.println(y);
	}
}
