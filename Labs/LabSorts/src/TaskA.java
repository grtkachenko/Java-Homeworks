import java.io.*;
import java.util.*;

public class TaskA {
	final int MAXN = (int) 1e6;

	class Heap {
		int[] data;
		int size = 0;

		Heap(int[] a, int n) {
			data = new int[n];
			for (int i = 0; i < n; i++) {
				data[i] = Integer.MAX_VALUE;
			}
			for (int i = 0; i < n; i++) {
				add(a[i]);
			}
		}

		void add(int val) {
			data[size++] = val;
			siftUp(size - 1);
		}

		int getMin() {
			int ans = data[0];
			data[0] = data[--size];
			siftDown(0);
			return ans;
		}

		void siftUp(int num) {
			if (num == 0 || data[(num - 1) / 2] < data[num]) {
				return;
			}
			int help = data[num];
			data[num] = data[(num - 1) / 2];
			data[(num - 1) / 2] = help;
			siftUp((num - 1) / 2);
		}

		void siftDown(int num) {
			int left = 2 * num + 1, right = 2 * num + 2, min = num;
			if (left < size && data[left] < data[num]) {
				min = left;
			}
			if (right < size && data[right] < data[min]) {
				min = right;
			}
			if (min != num) {
				int help = data[min];
				data[min] = data[num];
				data[num] = help;
				siftDown(min);
			}
		}
	}

	void solve() throws IOException {
		int n = nextInt();
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = nextInt();
		}
		Heap heap = new Heap(data, n);
		for (int i = 0; i < n; i++) {
			out.print(heap.getMin() + " ");
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("sort.in"));
		out = new PrintWriter("sort.out");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		// br = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		solve();
		br.close();
		out.close();
	}

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

	public static void main(String[] args) throws IOException {
		// Locale.setDefault(Locale.US);
		new TaskA().run();
	}
}