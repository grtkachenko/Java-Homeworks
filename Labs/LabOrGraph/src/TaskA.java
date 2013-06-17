import java.io.*;
import java.util.*;

public class TaskA {
	int[] color;
	boolean cycle = false;
	ArrayList<Integer>[] a;
	ArrayList<Integer> ans = new ArrayList<Integer>();

	void dfs(int u) {
		if (cycle) {
			return;
		}
		color[u] = 1;
		for (int i = 0; i < a[u].size(); i++) {
			int v = a[u].get(i);
			if (color[v] == 0) {
				dfs(v);
			} else if (color[v] == 1) {
				out.print(-1);
				cycle = true;
				return;
			}
		}
		color[u] = 2;
		ans.add(u);
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		a = new ArrayList[n];
		color = new int[n];
		for (int i = 0; i < n; i++) {
			color[i] = 0;
			a[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			a[nextInt() - 1].add(nextInt() - 1);
		}
		for (int i = 0; i < n; i++) {
			if (color[i] == 0) {
				dfs(i);
			}
		}
		if (!cycle) {
			for (int i = 0; i < n; i++) {
				out.print((ans.get(n - i - 1) + 1) + " ");
			}
		}

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("topsort.in"));
		out = new PrintWriter("topsort.out");
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
		new TaskA().run();
	}
}