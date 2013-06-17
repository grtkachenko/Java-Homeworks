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

	int[] ans;
	boolean[] was;
	int n, k;

	void gen(int mincan, int pos) throws IOException {
		if (pos == k) {
			for (int i = 0; i < k; i++)
				out.print(ans[i] + " ");
			out.println();
			return;
		}
		for (int i = mincan; i <= Math.min(mincan + (n - pos) - 1, n); i++) {
			ans[pos] = i;
			gen(i + 1, pos + 1);
		}
	}

	void solve() throws IOException {
		n = nextInt();
		k = nextInt();
		ans = new int[n];
		was = new boolean[n];
		gen(1, 0);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("choose.in"));
		out = new PrintWriter("choose.out");
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
