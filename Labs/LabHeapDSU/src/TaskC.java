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
	final int MAXM = (int) 3e5;
	int[] allReq = new int[MAXM];
	class Heap {
		private int size = 0;
		private int[] data = new int[MAXM];
		private int[] req = new int[MAXM];
		
		private void siftUp(int num) {
			int cur = num;
			while (cur > 0) {
				int par = (cur - 1) / 2;
				if (data[par] > data[cur]) {
					int help = data[par];
					data[par] = data[cur];
					data[cur] = help;
					
					allReq[req[par]] = cur;
					allReq[req[cur]] = par;
					
					help = req[par];
					req[par] = req[cur];
					req[cur] = help;
					cur = par;
					continue;
				}
				break;
			}
		}
		private void siftDown(int num) {
			int cur = num;
			while (2 * cur + 1 < size) {
				int l = 2 * cur + 1, r = 2 * cur + 2, par = cur;
				if (r >= size) {
					data[r] = Integer.MAX_VALUE;
				}
				if (data[l] <= data[par] && data[l] <= data[r]) {
					int help = data[par];
					data[par] = data[l];
					data[l] = help;
					
					allReq[req[par]] = l;
					allReq[req[l]] = par;
					
					help = req[par];
					req[par] = req[l];
					req[l] = help;
					cur = l;
					continue;
				}
				if (data[r] <= data[par] && data[r] <= data[l]) {
					int help = data[par];
					data[par] = data[r];
					data[r] = help;
					
					allReq[req[par]] = r;
					allReq[req[r]] = par;
					
					help = req[par];
					req[par] = req[r];
					req[r] = help;
					cur = r;
					continue;
				}
				break;
			}
		}
		public void push(int x, int numReq) {
			data[size] = x;
			req[size] = numReq;
			allReq[numReq] = size;
			siftUp(size++);
		}
		public void getMin() {
			if (size == 0) {
				out.println("*");
				return;
			}
			out.println(data[0]);
			data[0] = data[size - 1];
			req[0] = req[size - 1];
			allReq[req[size-- - 1]] = 0;
			siftDown(0);
		}
		public void decreaseKey(int num, int after) {
			int cur = allReq[num];
			data[cur] = after;
			siftUp(cur);
		}
	}

	void solve() throws IOException {
		int numReq = 0;
		Heap heap = new Heap();
		while (true) {
			String s = nextToken();
			if (s == null) {
				break;
			}
			numReq++;
			if (s.charAt(0) == 'p') {
				int x = nextInt();
				heap.push(x, numReq - 1);
				continue;
			}
			if (s.charAt(0) == 'e') {
				heap.getMin();
				continue;
			}
			int num = nextInt() - 1, x = nextInt();
			heap.decreaseKey(num, x);
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("priorityqueue.in"));
		out = new PrintWriter("priorityqueue.out");
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