import java.io.*;
import java.util.*;

public class TaskE {
	boolean used[];
	int[] fup, tin, color;
	ArrayList<Integer>[] a;
	int curColor = 1;

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

	void paint(int u, int col) {
		color[u] = col;
		for (Integer v : a[u]) {
			if (color[v] == 0) {
				if (fup[v] == tin[v]) {
					curColor++;
					paint(v, curColor);
				} else {
					paint(v, col);
				}

			}
		}
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		a = new ArrayList[n];
		fup = new int[n];
		tin = new int[n];
		color = new int[n];
		used = new boolean[n];
		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1, y = nextInt() - 1;
			a[x].add(y);
			a[y].add(x);
		}
		for (int i = 0; i < n; i++) {
			if (color[i] == 0) {
				dfs(i, 0, -1);
				paint(i, curColor);
				curColor++;
			}
		}
		out.println(curColor - 1);
		for (int i = 0; i < n; i++) {
			out.print(color[i] + " ");
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("bicone.in"));
		out = new PrintWriter("bicone.out");
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
		new TaskE().run();
	}
}