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

	void solve() throws IOException {
		String s = nextToken();
		int n = s.length();
		boolean f0 = true, f1 = true;
		for (int i = 0; i < s.length(); i++) 
			if (s.charAt(i) == '0') f1 = false; else f0 = false;
		if (f0) {
			out.println("-");
		} else {
			int num = 0;
			for (int i = s.length() - 1; i >= 0; i--) {
				if (s.charAt(i) == '1') {
					num = i;
					break;
				}
			}
			for (int i = 0; i < num; i++) out.print(s.charAt(i));
			out.print("0");
			for (int i = num + 1; i < n; i++) out.print("1");
			out.println();
		}
		if (f1) {
			out.println("-");
		} else {
			int num = 0;
			for (int i = s.length() - 1; i >= 0; i--) {
				if (s.charAt(i) == '0') {
					num = i;
					break;
				}
			}
			for (int i = 0; i < num; i++) out.print(s.charAt(i));
			out.print("1");
			for (int i = num + 1; i < n; i++) out.print("0");
			out.println();
		}
		
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("nextvector.in"));
		out = new PrintWriter("nextvector.out");
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
