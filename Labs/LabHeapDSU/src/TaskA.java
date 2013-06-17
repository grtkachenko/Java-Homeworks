import java.io.*;
import java.util.*;

public class TaskA {
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

	void solve() throws IOException {
		int n = nextInt();
		int[] a = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			a[i] = nextInt();
		}
		for (int i = 1; i <= n; i++) {
			if (2 * i <= n && a[i] > a[2 * i]) {
				out.println("NO");
				return;
			}
			if (2 * i + 1 <= n && a[i] > a[2 * i + 1]) {
				out.println("NO");
				return;
			}
		}
		out.println("YES");
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("isheap.in"));
		out = new PrintWriter("isheap.out");
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
		new TaskA().run();
	}
}