import java.io.*;
import java.util.*;

public class TaskE {
	class Edge {
		int u, v;
		long w;

		public Edge(int u, int v, long w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}

	boolean[] used;
	ArrayList<Edge>[] a;

	void dfs(int u) {
		used[u] = true;
		for (int i = 0; i < a[u].size(); i++) {
			int v = a[u].get(i).v;
			if (!used[v]) {
				dfs(v);
			}
		}
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt(), s = nextInt() - 1;
		ArrayList<Edge> data = new ArrayList<Edge>();
		a = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<Edge>();
		}

		for (int i = 0; i < m; i++) {
			Edge tmp = new Edge(nextInt() - 1, nextInt() - 1, nextLong());
			a[tmp.u].add(tmp);
			data.add(tmp);

		}
		long[] d = new long[n];
		Arrays.fill(d, Long.MAX_VALUE);
		d[s] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < data.size(); j++) {
				int u = data.get(j).u, v = data.get(j).v;
				long w = data.get(j).w;
				if (d[v] > d[u] + w && d[u] != Long.MAX_VALUE) {
					d[v] = d[u] + w;
				}

			}
		}
		long[] ans = new long[n];
		for (int i = 0; i < n; i++) {
			ans[i] = d[i];
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < data.size(); j++) {
				int u = data.get(j).u, v = data.get(j).v;
				long w = data.get(j).w;
				if (d[v] < ans[v]) {
					continue;
				}
				if (d[v] > d[u] + w && d[u] != Long.MAX_VALUE) {
					d[v] = d[u] + w;
				}

			}
		}
		used = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (ans[i] != d[i] && ans[i] != Long.MAX_VALUE && !used[i]) {
				dfs(i);
			}
		}

		for (int i = 0; i < n; i++) {
			if (ans[i] == d[i] && ans[i] != Long.MAX_VALUE && !used[i]) {
				out.println(ans[i]);
			} else {
				if (ans[i] == Long.MAX_VALUE) {
					out.println("*");
				} else {
					out.println("-");
				}
			}
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("path.in"));
		out = new PrintWriter("path.out");
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