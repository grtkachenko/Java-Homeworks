import java.io.*;
import java.util.*;

public class TaskI {
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

	void solve() throws IOException {
		int x = nextInt();
		long num = nextLong();

		int[][] help = new int[x + 1][x + 1];
		for (int i = 1; i <= x; i++)
			help[i][i] = 1;
		for (int i = 2; i <= x; i++) {
			for (int j = x; j >= 1; j--) {
				for (int k = x; k >= j; k--)
					if (i - j > 0)
						help[i][j] += help[i - j][k];
			}
		}

		int mincan = 1, cursum = x;
		for (int k = 1; k <= x; k++) {
			for (int i = mincan; i <= cursum; i++)
				if (help[cursum][i] > num) {
					if (cursum - i == 0)
						out.print(i);
					else
						out.print(i + "+");
					cursum -= i;
					mincan = i;
					break;
				} else {
					num -= help[cursum][i];
				}
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("num2part.in"));
		out = new PrintWriter("num2part.out");
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
		new TaskI().run();
	}
}
