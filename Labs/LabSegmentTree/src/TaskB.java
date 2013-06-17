import java.io.*;
import java.util.*;

public class TaskB {
	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

	class SegmentTree {
		long[] tree;
		int size;

		public SegmentTree(int size, long[] data) {
			tree = new long[2 * size - 1];
			this.size = size;

			for (int i = size - 1; i < 2 * size - 1; i++) {
				tree[i] = data[i - size + 1];
			}
			for (int i = size - 2; i >= 0; i--) {
				tree[i] = tree[2 * i + 1] + tree[2 * i + 2];
			}
		}

		void set(int num, long val) {
			int cur = num + size - 2;
			tree[cur] = val;
			while (cur != 0) {
				cur = (cur - 1) / 2;
				tree[cur] = tree[2 * cur + 1] + tree[2 * cur + 2];
			}
		}

		private long getsum(int num, int l, int r, int a, int b) {
			if (r < a || l > b) {
				return 0;
			}
			if (a <= l && r <= b) {
				return tree[num];
			}
			return getsum(num * 2 + 1, l, (l + r) / 2, a, b)
					+ getsum(num * 2 + 2, (l + r) / 2 + 1, r, a, b);
		}

		long sum(int l, long r) {
			return getsum(0, size - 1, 2 * size - 2, l + size - 2, (int) r
					+ size - 2);
		}
	}

	void solve() throws IOException {
		int n = nextInt(), deg = 1;
		while (deg < n) {
			deg *= 2;
		}
		long[] a = new long[deg];
		for (int i = 0; i < deg; i++) {
			a[i] = i < n ? nextLong() : 0;
		}
		SegmentTree tree = new SegmentTree(deg, a);
		while (true) {
			String s = nextToken();
			if (s == null) {
				break;
			}
			int x = nextInt();
			long y = nextLong();
			if (s.equals("sum")) {
				out.println(tree.sum(x, y));
				continue;
			}
			tree.set(x, y);
		}
	}

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

	void run() throws IOException {
		br = new BufferedReader(new FileReader("rsq.in"));
		out = new PrintWriter("rsq.out");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		// br = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		solve();
		br.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		// Locale.setDefault(Locale.US);
		new TaskB().run();
	}
}