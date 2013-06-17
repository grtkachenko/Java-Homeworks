public abstract class BinaryOperation implements Expression {
	protected Expression a, b;

	protected BinaryOperation(Expression a, Expression b) {
		this.a = a;
		this.b = b;
	}

}
