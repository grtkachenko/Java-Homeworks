import java.io.*;
import java.util.*;

public class TaskA {
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

	class Node {
		Node l, r, p;
		int val;

		Node(int val) {
			l = r = p = null;
			this.val = val;
		}
	}

	class Tree {
		Node root;

		Tree() {
			root = null;
		}

		void insert(int val) {
			if (exists(val)) {
				return;
			}
			if (root == null) {
				root = new Node(val);
				return;
			}
			Node cur = root;
			while (true) {
				if (val < cur.val) {
					if (cur.l == null) {
						cur.l = new Node(val);
						cur.l.p = cur;
						return;
					}
					cur = cur.l;
				} else {
					if (cur.r == null) {
						cur.r = new Node(val);
						cur.r.p = cur;
						return;
					}
					cur = cur.r;
				}
			}
		}

		boolean exists(int val) {
			if (root == null) {
				return false;
			}
			Node cur = root;
			while (true) {
				if (val < cur.val) {
					if (cur.l == null) {
						return false;
					}
					cur = cur.l;
				}
				if (val > cur.val) {
					if (cur.r == null) {
						return false;
					}
					cur = cur.r;
				}

				if (val == cur.val) {
					return true;
				}
			}
		}

		void delete(int val) {
			if (!exists(val)) {
				return;
			}
			Node cur = root;
			while (true) {
				if (val < cur.val) {
					cur = cur.l;
					continue;
				}
				if (val > cur.val) {
					cur = cur.r;
					continue;
				}
				if (val == cur.val) {
					break;
				}
			}
			deleteNode(cur);
		}

		void deleteNode(Node cur) {

			if (cur.l == null && cur.r == null) {
				if (cur == root) {
					root = null;
					return;
				}
				if (cur.p.l == cur) {
					cur.p.l = null;
					cur = null;
				} else {
					cur.p.r = null;
					cur = null;
				}
				return;
			}
			if (cur.r == null) {
				if (cur == root) {
					root = cur.l;
					root.p = null;
					return;
				}
				cur.l.p = cur.p;
				if (cur.p == null) {
					return;
				}
				if (cur.p.l == cur) {
					cur.p.l = cur.l;
					cur = null;
				} else {
					cur.p.r = cur.l;
					cur = null;
				}
				return;
			}
			if (cur.l == null) {
				if (cur == root) {
					root = cur.r;
					root.p = null;
					return;
				}
				cur.r.p = cur.p;
				if (cur.p == null) {
					return;
				}
				if (cur.p.l == cur) {
					cur.p.l = cur.r;
					cur = null;
				} else {
					cur.p.r = cur.r;
					cur = null;
				}
				return;
			}
			Node curHelp = cur.r;
			while (curHelp.l != null) {
				curHelp = curHelp.l;
			}

			cur.val = curHelp.val;
			deleteNode(curHelp);
		}

		void next(int val) {
			if (root == null) {
				out.println("none");
				return;
			}
			Node cur = root;
			int ans = Integer.MAX_VALUE;
			while (cur != null) {
				if (cur.val > val) {
					ans = Math.min(ans, cur.val);
				}
				if (cur.val <= val) {
					cur = cur.r;
				} else {
					cur = cur.l;
				}
			}
			if (ans == Integer.MAX_VALUE) {
				out.println("none");
			} else {
				out.println(ans);
			}
		}

		void prev(int val) {
			if (root == null) {
				out.println("none");
				return;
			}
			Node cur = root;
			int ans = -Integer.MAX_VALUE;
			while (cur != null) {
				if (cur.val < val) {
					ans = Math.max(ans, cur.val);
				}
				if (cur.val < val) {
					cur = cur.r;
				} else {
					cur = cur.l;
				}
			}
			if (ans == -Integer.MAX_VALUE) {
				out.println("none");
			} else {
				out.println(ans);
			}
		}
	}

	void solve() throws IOException {
		Tree tree = new Tree();
		while (true) {
			String s = nextToken();
			if (s == null) {
				break;
			}
			if (s.charAt(0) == 'i') {
				tree.insert(nextInt());
				continue;
			}
			if (s.charAt(0) == 'd') {
				tree.delete(nextInt());
				continue;
			}
			if (s.charAt(0) == 'e') {
				out.println(tree.exists(nextInt()));
				continue;
			}
			if (s.charAt(0) == 'n') {
				tree.next(nextInt());
				continue;
			}
			tree.prev(nextInt());
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("bstsimple.in"));
		out = new PrintWriter("bstsimple.out");
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
		new TaskA().run();
	}
}