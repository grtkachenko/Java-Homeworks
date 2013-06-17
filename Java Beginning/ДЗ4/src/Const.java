public class Const implements Expression {
	private int val;

	Const(int val) {
		this.val = val;
	}

	public int evaluate(int a) {
		return val;
	}
}
