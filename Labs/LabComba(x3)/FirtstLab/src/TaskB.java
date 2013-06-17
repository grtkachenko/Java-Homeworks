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

	int[] ans;
	boolean[] was;
	int n;

	void gen(int pos) throws IOException {
		if (pos == n) {
			for (int i = 0; i < n; i++)
				out.print(ans[i] + " ");
			out.println();
			return;
		}
		for (int i = 0; i < n; i++)
			if (!was[i]) {
				was[i] = true;
				ans[pos] = i + 1;
				gen(pos + 1);
				was[i] = false;
			}
	}

	void solve() throws IOException {
		n = nextInt();
		ans = new int[n];
		was = new boolean[n];
		for (int i = 1; i <= n; i++) {
			was[i - 1] = false;
		}
		gen(0);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("permutations.in"));
		out = new PrintWriter("permutations.out");
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
