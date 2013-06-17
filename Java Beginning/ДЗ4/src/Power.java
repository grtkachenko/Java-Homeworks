public class Power extends BinaryOperation {
	public Power(Expression a, Expression b) {
		super(a, b);
	}

	public int evaluate(int val) {
		return (int) Math.pow(a.evaluate(val), b.evaluate(val));
	}

}