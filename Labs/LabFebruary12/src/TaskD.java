import java.util.*;
import java.io.*;

public class TaskD {

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
		private Node head, tail;
		private int size;

		List() {
			head = tail = null;
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

		public void pushTail(int element) {
			size++;
			if (tail == null) {
				tail = head = new Node(element, null);
				return;
			}
			Node tmp = new Node(element, null);
			tail.next = tmp;
			tail = tmp;
		}

		public int popHead() {
			if (isEmpty()) {
				return 0;
			}
			int element = head.val;
			head = head.next;
			if (head == null) {
				tail = null;
			}
			size--;
			return element;
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}

	public class Queue {
		private List data = new List();

		public int pop() {
			// ѕо условию гарантируетс€ непустой стек
			return data.popHead();
		}

		public void push(int x) {
			data.pushTail(x);
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
		br = new BufferedReader(new FileReader("queue2.in"));
		out = new PrintWriter("queue2.out");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintW riter("output.txt");
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
