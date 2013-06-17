import java.io.*;
import java.util.*;

public class TaskJ {
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
		String s = nextToken();
		String[] arr = s.split("\\+");
		int[] data = new int[arr.length];
		int x = 0, n = arr.length;
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(arr[i]);
			x += data[i];
		}

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
		long ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = mincan; j < data[i]; j++) {
				ans += help[cursum][j];
			}
			cursum -= data[i];
			mincan = data[i];
		}
		out.print(ans);

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("part2num.in"));
		out = new PrintWriter("part2num.out");
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
		new TaskJ().run();
	}
}
