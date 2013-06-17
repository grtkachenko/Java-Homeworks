import java.io.*;
import java.util.*;

public class TaskD {

	class SegmentTree {
		long[] tree, d, assign;
		int size;


		public SegmentTree(int size, long[] data) {
			tree = new long[2 * size - 1];
			d = new long[2 * size - 1];
			assign = new long[2 * size - 1];
			this.size = size;

			for (int i = size - 1; i < 2 * size - 1; i++) {
				tree[i] = data[i - size + 1];
				assign[i] = Long.MAX_VALUE;
			}
			for (int i = size - 2; i >= 0; i--) {
				tree[i] = Math.min(tree[2 * i + 1], tree[2 * i + 2]);
				assign[i] = Long.MAX_VALUE;
			}
		}

		void push(int num) {
			if (assign[num] != Long.MAX_VALUE) {
				assign[2 * num + 1] = assign[2 * num + 2] = assign[num];
				d[2 * num + 1] = d[2 * num + 2] = 0;
				tree[2 * num + 1] = tree[2 * num + 2] = tree[num] + d[num];
				d[num] = 0;
				assign[num] = Long.MAX_VALUE;
			} else {
				d[2 * num + 1] += d[num];
				d[2 * num + 2] += d[num];
				d[num] = 0;
			}
		}

		private void setUpd(int num, int l, int r, int a, int b, long val) {
			if (r < a || l > b) {
				return;
			}
			if (a <= l && r <= b) {
				assign[num] = val;
				tree[num] = val;
				d[num] = 0;
				return;
			}
			push(num);
			setUpd(num * 2 + 1, l, (l + r) / 2, a, b, val);
			setUpd(num * 2 + 2, (l + r) / 2 + 1, r, a, b, val);

			tree[num] = Math.min(tree[2 * num + 1] + d[2 * num + 1],
					tree[2 * num + 2] + d[2 * num + 2]);
		}

		void set(int l, int r, long val) {
			setUpd(0, size - 1, 2 * size - 2, l + size - 2, r + size - 2, val);
		}

		private void addUpd(int num, int l, int r, int a, int b, long val) {
			if (r < a || l > b) {
				return;
			}
			if (a <= l && r <= b) {
				d[num] += val;
				return;
			}
			push(num);
			addUpd(num * 2 + 1, l, (l + r) / 2, a, b, val);
			addUpd(num * 2 + 2, (l + r) / 2 + 1, r, a, b, val);
			tree[num] = Math.min(tree[2 * num + 1] + d[2 * num + 1],
					tree[2 * num + 2] + d[2 * num + 2]);
		}

		void add(int l, int r, long val) {
			addUpd(0, size - 1, 2 * size - 2, l + size - 2, r + size - 2, val);
		}

		private long getMin(int num, int l, int r, int a, int b) {
			if (r < a || l > b) {
				return Long.MAX_VALUE;
			}
			if (a <= l && r <= b) {
				return tree[num] + d[num];
			}
			push(num);
			long ans = Math.min(getMin(num * 2 + 1, l, (l + r) / 2, a, b),
					getMin(num * 2 + 2, (l + r) / 2 + 1, r, a, b));
			tree[num] = ans;
			tree[num] = Math.min(tree[2 * num + 1] + d[2 * num + 1],
					tree[2 * num + 2] + d[2 * num + 2]);
			return ans;
		}

		long min(int l, int r) {
			return getMin(0, size - 1, 2 * size - 2, l + size - 2, r + size - 2);
		}
	}

	void solve() throws IOException {
		int n = nextInt(), deg = 1;
		while (deg < n) {
			deg *= 2;
		}
		long[] a = new long[deg];
		for (int i = 0; i < deg; i++) {
			a[i] = i < n ? nextLong() : Long.MAX_VALUE;
		}
		SegmentTree tree = new SegmentTree(deg, a);
		while (true) {
			String s = nextToken();
			if (s == null) {
				break;
			}
			int x = nextInt(), y = nextInt();
			if (s.equals("min")) {
				out.println(tree.min(x, y));
				continue;
			}
			if (s.equals("add")) {
				tree.add(x, y, nextLong());
				continue;
			}

			tree.set(x, y, nextLong());
		}

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("rmq2.in"));
		out = new PrintWriter("rmq2.out");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		// br = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		solve();
		br.close();
		out.close();
	}

	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

	String nextToken() throws IOException {
		while (stok == null || !stok.hasMoreTokens()) {
			String s = br.readLine();
			if (s == null) {
				return null;
			}
			stok = new StringTokenizer(s);
		}
		return stok.nextToken();
	}

	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}

	char nextChar() throws IOException {
		return (char) (br.read());
	}

	String nextLine() throws IOException {
		return br.readLine();
	}

	public static void main(String[] args) throws IOException {
		// Locale.setDefault(Locale.US);
		new TaskD().run();
	}
}