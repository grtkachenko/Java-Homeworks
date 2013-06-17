import java.io.*;
import java.util.*;

public class TaskD {
	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

	String nextToken() throws IOException {
		String s = null;
		while (stok == null || !stok.hasMoreTokens()) {
			s = br.readLine();
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

	void print(int x, int n) throws IOException {
		for (int i = n - 1; i >= 0; i--) {
			if ((x & (1 << i)) != 0)
				out.print("1");
			else
				out.print("0");
		}
		out.println();
	}

	int get(int x, int n) {
		return ((x << 1) % (1 << n));
	}

	void solve() throws IOException {
		int n = nextInt(), current = 0;
		boolean first = true;
		boolean[] was = new boolean[(1 << n)];
		for (int i = 0; i < (1 << n); i++)
			was[i] = false;
		was[0] = true;
		while (current != 0 || first) {
			first = false;
			print(current, n);
			current = get(current, n);
			if (!was[current | 1]) {
				was[current | 1] = true;
				current = current | 1;
			} else {
				was[current] = true;
			}
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("chaincode.in"));
		out = new PrintWriter("chaincode.out");
		solve();
		br.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		new TaskD().run();
	}
}
