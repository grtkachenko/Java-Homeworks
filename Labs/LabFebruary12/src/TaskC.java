import java.util.*;
import java.io.*;

public class TaskC {

	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;
	final int C = 2;

	String nextToken() throws IOException {
		String s = null;
		while (stok == null || !stok.hasMoreTokens()) {
			s = br.readLine();
			if (s == null) {
				return "-1";
			}

			stok = new StringTokenizer(s);
		}
		return stok.nextToken();

	}

	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	public class Vector {
		private int[] data;

		Vector() {
			this.data = new int[1];
		}

		public int get(int i) {
			return data[i];
		}

		public boolean hasExp(int left, int right) {
			int size = right - left;
			if (data.length / C > size) {
				int[] helpData = new int[data.length / C];
				for (int i = left; i < right; i++) {
					helpData[i - left] = data[i];
				}
				data = helpData;
				return true;
			}
			return false;
		}

		public boolean put(int x, int left, int right) {

			if (right < data.length) {
				data[right] = x;
				return false;
			}
			int[] helpData = new int[2 * data.length];
			for (int i = left; i < right; i++) {
				helpData[i - left] = data[i];
			}
			helpData[right - left] = x;
			data = helpData;
			return true;
		}
	}

	public class Queue {
		private Vector data = new Vector();
		private int tail = 0, head = 0;

		public int pop() {
			int whatGet = data.get(head);
			head++;
			if (data.hasExp(head, tail)) {
				tail -= head;
				head = 0;
			}
			return whatGet;
		}

		public void push(int x) {
			if (data.put(x, head, tail)) {
				tail = tail - head + 1;
				head = 0;
				return;
			}
			tail++;
		}

	}

	void solve() throws IOException {
		int m = nextInt();
		Queue queue = new Queue();
		for (int i = 0; i < m; i++) {
			String s = nextToken();
			if (s.charAt(0) == '-') {
				out.println(queue.pop());
				continue;
			}
			queue.push(nextInt());
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("queue1.in"));
		out = new PrintWriter("queue1.out");
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
