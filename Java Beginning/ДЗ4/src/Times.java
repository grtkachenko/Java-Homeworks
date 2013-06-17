public class Times extends BinaryOperation {
	public Times(Expression a, Expression b) {
		super(a, b);
	}

	public int evaluate(int val) {
		return a.evaluate(val) * b.evaluate(val);
	}

}
