import java.io.*;
import java.util.*;

public class TaskA {
	int[] d;

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
		ArrayList<Integer>[] data = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			data[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1, y = nextInt() - 1;
			data[x].add(y);
			data[y].add(x);
		}
		d = new int[n];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[0] = 0;
		TreeSet<Pair> ts = new TreeSet<Pair>();
		for (int i = 0; i < n; i++) {
			ts.add(new Pair(i, d[i]));
		}

		for (int i = 0; i < n; i++) {
			int cur = ts.first().v;
			ts.remove(ts.first());
			for (int j = 0; j < data[cur].size(); j++) {
				int v = data[cur].get(j);
				if (d[v] > d[cur] + 1 && d[cur] != Integer.MAX_VALUE) {
					Pair tmp = new Pair(v, d[v]);
					ts.remove(tmp);
					d[v] = d[cur] + 1;
					tmp.d = d[v];
					ts.add(tmp);
				}

			}
		}
		for (int i = 0; i < n; i++) {
			out.print(d[i] + " ");
		}

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("pathbge1.in"));
		out = new PrintWriter("pathbge1.out");
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