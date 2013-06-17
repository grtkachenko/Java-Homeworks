import java.io.*;
import java.util.*;

public class TaskD {
	int[] d;

	class Edge {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	class Pair implements Comparable<Pair> {
		int v, d;

		public Pair(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.d == o.d) {
				return this.v - o.v;
			}
			return this.d - o.d;
		}
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		ArrayList<Edge>[] data = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			data[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1, y = nextInt() - 1, w = nextInt();
			data[x].add(new Edge(y, w));
			data[y].add(new Edge(x, w));
		}
		d = new int[n];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[0] = 0;
		TreeSet<Pair> ts = new TreeSet<Pair>();
		for (int i = 0; i < n; i++) {
			ts.add(new Pair(i, d[i]));
		}
		for (int i = 0; i < n; i++) {
			int cur = ts.first().v, curv = 0, curw = 0;
			ts.remove(ts.first());
			for (int j = 0; j < data[cur].size(); j++) {
				Edge e = data[cur].get(j);
				curv = e.v;
				curw = e.w;
				if (d[curv] > d[cur] + curw && d[cur] != Integer.MAX_VALUE) {
					Pair tmp = new Pair(curv, d[curv]);
					ts.remove(tmp);
					d[curv] = d[cur] + curw;
					tmp.d = d[curv];
					ts.add(tmp);
				}

			}
		}
		for (int i = 0; i < n; i++) {
			out.print(d[i] + " ");
		}

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("pathbgep.in"));
		out = new PrintWriter("pathbgep.out");
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