import java.io.*;
import java.util.*;

public class TaskB {
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

	final int max = Integer.MAX_VALUE;

	class Node {
		Node l, r, p;
		int val, color;

		// 0 - black
		// 1 - red
		Node(int val, int color) {
			l = r = p = null;
			this.val = val;
			this.color = color;
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
				root = new Node(val, 0);
				root.l = new Node(max, 0);
				root.r = new Node(max, 0);
				return;
			}
			Node cur = root;
			while (true) {
				if (val < cur.val) {
					if (cur.l.l == null) {
						cur.l = new Node(val, 1);
						cur.l.l = new Node(max, 0);
						cur.l.r = new Node(max, 0);
						cur.l.p = cur;
						cur = cur.l;
						break;
					}
					cur = cur.l;
				} else {
					if (cur.r.l == null) {
						cur.r = new Node(val, 1);
						cur.r.l = new Node(max, 0);
						cur.r.r = new Node(max, 0);
						cur.r.p = cur;
						cur = cur.r;
						break;
					}
					cur = cur.r;
				}
			}
			insertNode(cur);
		}

		Node uncle(Node cur) {
			if (cur == null || cur.p == null || cur.p.p == null) {
				return null;
			}
			if (cur.p.p.l == cur.p) {
				return cur.p.p.r;
			} else {
				return cur.p.p.l;
			}

		}

		void rotateLeft(Node cur) {
			Node help = cur.r;
			if (cur == root) {
				root = help;
			}
			cur.r = help.l;
			cur.r.p = cur;
			help.l = cur;
			help.p = cur.p;
			if (cur.p == null) {

			}
			if (cur.p != null && cur.p.r == cur) {
				cur.p.r = help;
			} else {
				if (cur.p != null && cur.p.l == cur) {
					cur.p.l = help;
				}
			}
			cur.p = help;
		}

		void rotateRight(Node cur) {
			Node help = cur.l;
			if (cur == root) {
				root = help;
			}
			cur.l = help.r;
			cur.l.p = cur;
			help.r = cur;
			help.p = cur.p;
			if (cur.p != null && cur.p.r == cur) {
				cur.p.r = help;
			} else {
				if (cur.p != null && cur.p.l == cur) {
					cur.p.l = help;
				}
			}
			cur.p = help;
		}

		void insertNode(Node cur) {
			if (cur == root) {
				cur.color = 0;
				return;
			}
			if (cur.p.color == 0) {
				return;
			}
			Node u = uncle(cur);
			if (u != null && u.color == 1) {
				cur.p.color = 0;
				u.color = 0;
				u.p.color = 1;
				insertNode(u.p);
				return;
			}
			if (cur.p.r == cur && cur.p == cur.p.p.l) {
				rotateLeft(cur.p);
				cur = cur.l;
			} else {
				if (cur.p.l == cur && cur.p == cur.p.p.r) {
					rotateRight(cur.p);
					cur = cur.r;
				}
			}
			cur.p.color = 0;
			cur.p.p.color = 1;
			if (cur == cur.p.l && cur.p == cur.p.p.l) {
				rotateRight(cur.p.p);
			} else {
				rotateLeft(cur.p.p);
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

		Node sibling(Node cur) {
			if (cur == root) {
				return null;
			}
			if (cur.p.l == cur) {
				return cur.p.r;
			} else {
				return cur.p.l;
			}
		}

		void deleteOne(Node cur) {
			if (cur.r.l == null || cur.l.l == null) {
				Node child = (cur.r.l == null) ? cur.l : cur.r;
				if (cur == root) {
					root = child;
					root.p = null;
					if (root.l == null) {
						root = null;
						return;
					}
				} else {
					child.p = cur.p;
					if (cur.p.l == cur) {
						cur.p.l = child;
					} else {
						cur.p.r = child;
					}
				}
				if (cur.color == 1) {
					return;
				}
				if (child.color == 1) {
					child.color = 0;
					return;
				}
				deleteFix(child);
			}
		}

		void deleteNode(Node cur) {
			if (cur.l.l == null || cur.r.l == null) {
				deleteOne(cur);
				return;
			}
			Node help = cur.r;
			while (help.l.l != null) {
				help = help.l;
			}
			cur.val = help.val;
			deleteOne(help);
		}

		void deleteFix(Node cur) {
			if (cur.p == null) {
				return;
			}
			Node s = sibling(cur);
			if (s.color == 1) {
				cur.p.color = 1;
				s.color = 0;
				if (cur == cur.p.l) {
					rotateLeft(cur.p);
				} else {
					rotateRight(cur.p);
				}
			}
			s = sibling(cur);
			if (cur.p.color == 0 && s.color == 0 && s.l.color == 0
					&& s.r.color == 0) {
				s.color = 1;
				deleteFix(cur.p);
				return;
			}
			s = sibling(cur);
			if (cur.p.color == 1 && s.color == 0 && s.l.color == 0
					&& s.r.color == 0) {
				s.color = 1;
				cur.p.color = 0;
				return;
			}
			s = sibling(cur);
			if (s.color == 0) {
				if (cur == cur.p.l && s.r.color == 0 && s.l.color == 1) {
					s.color = 1;
					s.l.color = 0;
					rotateRight(s);
				} else {
					if (cur == cur.p.r && s.l.color == 0 && s.r.color == 1) {
						s.color = 1;
						s.r.color = 0;
						rotateLeft(s);
					}
				}
			}
			s = sibling(cur);
			s.color = cur.p.color;
			cur.p.color = 0;
			if (cur == cur.p.l) {
				s.r.color = 0;
				rotateLeft(cur.p);
			} else {
				s.l.color = 0;
				rotateRight(cur.p);
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
		br = new BufferedReader(new FileReader("bst.in"));
		out = new PrintWriter("bst.out");
		// br = new BufferedReader(new FileReader("bstsimple.in"));
		// out = new PrintWriter("bstsimple.out");
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
}