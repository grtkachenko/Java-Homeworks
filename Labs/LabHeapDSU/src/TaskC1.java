import java.io.*;
import java.util.*;

public class TaskC1 implements Runnable {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer stok;

	String nextToken() {
		try {
			while (stok == null || !stok.hasMoreTokens()) {
				stok = new StringTokenizer(in.readLine());
			}
		} catch (Exception e) {
			return null;
		}
		return stok.nextToken();
	}

	int nextInt() {
		return Integer.parseInt(nextToken());
	}

	long nextLong() {
		return Long.parseLong(nextToken());
	}

	double nextDouble() {
		return Double.parseDouble(nextToken());
	}

	class Node {
		int value, id;

		Node(int value, int id) {
			this.value = value;
			this.id = id;
		}

		public String toString() {
			return value + ":" + id;
		}
	}

	public class Heap {
		int size;
		Node[] a;
		int[] wh;

		Heap() {
			size = 0;
			a = new Node[300000];
			wh = new int[300000];
		}

		void check(int need) {
			if (a.length > need && wh.length > need) {
				return;
			}
			Node[] b = new Node[Math.max(need, a.length * 2)];
			for (int i = 0; i < size; i++) {
				b[i] = a[i];
			}
			a = b;
			int[] zh = new int[Math.max(need, wh.length * 2)];
			for (int i = 0; i < wh.length; i++) {
				zh[i] = wh[i];
			}
			wh = zh;
		}

		void siftUp(int x) {
			if (x == 0) {
				return;
			}
			int p = (x - 1) / 2;
			if (a[p].value > a[x].value) {
				Node _ = a[p];
				a[p] = a[x];
				a[x] = _;
				wh[a[p].id] = p;
				wh[a[x].id] = x;
				siftUp(p);
			}
		}

		void siftDown(int x) {
			if (2 * x + 1 >= size) {
				return;
			}
			if (2 * x + 2 >= size) {
				if (a[x].value > a[2 * x + 1].value) {
					Node _ = a[x];
					a[x] = a[2 * x + 1];
					a[2 * x + 1] = _;
					wh[a[x].id] = x;
					wh[a[2 * x + 1].id] = 2 * x + 1;
					siftDown(2 * x + 1);
				}
				return;
			}
			if (a[2 * x + 1].value < a[2 * x + 2].value) {
				if (a[x].value > a[2 * x + 1].value) {
					Node _ = a[x];
					a[x] = a[2 * x + 1];
					a[2 * x + 1] = _;
					wh[a[x].id] = x;
					wh[a[2 * x + 1].id] = 2 * x + 1;
					siftDown(2 * x + 1);
				}
			} else {
				if (a[x].value > a[2 * x + 2].value) {
					Node _ = a[x];
					a[x] = a[2 * x + 2];
					a[2 * x + 2] = _;
					wh[a[x].id] = x;
					wh[a[2 * x + 2].id] = 2 * x + 2;
					siftDown(2 * x + 2);
				}
			}
		}

		public String toString() {
			return Arrays.toString(a) + "|" + Arrays.toString(wh);
		}

		void add(int x, int id) {
			check(size + 1);
			check(id + 1);
			a[size] = new Node(x, id);
			wh[id] = size;
			size++;
			siftUp(size - 1);
		}

		int extractMin() {
			if (size == 0) {
				return Integer.MAX_VALUE;
			}
			int res = a[0].value;
			a[0] = a[size - 1];
			size--;
			wh[a[0].id] = 0;
			siftDown(0);
			return res;
		}

		void decreaseKey(int x, int y) {
			a[wh[x - 1]].value = y;
			siftUp(wh[x - 1]);
		}
	}

	void solve() throws IOException {
		Heap h = new Heap();
		String s;
		int x, y;
		int i = 0;
		while (true) {
			s = nextToken();
			if (s == null) {
				break;
			}
			if (s.equals("push")) {
				x = nextInt();
				h.add(x, i);
			} else if (s.equals("extract-min")) {
				x = h.extractMin();
				if (x == Integer.MAX_VALUE) {
					out.println('*');
				} else {
					out.println(x);
				}
			} else {
				h.decreaseKey(nextInt(), nextInt());
			}
			i++;
		}
	}

	public void run() {
		try {
			in = new BufferedReader(new FileReader("priorityqueue.in"));
			out = new PrintWriter("priorityqueue.out");
			// in = new BufferedReader(new FileReader("input.txt"));
			// out = new PrintWriter("output.txt");
			// in = new BufferedReader(new InputStreamReader(System.in));
			// out = new PrintWriter(System.out);
			solve();
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) throws IOException {
		// Locale.setDefault(Locale.US);
		new Thread(new TaskC1()).start();
	}
}