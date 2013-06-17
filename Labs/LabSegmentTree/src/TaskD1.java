import java.io.*;
import java.util.*;

public class TaskD1 {

	void solve() throws IOException {
		int n = nextInt();
		long[] a = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextLong();
		}
		while (true) {
			String s = nextToken();
			if (s == null) {
				break;
			}
			int x = nextInt() - 1, y = nextInt() - 1;
			if (s.equals("min")) {
				long res = Long.MAX_VALUE;
				for (int i = x; i <= y; i++) {
					res = Math.min(res, a[i]);
				}
				out.println(res);
				continue;
			}
			if (s.equals("add")) {
				long val = nextLong();
				for (int i = x; i <= y; i++) {
					a[i] += val;
				}
				continue;
			}

		}

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("rmq2.in"));
		out = new PrintWriter("rmq2.out");
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
		new TaskD1().run();
	}
}