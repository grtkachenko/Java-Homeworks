public class Not implements Expression {
	private Expression exp;

	public Not(Expression exp) {
		this.exp = exp;
	}

	public int evaluate(int a) {
		return ~exp.evaluate(a);
	}
}
