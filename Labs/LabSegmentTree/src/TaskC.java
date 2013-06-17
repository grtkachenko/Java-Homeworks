import java.io.*;
import java.util.*;

public class TaskC {
	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;
	int r;

	class SegmentTree {
		int[][][] tree;
		int size;

		public SegmentTree(int size, int[][][] data) {
			tree = new int[2 * size - 1][2][2];
			this.size = size;

			for (int i = size - 1; i < 2 * size - 1; i++) {
				tree[i] = data[i - size + 1];
			}
			for (int i = size - 2; i >= 0; i--) {
				tree[i] = mulMatrix(tree[2 * i + 1], tree[2 * i + 2]);
			}
		}

		private int[][] getMul(int num, int l, int r, int a, int b) {
			if (r < a || l > b) {
				return null;
			}
			if (a <= l && r <= b) {
				return tree[num];
			}
			return mulMatrix(getMul(num * 2 + 1, l, (l + r) / 2, a, b),
					getMul(num * 2 + 2, (l + r) / 2 + 1, r, a, b));
		}

		int[][] mul(int l, int r) {
			return getMul(0, size - 1, 2 * size - 2, l + size - 2, r + size - 2);
		}
	}

	int[][] mulMatrix(int[][] a, int[][] b) {
		if (a == null && b == null) {
			return null;
		}
		int[][] data = new int[2][2];
		if (a == null) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					data[i][j] = b[i][j];
				}
			}
			return data;
		}
		if (b == null) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					data[i][j] = a[i][j];
				}
			}
			return data;
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				data[i][j] = (a[i][0] * b[0][j] + a[i][1] * b[1][j]) % r;
			}
		}
		return data;
	}

	void solve() throws IOException {

		r = nextInt();
		int n = nextInt(), m = nextInt(), deg = 1;
		while (deg < n) {
			deg *= 2;
		}
		int[][] tmp = new int[2][2];
		int[][][] a = new int[deg][2][2];
		for (int i = 0; i < deg; i++) {
			if (i >= n) {
				a[i] = null;
				continue;
			}
			for (int ii = 0; ii < 2; ii++) {
				for (int jj = 0; jj < 2; jj++) {
					a[i][ii][jj] = nextInt() % r;
				}
			}
		}
		SegmentTree tree = new SegmentTree(deg, a);

		for (int ii = 0; ii < m; ii++) {
			int l = nextInt(), r = nextInt();
			int[][] ans = tree.mul(l, r);
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					out.print(ans[i][j] + " ");
				}
				out.println();
			}
			out.println();
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
		br = new BufferedReader(new FileReader("crypto.in"));
		out = new PrintWriter("crypto.out");
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
		new TaskC().run();
	}
}