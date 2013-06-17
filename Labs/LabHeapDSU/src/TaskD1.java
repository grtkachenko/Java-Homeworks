import java.io.*;
import java.util.*;

public class TaskD1 {
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

	final int MAXN = (int) 1e5, INF = Integer.MAX_VALUE;
	Random help = new Random();
	boolean[] used;

	class DSU {
		int n;
		int[] p, next;

		DSU(int n) {
			this.n = n;
			p = new int[n];
			next = new int[n];
			for (int i = 0; i < n; i++) {
				p[i] = next[i] = i;
			}
		}

		int get(int x) {
			if (p[x] != x) {
				p[x] = get(p[x]);
			}
			return p[x];
		}

		void union(int x, int y) {
			int a = get(x), b = get(y);
			int curNext = next[b];
			if (help.nextBoolean()) {
				p[a] = b;
				next[b] = curNext;
			} else {
				p[b] = a;
				next[a] = curNext;
			}
		}

		void cut(int x) {
			int root = next[get(x)];
			if (used[root]) {
				for (int i = 0; i < n; i++) {
					p[i] = x;
					next[i] = x;
				}
				used[x] = false;
				return;
			}
			int cur = root;
			p[root] = root;
			next[root] = root;
			if (cur == 0) {
				cur = n - 1;
			} else {
				cur--;
			}
			while (cur != x) {
				next[cur] = root;
				p[cur] = root;
				if (cur == 0) {
					cur = n - 1;
				} else {
					cur--;
				}
			}
			used[x] = false;
			p[x] = x;
			next[x] = x;
			if (cur == 0) {
				cur = n - 1;
			} else {
				cur--;
			}
			while (used[cur]) {
				next[cur] = x;
				p[cur] = x;
				if (cur == 0) {
					cur = n - 1;
				} else {
					cur--;
				}
			}
		}
	}

	int[] next;
	int n;
	DSU dsu;

	void solve() throws IOException {
		n = nextInt();
		int m = nextInt();
		dsu = new DSU(n);
		used = new boolean[n];
		for (int i = 0; i < m; i++) {
			String tmp = nextToken();
			int x = nextInt() - 1;
			if (tmp.equals("enter")) {
				int next = dsu.next[dsu.get(x)];
				used[next] = true;
				out.println(next + 1);
				if (next == n - 1) {
					dsu.union(next, 0);
				} else {
					dsu.union(next, next + 1);
				}
			} else {
				dsu.cut(x);
			}
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("parking.in"));
		out = new PrintWriter("parking.out");
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
		new TaskD1().run();
	}
}