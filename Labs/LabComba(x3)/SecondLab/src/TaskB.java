import java.io.*;
import java.util.*;

public class TaskB {
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
	int[] perm;

	long fac(long n) {
		long x = 1;
		for (int i = 1; i <= n; i++)
			x *= i;
		return x;
	}

	void solve() throws IOException {
		n = nextInt();
		perm = new int[n];
		used = new boolean[n + 1];
		for (int i = 0; i < n; i++) {
			perm[i] = nextInt();
			used[i + 1] = false;
		}

		long ans = 0;
		for (int i = 0; i < n; i++) {
			int kol = 0;
			for (int j = 1; j < perm[i]; j++)
				if (!used[j])
					kol++;
			ans += kol * fac(n - i - 1);
			used[perm[i]] = true;
		}
		out.print(ans);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("perm2num.in"));
		out = new PrintWriter("perm2num.out");
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
		new TaskB().run();
	}
}
