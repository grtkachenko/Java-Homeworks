import java.io.*;
import java.util.*;

public class TaskB {
	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

	Random random = new Random();
	Edge[] edges;
	int n, m;
	int[] p;

	class Edge implements Comparable<Edge> {
		int u, v, w;

		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
	}

	int get(int u) {
		return p[u] == u ? u : (p[u] = get(p[u]));
	}

	void unite(int u, int v) {
		if (random.nextBoolean()) {
			p[get(u)] = get(v);
		} else {
			p[get(v)] = get(u);
		}
	}

	void solve() throws IOException {
		n = nextInt();
		m = nextInt();
		edges = new Edge[m];
		p = new int[n];
		for (int i = 0; i < m; i++) {
			edges[i] = new Edge(nextInt() - 1, nextInt() - 1, nextInt());
		}
		for (int i = 0; i < n; i++) {
			p[i] = i;
		}
		Arrays.sort(edges);
		long ans = 0;
		for (int i = 0; i < m; i++) {
			Edge cur = edges[i];
			if (get(cur.u) != get(cur.v)) {
				unite(cur.u, cur.v);
				ans += cur.w;
			}

		}
		out.println(ans);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("spantree2.in"));
		out = new PrintWriter("spantree2.out");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		// br = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		solve();
		br.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		// Locale.setDefault(Locale.US);
		new TaskB().run();
	}

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
}