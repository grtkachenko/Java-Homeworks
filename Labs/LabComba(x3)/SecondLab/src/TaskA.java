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
	boolean[] used;
	long[] ans;

	long fac(long n) {
		long x = 1;
		for (int i = 1; i <= n; i++)
			x *= i;
		return x;
	}

	void get(long num, int numost) throws IOException {
		if (numost == 0) {
			for (int i = 0; i < n; i++)
				out.print((ans[i] + 1) + " ");
			br.close();
			out.close();
			System.exit(0);
		}
		for (int i = 0; i < n; i++)
			if (!used[i]) {
				if (num <= fac(numost - 1)) {
					ans[n - numost] = i;
					used[i] = true;
					get(num, numost - 1);
				} else {
					num -= fac(numost - 1);
				}
			}
	}

	void solve() throws IOException {
		n = nextInt();
		k = nextLong();
		used = new boolean[n];
		ans = new long[n];
		for (int i = 0; i < n; i++)
			used[i] = false;
		get(k + 1, n);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("num2perm.in"));
		out = new PrintWriter("num2perm.out");
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
