import java.io.*;
import java.util.*;

public class TaskC1 {
	int r;

	class SegmentTree {
		Matrix[] tree;
		int size;

		public SegmentTree(int size, Matrix[] data) {
			tree = new Matrix[2 * size - 1];
			this.size = size;

			for (int i = size - 1; i < 2 * size - 1; i++) {
				tree[i] = data[i - size + 1];
			}
			for (int i = size - 2; i >= 0; i--) {
				tree[i] = mulMatrix(tree[2 * i + 1], tree[2 * i + 2]);
			}
		}

		private Matrix getMul(int num, int l, int r, int a, int b) {
			if (r < a || l > b) {
				return null;
			}
			if (a <= l && r <= b) {
				return tree[num];
			}
			return mulMatrix(getMul(num * 2 + 1, l, (l + r) / 2, a, b),
					getMul(num * 2 + 2, (l + r) / 2 + 1, r, a, b));
		}

		Matrix mul(int l, int r) {
			return getMul(0, size - 1, 2 * size - 2, l + size - 2, r + size - 2);
		}
	}

	Matrix mulMatrix(Matrix a, Matrix b) {
		if (a == null && b == null) {
			return null;
		}
		if (a == null) {
			return new Matrix(b.a, b.b, b.c, b.d);
		}
		if (b == null) {
			return new Matrix(a.a, a.b, a.c, a.d);
		}

		int tempA, tempB, tempC, tempD;
		tempA = (a.a * b.a + a.b * b.c) % r;
		tempB = (a.a * b.b + a.b * b.d) % r;
		tempC = (a.c * b.a + a.d * b.c) % r;
		tempD = (a.c * b.b + a.d * b.d) % r;

		return new Matrix(tempA, tempB, tempC, tempD);
	}

	class Matrix {
		int a, b, c, d;

		public Matrix(int[][] data) {
			a = data[0][0];
			b = data[0][1];
			c = data[1][0];
			d = data[1][1];
		}

		public Matrix(int a, int b, int c, int d) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}

		void println() {
			out.println(a + " " + b);
			out.println(c + " " + d);
			out.println();
		}

	}

	void solve() throws IOException {

		r = nextInt();
		int n = nextInt(), m = nextInt(), deg = 1;
		while (deg < n) {
			deg *= 2;
		}
		int[][] tmp = new int[2][2];
		Matrix[] a = new Matrix[deg];
		for (int i = 0; i < deg; i++) {
			if (i >= n) {
				a[i] = null;
				continue;
			}
			for (int ii = 0; ii < 2; ii++) {
				for (int jj = 0; jj < 2; jj++) {
					tmp[ii][jj] = nextInt() % r;
				}
			}
			a[i] = new Matrix(tmp);
		}
		SegmentTree tree = new SegmentTree(deg, a);

		for (int i = 0; i < m; i++) {
			int l = nextInt(), r = nextInt();
			Matrix ans = tree.mul(l, r);
			ans.println();
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

	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

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
		new TaskC1().run();
	}
}