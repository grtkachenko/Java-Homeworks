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

	boolean check(int val) {
		for (int i = 0; i < n - 1; i++) {
			if (((val & (1 << i)) != 0) && ((val & (1 << (i + 1))) != 0)) {
				return false;
			}
		}
		return true;
	}

	void print(int val) throws IOException {
		for (int i = n - 1; i >= 0; i--) {
			if ((val & (1 << i)) != 0)
				out.print("1");
			else
				out.print("0");
		}
		out.println();
	}

	void solve() throws IOException {
		n = nextInt();
		int kol = 0;
		for (int i = 0; i < (1 << n); i++) {
			if (check(i))
				kol++;
		}
		out.println(kol);
		for (int i = 0; i < (1 << n); i++) {
			if (check(i))
				print(i);
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("vectors.in"));
		out = new PrintWriter("vectors.out");
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
