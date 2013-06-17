import java.util.*;
import java.io.*;

public class TaskB {

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

	public class List {
		private Node head;
		private int size;

		List() {
			head = null;
			size = 0;
		}

		public class Node {
			public int val;
			public Node next;

			Node(int val, Node next) {
				this.next = next;
				this.val = val;
			}
		}

		public void pushHead(int x) {
			if (head == null) {
				head = new Node(x, null);
				size++;
				return;
			}
			Node tmp = new Node(x, head);
			head = tmp;
			size++;
		}

		public int popHead() {
			int whatGet = head.val;
			if (head.next == null) {
				head = null;
				return whatGet;
			}
			head = head.next;
			return whatGet;
		}

	}

	public class Stack {
		private List data = new List();
		private int size;

		public int pop() {
			size--;
			// ѕо условию гарантируетс€ непустой стек
			return data.popHead();
		}

		public void push(int x) {
			data.pushHead(x);
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
		br = new BufferedReader(new FileReader("stack2.in"));
		out = new PrintWriter("stack2.out");
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
