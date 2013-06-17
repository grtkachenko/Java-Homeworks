import java.io.*;
import java.util.*;

public class TaskB {
	boolean used[];
	int[] fup, tin;
	ArrayList<Integer>[] a;

	void dfs(int u, int deep, int par) {
		tin[u] = fup[u] = deep;
		used[u] = true;
		for (Integer v : a[u]) {
			if (v == par) {
				continue;
			}
			if (!used[v]) {
				dfs(v, deep + 1, u);
				fup[u] = Math.min(fup[u], fup[v]);
			} else {
				fup[u] = Math.min(fup[u], tin[v]);
			}
		}
	}

	class Edge {
		int u, v;

		Edge(int u, int v) {
			this.v = v;
			this.u = u;
		}
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		a = new ArrayList[n];
		fup = new int[n];
		tin = new int[n];
		used = new boolean[n];
		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<Integer>();
		}
		Edge[] edges = new Edge[m];
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1, y = nextInt() - 1;
			a[x].add(y);
			a[y].add(x);
			edges[i] = new Edge(x, y);
		}
		for (int i = 0; i < n; i++) {
			if (!used[i]) {
				dfs(i, 0, -1);
			}
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			int u = edges[i].u, v = edges[i].v;
			if ((tin[u] < tin[v] && fup[v] == tin[v])
					|| (tin[u] > tin[v] && fup[u] == tin[u])) {
				ans.add(i + 1);
			}
		}
		out.println(ans.size());
		for (Integer cur : ans) {
			out.print(cur + " ");
		}

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("bridges.in"));
		out = new PrintWriter("bridges.out");
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
		new TaskB().run();
	}
}