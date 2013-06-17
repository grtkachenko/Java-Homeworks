import java.io.*;
import java.util.*;

public class TaskB {
	void solve() throws IOException {
		int n = nextInt();
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = nextInt();
		}
		int m = nextInt();
		for (int i = 0; i < m; i++) {
			int cur = nextInt(), last = 0;
			;
			int l = -1, r = n;
			while (r - l > 1) {
				int mid = (l + r) / 2;
				if (data[mid] <= cur) {
					l = mid;
				} else {
					r = mid;
				}
			}
			if (data[l] != cur) {
				out.println("-1 -1");
				continue;
			}
			last = l + 1;
			l = -1;
			r = n;
			while (r - l > 1) {
				int mid = (l + r) / 2;
				if (data[mid] >= cur) {
					r = mid;
				} else {
					l = mid;
				}
			}
			out.println((r + 1) + " " + last);
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("binsearch.in"));
		out = new PrintWriter("binsearch.out");
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
		new TaskB().run();
	}
}