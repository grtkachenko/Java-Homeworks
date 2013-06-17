import java.io.*;
import java.util.*;

public class TaskD {
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

	class Triple {
		int num, p, max;

		Triple(int num, int p, int max) {
			this.num = num;
			this.p = p;
			this.max = max;
		}
	}

	class Pair {
		int type, req;

		Pair(int type, int req) {
			this.type = type;
			this.req = req;
		}
	}

	class DSU {
		int n;
		int[] p, next;
		Deque<Triple> stack = new ArrayDeque<Triple>();

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

		int getUnion(int x) {
			if (p[x] != x) {
				stack.addLast(new Triple(x, p[x], next[x]));
				p[x] = get(p[x]);
			}
			return p[x];
		}

		void union(int x, int y) {
			stack.addLast(new Triple(-1, 0, 0));
			int a = getUnion(x), b = getUnion(y);
			stack.addLast(new Triple(a, p[a], next[a]));
			stack.addLast(new Triple(b, p[b], next[b]));
			p[a] = b;
		}

		void undo() {
			while (true) {
				Triple cur = stack.pollLast();
				if (cur.num == -1) {
					break;
				}
				p[cur.num] = cur.p;
				next[cur.num] = cur.max;
			}
		}
	}

	Pair[] req;
	int[] next;
	int n;
	DSU dsu;
	Deque<Integer>[] exit;
	void getAns(int l, int r) {
		if (r == l) {
			if (req[l].type == 0) {
				int nextFree = dsu.next[dsu.get(req[l].req)];
				out.println(nextFree + 1);
				if (nextFree == n - 1) {
					dsu.union(nextFree, 0);
				} else {
					dsu.union(nextFree, nextFree + 1);
				}
				next[l] = exit[req[l].req].pollFirst();
			}
		}
		getAns(l, (l + r) / 2);
		getAns((l + r) / 2 + 1, r);
		
	}
	void solve() throws IOException {
		n = nextInt();
		int m = nextInt();
		dsu = new DSU(n);
		next = new int[n];
		req = new Pair[m];
		exit = new ArrayDeque[n];
		for (int i = 0; i < n; i++) {
			exit[i] = new ArrayDeque<Integer>();
			next[i] = INF;
		}
		for (int i = 0; i < n; i++) {
			String tmp = nextToken();
			if (tmp.equals("enter")) {
				req[i] = new Pair(0, nextInt() - 1);
			} else {
				req[i] = new Pair(1, nextInt() - 1);
				exit[req[i].req].addLast(i);
			}
		}
		getAns(0, n - 1);
		// int n = nextInt(), m = nextInt();
		// dsu = new DSU(n);
		// for (int i = 0; i < m; i++) {
		// String s = nextToken();
		// if (s.equals("union")) {
		// dsu.union(nextInt() - 1, nextInt() - 1);
		// continue;
		// }
		// if (s.equals("path")) {
		// if (dsu.get(nextInt() - 1) == dsu.get(nextInt() - 1)) {
		// out.println("YES");
		// } else {
		// out.println("NO");
		// }
		// continue;
		// }
		// dsu.undo();
		//
		// }

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
		new TaskD().run();
	}
}