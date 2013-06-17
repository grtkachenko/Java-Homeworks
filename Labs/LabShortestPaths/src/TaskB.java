import java.io.*;
import java.util.*;

public class TaskB {
	int[] d;

	public class ItemComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer x, Integer y) {
			return d[x] - d[y];
		}
	}

	class Edge {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	void solve() throws IOException {
		int n = nextInt(), s = nextInt() - 1, f = nextInt() - 1;
		ArrayList<Edge>[] data = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			data[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int cur = nextInt();
				if (cur > 0) {
					data[i].add(new Edge(j, cur));
				}
			}
		}

		d = new int[n];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[s] = 0;
		ItemComparator comp = new ItemComparator();
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(n, comp);
		for (int i = 0; i < n; i++) {
			q.add(i);
		}

		for (int i = 0; i < n; i++) {
			int cur = q.poll();
			for (int j = 0; j < data[cur].size(); j++) {
				Edge e = data[cur].get(j);
				if (d[e.v] > d[cur] + e.w && d[cur] != Integer.MAX_VALUE) {
					d[e.v] = d[cur] + e.w;
					q.remove(e.v);
					q.add(e.v);
				}

			}
		}
		if (d[f] == Integer.MAX_VALUE) {
			out.println("-1");
		} else {
			out.println(d[f]);
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("pathmgep.in"));
		out = new PrintWriter("pathmgep.out");
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