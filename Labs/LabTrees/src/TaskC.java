import java.io.*;
import java.util.*;

public class TaskC {
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

	Random help = new Random();

	class Node {
		int x, c;
		long y;
		Node l, r;

		Node(int x) {
			this.x = x;
			l = null;
			r = null;
			c = 1;
			y = Math.abs(help.nextLong());
		}
	}

	class Treap {
		Node root;

		Treap(int n) {
			root = null;
			for (int i = 0; i < n; i++) {
				root = merge(root, new Node(i + 1));
			}
		}

		int trueCalc(Node t) {
			if (t == null) {
				return 0;
			} else {
				return t.c;
			}
		}

		void calc(Node t) {
			if (t == null) {
				return;
			}
			t.c = trueCalc(t.l) + 1 + trueCalc(t.r);
		}

		Node merge(Node l, Node r) {
			if (l == null) {
				return r;
			}
			if (r == null) {
				return l;
			}
			if (l.y < r.y) {
				l.r = merge(l.r, r);
				calc(l);
				return l;
			} else {
				r.l = merge(l, r.l);
				calc(r);
				return r;
			}
		}

		Node[] split(Node t, int k) {
			if (t == null) {
				return new Node[] { null, null };
			}
			Node[] help;
			if (trueCalc(t.l) >= k) {
				help = split(t.l, k);
				t.l = help[1];
				help[1] = t;
			} else {
				help = split(t.r, k - trueCalc(t.l) - 1);
				t.r = help[0];
				help[0] = t;
			}
			calc(help[0]);
			calc(help[1]);
			return help;
		}

		void move(int l, int r) {
			Node[] help = split(root, r);
			Node[] help1 = split(help[0], l - 1);
			help[0] = merge(help1[1], help1[0]);
			root = merge(help[0], help[1]);
		}

		void print(Node t) {
			if (t == null) {
				return;
			}
			print(t.l);
			out.print(t.x + " ");
			print(t.r);
		}

	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		Treap tree = new Treap(n);
		for (int i = 0; i < m; i++) {
			tree.move(nextInt(), nextInt());
		}
		tree.print(tree.root);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("movetofront.in"));
		out = new PrintWriter("movetofront.out");
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
		new TaskC().run();
	}
}