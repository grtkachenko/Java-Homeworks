public class Minus extends BinaryOperation {
	public Minus(Expression a, Expression b) {
		super(a, b);
	}

	public int evaluate(int val) {
		return a.evaluate(val) - b.evaluate(val);
	}

}
