import java.io.*;
import java.util.*;

public class TaskD {
	double[] data;

	boolean isTrue(double a, int n, double m) {
		data[0] = a;
		data[1] = m;
		for (int i = 2; i < n; i++) {
			data[i] = 2 * (data[i - 1] + 1) - data[i - 2];
			if (data[i] < 0 || Math.abs(data[i] - 1e-8) < 0) {
				return false;
			}
		}
		return true;
	}

	double getLast(double a, int n, double m) {
		data[0] = a;
		data[1] = m;
		for (int i = 2; i < n; i++) {
			data[i] = 2 * (data[i - 1] + 1) - data[i - 2];
		}
		return data[n - 1];
	}

	void solve() throws IOException {
		int n = nextInt();
		data = new double[n];
		double a = nextDouble();
		double l = 0, r = 1e15;
		for (int i = 0; i < 100; i++) {
			double m = (l + r) / 2;
			if (isTrue(a, n, m)) {
				r = m;
			} else {
				l = m;
			}
		}

		out.println(getLast(a, n, l));
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("garland.in"));
		out = new PrintWriter("garland.out");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		// br = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		solve();
		br.close();
		out.close();
	}

	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

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

	public static void main(String[] args) throws IOException {
		// Locale.setDefault(Locale.US);
		new TaskD().run();
	}
}