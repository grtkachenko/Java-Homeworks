import java.io.*;
import java.util.*;

public class TaskF {
	void solve() throws IOException {
		int n = nextInt();
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = i + 1;
		}
		for (int i = 2; i < n; i++) {
			int help = ans[i / 2];
			ans[i / 2] = ans[i];
			ans[i] = help;
		}
		for (int i = 0; i < n; i++) {
			out.print(ans[i] + " ");
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("antiqs.in"));
		out = new PrintWriter("antiqs.out");
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
		new TaskF().run();
	}
}