import java.io.*;
import java.util.*;

public class TaskD {
	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

	String nextToken() throws IOException {
		while (stok == null || !stok.hasMoreTokens()) {
			String s = br.readLine();
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

	int n;
	char[] ans;

	void gen(int balance, int pos) throws IOException {
		if (pos == 2 * n) {
			if (balance != 0)
				return;
			for (int i = 0; i < 2 * n; i++)
				out.print(ans[i]);
			out.println();
			return;
		}
		if (balance == 0) {
			ans[pos] = '(';
			gen(balance + 1, pos + 1);
			return;
		}
		ans[pos] = '(';
		gen(balance + 1, pos + 1);
		ans[pos] = ')';
		gen(balance - 1, pos + 1);
	}

	void solve() throws IOException {
		n = nextInt();
		ans = new char[2 * n];
		gen(0, 0);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("brackets.in"));
		out = new PrintWriter("brackets.out");
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
		new TaskD().run();
	}
}
