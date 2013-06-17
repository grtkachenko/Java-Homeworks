import java.io.*;
import java.util.*;

public class TaskC {
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
		int n = nextInt(), k = nextInt();
		int[] data = new int[k];
		for (int i = 0; i < k; i++) data[i] = nextInt();
		if (data[0] == n - k + 1) {
			out.print("-1");
			return;
		}
		for (int i = k - 1; i >= 0; i--) {
			if (data[i] != n - (k - i - 1)) {
				for (int j = 0; j < i; j++) out.print(data[j] + " ");
				out.print((data[i] + 1) + " ");
				for (int j = i + 1; j < k; j++) {
					out.print((data[i] + 1 + (j - i)) + " ");
				}
				return;
			}
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("nextchoose.in"));
		out = new PrintWriter("nextchoose.out");
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
		new TaskC().run();
	}
}
