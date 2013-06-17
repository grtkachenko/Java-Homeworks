import java.io.*;
import java.util.*;

public class TaskF {
	boolean[] used, used1;
	int[] fup, tin;
	ArrayList<Integer>[] a, num;

	ArrayList<Integer> ans = new ArrayList<Integer>();
	boolean[] get;
	int numRoot = 0;

	void dfs(int root, int u, int deep, int par) {
		tin[u] = fup[u] = deep;
		used[u] = true;
		for (Integer v : a[u]) {
			if (v == par) {
				continue;
			}
			if (!used[v]) {
				if (u == root) {
					numRoot++;
				}
				if (numRoot > 1) {
					get[root] = true;
				}
				dfs(root, v, deep + 1, u);
				fup[u] = Math.min(fup[u], fup[v]);
				if (fup[v] >= tin[u] && u != root) {
					get[u] = true;
				}
			} else {
				fup[u] = Math.min(fup[u], tin[v]);
			}
		}
	}

	int curColor = 0;
	int[] color;

	void paint(int u, int col, int par) {
		used1[u] = true;
		for (int i = 0; i < a[u].size(); i++) {
			int v = a[u].get(i);
			if (par == v) {
				continue;
			}
			if (!used1[v]) {
				if (fup[v] >= tin[u]) {
					curColor++;
					color[num[u].get(i)] = curColor;
					paint(v, curColor, u);
				} else {
					color[num[u].get(i)] = col;
					paint(v, col, u);
				}
			} else {
				if (tin[v] <= tin[u]) {
					color[num[u].get(i)] = col;
				}
			}
		}
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		a = new ArrayList[n];
		num = new ArrayList[n];

		fup = new int[n];
		tin = new int[n];
		color = new int[m];
		get = new boolean[n];
		used = new boolean[n];
		used1 = new boolean[n];
		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<Integer>();
			num[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1, y = nextInt() - 1;
			a[x].add(y);
			a[y].add(x);
			num[x].add(i);
			num[y].add(i);
		}

		for (int i = 0; i < n; i++) {
			if (!used[i]) {
				numRoot = 0;
				dfs(i, i, 0, -1);
				paint(i, -1, -1);
			}
		}

		out.println(curColor);
		for (Integer cur : color) {
			out.print(cur + " ");
		}

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("biconv.in"));
		out = new PrintWriter("biconv.out");
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