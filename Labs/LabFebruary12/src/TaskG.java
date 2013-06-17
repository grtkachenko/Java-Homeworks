import java.util.*;
import java.io.*;

public class TaskG {

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
			this.data = new int[100];
		}

		public int get(int i) {
			return data[i];
		}

		public void put(int x, int num) {
			if (num < data.length) {
				data[num] = x;
				return;
			}
			int[] helpData = new int[2 * data.length];
			for (int i = 0; i < data.length; i++) {
				helpData[i] = data[i];
			}
			helpData[num] = x;
			data = helpData;
		}
	}

	public class Stack {
		private Vector data = new Vector();
		private Vector curMin = new Vector();
		private int size;

		public int pop() {
			size--;
			return data.get(size);
		}

		public int getItem() {
			return data.get(size - 1);
		}

		public int getMin() {
			return curMin.get(size - 1);
		}

		public void push(int x) {
			data.put(x, size);
			if (isEmpty()) {
				curMin.put(x, size);
			} else {
				curMin.put(Math.min(x, curMin.get(size - 1)), size);
			}
			size++;
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}

	public class QueueMin {
		private Stack pushStack = new Stack();
		private Stack popStack = new Stack();

		public int pop() {
			if (!popStack.isEmpty()) {
				return popStack.pop();
			}
			while (!pushStack.isEmpty()) {
				popStack.push(pushStack.pop());
			}
			return popStack.pop();
		}

		public int getMin() {
			int x = pushStack.isEmpty() ? Integer.MAX_VALUE : pushStack
					.getMin();
			int y = popStack.isEmpty() ? Integer.MAX_VALUE : popStack.getMin();
			return Math.min(x, y);
		}

		public void push(int x) {
			pushStack.push(x);
		}

	}

	void solve() throws IOException {
		int m = nextInt();
		QueueMin queue = new QueueMin();
		for (int i = 0; i < m; i++) {
			String s = nextToken();
			if (s.charAt(0) == '-') {
				queue.pop();
				continue;
			}
			if (s.charAt(0) == '?') {
				out.println(queue.getMin());
				continue;
			}

			queue.push(nextInt());
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("queuemin.in"));
		out = new PrintWriter("queuemin.out");
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
		new TaskG().run();
	}
}
