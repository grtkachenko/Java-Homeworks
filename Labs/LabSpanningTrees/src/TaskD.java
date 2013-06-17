import java.io.*;
import java.util.*;

public class TaskD {
	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;
	int n;
	ArrayList<Integer> ans = new ArrayList<Integer>();
	int[][] edge;

	void euler(int u) {
		for (int i = 0; i < n; i++) {
			if (edge[u][i] != 0) {
				edge[u][i]--;
				edge[i][u]--;
				euler(i);
			}
		}
		ans.add(u + 1);
	}

	void solve() throws IOException {
		n = nextInt();
		edge = new int[n][n];
		int numOdd = 0, first = -1, second = -1;
		for (int i = 0; i < n; i++) {
			int l = nextInt();
			if (l % 2 != 0) {
				numOdd++;
				if (first == -1) {
					first = i;
				} else {
					second = i;
				}
			}
			for (int j = 0; j < l; j++) {
				int v = nextInt() - 1;
				edge[i][v]++;
			}
		}
		if (numOdd == 2) {
			edge[first][second]++;
			edge[second][first]++;
		}
		euler(0);
		if (first == -1) {
			out.println(ans.size() - 1);
		} else {
			out.println(ans.size() - 2);
		}
		int start = 0;
		for (int i = 0; i < ans.size() - 1; i++) {
			if ((ans.get(i) == first + 1 && ans.get(i + 1) == second + 1)
					|| (ans.get(i + 1) == first + 1 && ans.get(i) == second + 1)) {
				start = i + 1;
				break;
			}
		}
		for (int i = start; i < ans.size(); i++) {
			out.print(ans.get(i) + " ");
		}
		for (int i = 1; i < start; i++) {
			out.print(ans.get(i) + " ");
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("euler.in"));
		out = new PrintWriter("euler.out");
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
		new TaskD().run();
	}

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
}