import java.io.*;
import java.util.*;

public class TaskE {
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
	long k;
	long[][] f;

	void solve() throws IOException {
		n = nextInt();
		k = nextLong();
		k++;
		f = new long[2 * n + 1][2 * n + 1];
		f[2 * n][0] = 1;
		for (int i = 2 * n - 1; i >= 0; i--) {
			for (int j = 2 * n - 1; j > 0; j--) {
				f[i][j] = f[i + 1][j - 1] + f[i + 1][j + 1];
			}
			f[i][0] = f[i + 1][1];
		}
		int bal = 0;
		for (int i = 0; i < 2 * n; i++) {
			if (bal == 0) {
				out.print("(");
				bal++;
				continue;
			}
			if (f[i + 1][bal + 1] >= k) {
				out.print("(");
				bal++;
			} else {
				out.print(")");
				k -= f[i + 1][bal + 1];
				bal--;
			}
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("num2brackets.in"));
		out = new PrintWriter("num2brackets.out");
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
		new TaskE().run();
	}
}
