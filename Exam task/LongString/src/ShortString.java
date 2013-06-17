public class ShortString extends LongString {
	final int p = 31;
	private String data;
	private long hashDeg = 1;

	public void add(LongString val) {

	}

	ShortString() {

	}

	ShortString(String data) {
		this.data = data;
		for (int i = 0; i < data.length(); i++) {
			hashDeg *= p;
		}
	}

	public int getLength() {
		return data.length();
	}

	public char charAt(int i) {
		return data.charAt(i);
	}

	public long getHash() {
		long hash = 0;
		for (int i = 0; i < data.length(); i++) {
			hash = hash * p + (int) data.charAt(i);
		}
		return hash;
	}

	public LongString substring(int a, int b) {
		return new ShortString(data.substring(a, b));
	}

	public String toString() {
		String res = new String(data);
		return res;
	}

	public long getHashDegree() {
		return hashDeg;
	}

}
