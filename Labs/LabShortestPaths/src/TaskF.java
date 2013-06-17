import java.io.*;
import java.util.*;

public class TaskF {
	// CHANGE!!!
	final int NO_EDGE = (int) 1e9;
	final int MAX = Integer.MAX_VALUE;

	void solve() throws IOException {
		int n = nextInt();
		int[][] d = new int[n][n];
		int[][] next = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				d[i][j] = nextInt();
				if (d[i][j] == NO_EDGE) {
					d[i][j] = MAX;
				} else {
					if (i != j) {
						next[i][j] = j;
					} else {
						next[i][j] = -1;
					}
				}

			}
			if (d[i][i] < 0) {
				out.println("YES");
				out.println("2");
				out.print((i + 1) + " " + (i + 1));
				return;
			} else {
				d[i][i] = MAX;
			}

		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (d[i][k] != MAX && d[k][j] != MAX
							&& d[i][j] > d[i][k] + d[k][j] && k != i && k != j) {
						d[i][j] = d[i][k] + d[k][j];
						next[i][j] = next[i][k];
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (d[i][i] < 0) {
				ArrayList<Integer> ans = new ArrayList<Integer>();
				int cur = i;
				boolean[] used = new boolean[n];
				ans.add(cur);
				used[cur] = true;
				cur = next[i][i];

				while (cur != i) {
					if (used[cur]) {
						int tmp = cur;
						ans.clear();
						ans.add(tmp);
						tmp = next[cur][cur];
						while (tmp != cur) {
							ans.add(tmp);
							tmp = next[tmp][cur];
						}
						ans.add(tmp);
						out.println("YES");
						out.println(ans.size());
						for (Integer j : ans) {
							out.print((j + 1) + " ");
						}
						return;
					}
					used[cur] = true;
					ans.add(cur);
					cur = next[cur][i];

				}
				ans.add(cur);
				out.println("YES");
				out.println(ans.size());
				for (Integer j : ans) {
					out.print((j + 1) + " ");
				}
				return;
			}
		}
		out.println("NO");

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("negcycle.in"));
		out = new PrintWriter("negcycle.out");
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
		new TaskF().run();
	}
}