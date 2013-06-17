import java.util.*;
import java.io.*;

public class TaskA {
	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

	String nextToken() throws IOException {
		String s = null;
		while (stok == null || !stok.hasMoreTokens()) {
			s = br.readLine();
			if (s == null)
				return "-1";
			stok = new StringTokenizer(s);
		}
		return stok.nextToken();
	}

	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	void solve() throws IOException {
		int n = nextInt();
		for (int i = 0; i < (1 << n); i++) {
			for (int j = n - 1; j >= 0; j--)
				if ((i & (1 << j)) != 0)
					out.print("1");
				else
					out.print("0");
			out.println();
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("allvectors.in"));
		out = new PrintWriter("allvectors.out");
		solve();
		br.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		new TaskA().run();
	}
}
