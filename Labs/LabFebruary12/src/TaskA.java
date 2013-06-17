import java.util.*;
import java.io.*;

public class TaskA {

	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

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
		private int size;

		public int pop() {
			size--;
			// ѕо условию гарантируетс€ непустой стек
			return data.get(size);
		}

		public void push(int x) {
			data.put(x, size);
			size++;
		}

		public int size() {
			return size;
		}
	}

	void solve() throws IOException {
		int m = nextInt();
		Stack stack = new Stack();
		for (int i = 0; i < m; i++) {
			String s = nextToken();
			if (s.charAt(0) == '-') {
				out.println(stack.pop());
				continue;
			}
			stack.push(nextInt());
		}
	}

	void run() throws IOException {
		// br = new BufferedReader(new FileReader("stack1.in"));
		// out = new PrintWriter("stack1.out");
		br = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
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
