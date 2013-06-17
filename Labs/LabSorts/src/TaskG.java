import java.io.*;
import java.util.*;

public class TaskG {
	class Comp {
		int x, y;

		Comp(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt(), k = nextInt();
		Comp[] data = new Comp[m];
		int it = 0;
		for (int i = 0; i < k; i++) {
			int r = nextInt();
			for (int j = 0; j < r; j++) {
				int x = nextInt() - 1, y = nextInt() - 1;
				data[it++] = new Comp(Math.min(x, y), Math.max(x, y));
			}
		}
		for (int i = 0; i < (1 << n); i++) {
			int[] num = new int[n];
			for (int j = 0; j < n; j++) {
				if ((i | (1 << j)) == i) {
					num[j] = 1;
				} else {
					num[j] = 0;
				}
			}
			for (int j = 0; j < m; j++) {
				if (num[data[j].x] > num[data[j].y]) {
					num[data[j].x] = 0;
					num[data[j].y] = 1;
				}
			}
			for (int j = 0; j < n - 1; j++) {
				if (num[j] > num[j + 1]) {
					out.println("No");
					return;
				}
			}
		}
		out.println("Yes");
	}

	void run() throws IOException {
		// br = new BufferedReader(new FileReader("netcheck.in"));
		// out = new PrintWriter("netcheck.out");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
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
		new TaskG().run();
	}
}