import java.io.*;
import java.util.*;

public class TaskD {
	ArrayList<Integer>[] a;
	boolean[] used;
	int[] deep;
	boolean isCan = true;

	void dfs(int u, int par, int curDeep) {
		used[u] = true;
		deep[u] = curDeep;
		for (int i = 0; i < a[u].size(); i++) {
			if (a[u].get(i) == par) {
				continue;
			}
			if (!used[a[u].get(i)]) {
				dfs((int) a[u].get(i), u, curDeep + 1);
			} else {
				if (deep[u] % 2 == deep[a[u].get(i)] % 2) {
					isCan = false;
				}
			}
		}
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		a = new ArrayList[n];
		used = new boolean[n];
		deep = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1, y = nextInt() - 1;
			a[x].add(y);
			a[y].add(x);
		}
		for (int i = 0; i < n; i++) {
			if (!used[i]) {
				dfs(i, -1, 0);
			}
		}
		out.print(isCan ? "YES" : "NO");
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("bipartite.in"));
		out = new PrintWriter("bipartite.out");
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