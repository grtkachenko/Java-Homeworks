import java.io.*;
import java.util.*;

public class TaskF {
	ArrayList<Integer>[] a;
	int[] to, from;
	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		a = new ArrayList[n];
		to = new int[n];
		from = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1, y = nextInt() - 1;
			from[x]++;
			to[y]++;
			a[x].add(y);
		}
		int num = -1;
		for (int i = 0; i < n; i++) {
			if (to[i] == 0) {
				if (num != -1) {
					out.print("NO");
					return;
				}
				num = i;
			}
		}
		boolean[] was = new boolean[n];
		for (int i = 0; i < n - 1; i++) {
			int cur = -1;
			was[num] = true;
			to[num] = from[num] = 0;
			for (int j = 0; j < a[num].size(); j++) {
				int v = a[num].get(j);
				if (was[v]) {
					continue;
				}
				to[v]--;
				if (to[v] == 0) {
					if (cur != -1) {
						out.print("NO");
						return;
					}
					cur = v;
				}
			}
			if (cur == -1) {
				out.print("NO");
				return;
			}
			num = cur;
		}
		out.print("YES");
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("hamiltonian.in"));
		out = new PrintWriter("hamiltonian.out");
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
		new TaskF().run();
	}
}