import java.io.*;
import java.util.*;

public class Gen {
	void solve() throws IOException {
		Random help = new Random();
		out.println(1000);
		for (int i = 0; i < 1000; i++) {
			out.print((Math.abs(Math.abs(help.nextInt())) % 100) + " ");
		}
		out.println();
		for (int i = 0; i < 1000; i++) {
			if (help.nextBoolean()) {
				int l = (Math.abs(help.nextInt()) % 60) + 1;
				int r = l + Math.abs(help.nextInt()) % (100 - l - 10);
				out.println("min " + l + " " + r);
			} else {
				int l = (Math.abs(help.nextInt()) % 60) + 1;
				int r = l + Math.abs(help.nextInt()) % (100 - l - 10);
				int val = Math.abs(help.nextInt()) % 93;
				out.println("add " + l + " " + r + " " + val);
			}
		}
	}

	void run() throws IOException {
		out = new PrintWriter("rmq2.in");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		// br = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		solve();
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
		new Gen().run();
	}
}