import java.io.*;
import java.util.*;

public class TaskC {
	int[] data;
	Random help = new Random();
	int n;

	void swap(int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	int kth(int k) {
		int l = 0, r = n - 1;
		while (true) {
			if (r <= l + 1) {
				if (r == l + 1 && data[r] < data[l]) {
					swap(l, r);
				}
				return data[k];
			}
			int m = (l + help.nextInt(r - l));
			swap(m, l + 1);
			if (data[l] > data[r]) {
				swap(l, r);
			}
			if (data[l + 1] > data[r]) {
				swap(l + 1, r);
			}
			if (data[l] > data[l + 1]) {
				swap(l, l + 1);
			}
			int i = l + 1, j = r + 1, cur = data[l + 1];
			while (true) {
				while (data[++i] < cur)
					;
				while (data[--j] > cur)
					;
				if (i > j) {
					break;
				}
				swap(i, j);
			}
			data[l + 1] = data[j];
			data[j] = cur;
			if (j >= k) {
				r = j - 1;
			}
			if (j <= k) {
				l = i;
			}
		}

	}

	void solve() throws IOException {
		n = nextInt();
		int k = nextInt();

		data = new int[n];
		int a = nextInt(), b = nextInt(), c = nextInt();
		if (n == 1) {
			out.println(nextInt());
			return;
		}
		data[0] = nextInt();
		data[1] = nextInt();
		for (int i = 2; i < n; i++) {
			data[i] = a * data[i - 2] + b * data[i - 1] + c;
		}
		out.println(kth(k - 1));
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("kth.in"));
		out = new PrintWriter("kth.out");
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
		new TaskC().run();
	}
}