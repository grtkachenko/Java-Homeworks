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

	void solve() throws IOException {
		String s = nextToken();
		int[] bal = new int[s.length()];
		bal[0] = 1;
		for (int i = 1; i < s.length(); i++)
			if (s.charAt(i) == '(')
				bal[i] = bal[i - 1] + 1;
			else
				bal[i] = bal[i - 1] - 1;
		// && (s.length() - i - 1) % 2 == bal[i] % 2)
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				if (bal[i] > 1) {
					for (int j = 0; j < i; j++)
						out.print(s.charAt(j));
					out.print(")");
					bal[i] -= 2;
					int num = s.length() - i - 1;
					for (int j = 0; j < (num - bal[i]) / 2; j++)
						out.print("(");
					for (int j = 0; j < num - (num - bal[i]) / 2; j++)
						out.print(")");
					return;

				}
			}
		}
		out.print("-");
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("nextbrackets.in"));
		out = new PrintWriter("nextbrackets.out");
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
