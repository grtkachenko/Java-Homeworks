import java.util.ArrayList;

public class ConcatenationString extends LongString {

	private ArrayList<LongString> data = new ArrayList<LongString>();
	private long hashDeg = 1;
	private ArrayList<Integer> sum = new ArrayList<Integer>();

	public ConcatenationString() {

	}

	public void add(LongString val) {
		data.add(val);
		if (sum.size() == 0) {
			sum.add(val.getLength());
		} else {
			sum.add(sum.get(sum.size() - 1) + val.getLength());
		}
		for (int i = 0; i < val.getLength(); i++) {
			hashDeg *= p;
		}
	}

	public int getLength() {
		int totalSize = 0;
		for (int i = 0; i < data.size(); i++) {
			totalSize += data.get(i).getLength();
		}
		return totalSize;
	}

	public char charAt(int num) {
		if (num >= getLength() || num < 0) {
			throw new IndexOutOfBoundsException("String index out of range: "
					+ num);
		}
		int left = -1, right = data.size();
		while (right - left > 1) {
			int mid = (left + right) / 2;
			if (sum.get(mid) <= num) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return data.get(right).charAt(num - (right == 0 ? 0 : sum.get(right - 1)));
	}

	public long getHash() {
		long hash = 0;
		for (int i = 0; i < data.size(); i++) {
			hash = hash * data.get(i).getHashDegree() + data.get(i).getHash();
		}
		return hash;
	}

	public LongString substring(int a, int b) {
		if (!(a >= 0 && b <= getLength()) || (a > b)) {
			throw new IndexOutOfBoundsException(
					"Substring indexes is incorrect");
		}
		ConcatenationString ans = new ConcatenationString();
		int left = a, right = b;
		for (int i = 0; i < data.size(); i++) {
			if (right <= 0) {
				break;
			}
			if (left < 0) {
				left = 0;
			}
			if (left < 0 || right <= 0) {
				break;
			}
			if (data.get(i).getLength() > left) {
				ans.add(data.get(i).substring(left,
						Math.min(data.get(i).getLength(), right)));
			}

			left -= data.get(i).getLength();
			right -= data.get(i).getLength();
		}
		return ans;
	}

	public String toString() {
		String res = "";
		for (int i = 0; i < data.size(); i++) {
			res += data.get(i).toString();
		}
		return res;
	}

	public long getHashDegree() {
		return hashDeg;
	}

}
