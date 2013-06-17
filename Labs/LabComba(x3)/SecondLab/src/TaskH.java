import java.io.*;
import java.util.*;

public class TaskH {
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
		String s = nextToken();
		n = s.length() / 2;
		f = new long[2 * n + 1][2 * n + 1];
		f[2 * n][0] = 1;
		for (int i = 2 * n - 1; i >= 0; i--) {
			for (int j = 2 * n - 1; j > 0; j--) {
				f[i][j] = f[i + 1][j - 1] + f[i + 1][j + 1];
			}
			f[i][0] = f[i + 1][1];
		}
		int bal = 0;
		Deque<Integer> stack = new ArrayDeque<Integer>();
		long ans = 0;

		for (int i = 0; i < 2 * n; i++) {
			if (s.charAt(i) == '(') {
				bal++;
				stack.addLast(0);
				continue;
			}
			if (bal + 1 <= n) {
				ans += f[i + 1][bal + 1]
						* ((int) Math.pow(2, (2 * n - i - 2 - bal) / 2));
			}
			if (s.charAt(i) == ')') {
				bal--;
				stack.pollLast();
				continue;
			}

			if (bal > 0 && stack.size() != 0)
				if (stack.peekLast() == 0) {
					ans += f[i + 1][bal - 1]
							* ((int) Math.pow(2, (2 * n - i - bal) / 2));
				}
			if (s.charAt(i) == '[') {
				bal++;
				stack.addLast(2);
				continue;
			}
			if (bal + 1 <= n) {
				ans += f[i + 1][bal + 1]
						* ((int) Math.pow(2, (2 * n - i - 2 - bal) / 2));
			}
			bal--;
			stack.pollLast();

		}
		out.print(ans);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("brackets2num2.in"));
		out = new PrintWriter("brackets2num2.out");
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
		new TaskH().run();
	}
}
