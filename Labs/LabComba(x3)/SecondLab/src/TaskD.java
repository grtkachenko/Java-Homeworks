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

	int n, k;

	long cc(int n, int k) {
		long ans = 1;
		for (int i = n - k + 1; i <= n; i++)
			ans *= i;
		for (int i = 1; i <= k; i++)
			ans /= i;
		return ans;
	}

	long c(int n, int k) {
		if (n < k)
			return 0;
		if (n == k || k == 0)
			return 1;
		if (n <= 17) {
			return cc(n, k);
		}
		return c(n - 1, k - 1) + c(n - 1, k);
	}

	long[][] c = new long[31][31];
	boolean[] was;

	void solve() throws IOException {
		n = nextInt();
		k = nextInt();
		for (int i = 0; i < 31; i++)
			for (int j = 0; j < 31; j++)
				if (c[i][j] == 0)
					c[i][j] = c(i, j);
		was = new boolean[n + 1];
		for (int i = 0; i <= n; i++)
			was[i] = false;
		for (int i = 0; i < k; i++)
			was[nextInt()] = true;
		long ans = 0;
		int tekk = k;
		for (int i = 1; i <= n; i++) {
			int kol = 0;
			if (!was[i]) {
				if (tekk <= 0)
					continue;
				ans += c[n - i][tekk - 1];
			} else {
				tekk--;
			}

		}
		out.print(ans);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("choose2num.in"));
		out = new PrintWriter("choose2num.out");
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
