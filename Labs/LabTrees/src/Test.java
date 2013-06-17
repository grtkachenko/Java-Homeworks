import java.io.*;
import java.util.*;

public class Test {
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

	class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void print() {
			System.out.println(x + " " + y);
		}
	}

	void fix(int p) {
		p = 1;
	}

	void solve() throws IOException {
		int p = 1;
		System.out.println(p);
		fix(p);
		System.out.println(p);
	}

	void run() throws IOException {
		out = new PrintWriter("bst.in");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		// br = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		// Locale.setDefault(Locale.US);
		new Test().run();
	}
}