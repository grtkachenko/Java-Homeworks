import java.io.*;
import java.util.*;

public class TaskC {
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
	long m;
	long cc(int n, int k) {
		long ans = 1;
		for (int i = n - k + 1; i <= n; i++) ans *= i;
		for (int i = 1; i <= k; i++) ans /= i;
		return ans;
	}
	long c(int n, int k) {
		if (n < k) return 0;
		if (n == k || k == 0) return 1;
		if (n <= 17) {
			return cc(n, k);
		}
		return c(n - 1, k - 1) + c(n - 1, k);
	}
	boolean[] was;
	void get(int cur, int kol, long num) throws IOException {
		if (kol == 0) {
			for (int i = 0; i <= n; i++)
				if (was[i]) {
					out.print(i + " ");
				}
			br.close();
			out.close();
			System.exit(0);
		}
		if (c[n - cur][kol - 1] >= num) {
			was[cur] = true;
			get(cur + 1, kol - 1, num);
		} else {
			get(cur + 1, kol, num - c[n - cur][kol - 1]);
		}
		
	}
	long[][] c = new long[31][31];
	void solve() throws IOException {
		n = nextInt();
		k = nextInt();
		m = nextLong();
		m++;
		for (int i = 0; i < 31; i++)
			for (int j = 0; j < 31; j++)
				if (c[i][j] == 0) c[i][j] = c(i, j);
		was = new boolean[n + 1];
		for (int i = 0; i < n; i++) was[i] = false;
		get(1, k, m);
		out.println(c(n, k));
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("num2choose.in"));
		out = new PrintWriter("num2choose.out");
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
		new TaskC().run();
	}
}
