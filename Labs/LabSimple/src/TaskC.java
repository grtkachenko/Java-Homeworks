import java.util.*;
import java.io.*;

public class TaskC {
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

	void solve() throws IOException {
		int n = nextInt();

		int total = (int) Math.pow(3, n);
		int[][] ans = new int[total][n];
		for (int i = 0; i < total; i++) {
			ans[i][0] = (i + 1) % 3;
		}
		for (int i = 1; i < n; i++) {
			int cur = 0;
			for (int num = 0; num < (total / ((int) Math.pow(3, i + 1))); num++) {
				for (int j = 0; j < ((int) Math.pow(3, i - 1)); j++) {
					ans[cur][i] = 1;
					ans[cur + 1][i] = 2;
					ans[cur + 2][i] = 0;
					cur += 3;
				}
				for (int j = 0; j < ((int) Math.pow(3, i - 1)); j++) {
					ans[cur][i] = 2;
					ans[cur + 1][i] = 0;
					ans[cur + 2][i] = 1;
					cur += 3;
				}
				for (int j = 0; j < ((int) Math.pow(3, i - 1)); j++) {
					ans[cur][i] = 0;
					ans[cur + 1][i] = 1;
					ans[cur + 2][i] = 2;
					cur += 3;
				}
			}
		}

		for (int i = 0; i < (total); i++) {
			for (int j = 0; j < n; j++)
				out.print(ans[i][j]);
			out.println();
		}

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("antigray.in"));
		out = new PrintWriter("antigray.out");
		solve();
		br.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		new TaskC().run();
	}
}
