import java.io.*;
import java.util.*;

public class TaskC {
	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		int[][] d = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(d[i], Integer.MAX_VALUE / 2 - 3131);
		}
		for (int i = 0; i < m; i++) {
			d[nextInt() - 1][nextInt() - 1] = nextInt();
		}
		for (int i = 0; i < n; i++) {
			d[i][i] = 0;
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (d[i][j] > d[i][k] + d[k][j]) {
						d[i][j] = d[i][k] + d[k][j];
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				out.print(d[i][j] + " ");
			}
			out.println();
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("pathsg.in"));
		out = new PrintWriter("pathsg.out");
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
		new TaskC().run();
	}
}