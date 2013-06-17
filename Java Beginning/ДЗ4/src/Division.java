public class Division extends BinaryOperation {
	public Division(Expression a, Expression b) {
		super(a, b);
	}

	public int evaluate(int val) {
		return a.evaluate(val) * b.evaluate(val);
	}

}
