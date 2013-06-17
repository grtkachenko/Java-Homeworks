import java.util.*;
import java.io.*;

public class TaskE {

	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

	String nextToken() throws IOException {
		String s = null;
		while (stok == null || !stok.hasMoreTokens()) {
			s = br.readLine();
			if (s == null) {
				return "-1";
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

	void solve() throws IOException {
		int n = nextInt(), k = nextInt();
		int total = (int) Math.pow(k, n);
		int[][] ans = new int[total][n];
		int cur = (int) Math.pow(k, n - 1);
		for (int i = n - 1; i >= 0; i--) {
			for (int num = 0; num < (total / k / cur); num++) {
				for (int j = 0; j < k; j++) {
					for (int j1 = 0; j1 < cur; j1++) {
						if (num % 2 == 0)
							ans[num * cur * k + j1 + j * cur][i] = j;
						else
							ans[num * cur * k + j1 + j * cur][i] = k - j - 1;
					}
				}
			}
			cur /= k;
		}
		for (int i = 0; i < (total); i++) {
			for (int j = 0; j < n; j++)
				out.print(ans[i][j]);
			out.println();
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("telemetry.in"));
		out = new PrintWriter("telemetry.out");
		solve();
		br.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		// Locale.setDefault(Locale.US);
		new TaskE().run();
	}
}
