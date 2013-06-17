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

	int[] ans = new int[100];
	int len = 0, n;

	void gen(int ost, int mincan) throws IOException {
		if (ost == 0) {
			out.print(ans[0]);
			for (int i = 1; i < len; i++) {
				out.print("+" + ans[i]);
			}
			out.println();
			return;
		}
		for (int i = mincan; i <= ost; i++) {
			ans[len] = i;
			len++;
			gen(ost - i, i);
			len--;
		}
	}

	void solve() throws IOException {
		n = nextInt();
		gen(n, 1);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("partition.in"));
		out = new PrintWriter("partition.out");
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
