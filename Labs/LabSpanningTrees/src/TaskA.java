import java.io.*;
import java.util.*;

public class TaskA {
	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

	void solve() throws IOException {
		int n = nextInt();
		int[] x, y;
		double[] d = new double[n];
		x = new int[n];
		y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = nextInt();
			y[i] = nextInt();
			d[i] = Double.MAX_VALUE;
		}
		boolean[] used = new boolean[n];
		d[0] = 0;
		double ans = 0;
		for (int i = 0; i < n; i++) {
			double min = Double.MAX_VALUE;
			int num = -1;
			;
			for (int j = 0; j < n; j++) {
				if (!used[j] && d[j] < min) {
					min = d[j];
					num = j;
				}
			}
			ans += min;
			used[num] = true;
			for (int j = 0; j < n; j++) {
				if (!used[j]) {
					d[j] = Math.min(
							d[j],
							Math.sqrt((x[num] - x[j]) * (x[num] - x[j])
									+ (y[num] - y[j]) * (y[num] - y[j])));
				}
			}
		}
		out.print(ans);

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("spantree.in"));
		out = new PrintWriter("spantree.out");
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

	String nextToken() throws IOException {
		while (stok == null || !stok.hasMoreTokens()) {
			String s = br.readLine();
			if (s == null) {
				return null;
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
}