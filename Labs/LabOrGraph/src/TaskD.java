import java.io.*;
import java.util.*;

public class TaskD {
	boolean[] used;
	int[] ans;
	ArrayList<Integer>[] a;

	int dfs(int u) {
		used[u] = true;
		ans[u] = 0;
		for (int i = 0; i < a[u].size(); i++) {
			int v = a[u].get(i);
			if (!used[v]) {
				if (dfs(v) == 0) {
					ans[u] = 1;
				}
			} else {
				if (ans[u] == 0) {
					ans[u] = 1;
				}
			}

		}
		return ans[u];
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt(), s = nextInt() - 1;
		a = new ArrayList[n];
		ans = new int[n];
		used = new boolean[n];
		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1, y = nextInt() - 1;
			a[x].add(y);
		}
		dfs(s);
		out.print(ans[s] == 1 ? "First player wins" : "Second player wins");
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("game.in"));
		out = new PrintWriter("game.out");
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