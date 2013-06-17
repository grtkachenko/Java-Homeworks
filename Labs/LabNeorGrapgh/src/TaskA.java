import java.io.*;
import java.util.*;

public class TaskA {
	ArrayList<Integer>[] a;
	int[] color;
	int curColor = 1;

	void dfs(int u) {
		color[u] = curColor;
		for (int i = 0; i < a[u].size(); i++) {
			if (color[a[u].get(i)] == 0) {
				dfs(a[u].get(i));
			}
		}
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		a = new ArrayList[n];
		color = new int[n];
		for (int i = 0; i < n; i++) {
			color[i] = 0;
			a[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1, y = nextInt() - 1;
			a[x].add(y);
			a[y].add(x);
		}
		for (int i = 0; i < n; i++) {
			if (color[i] == 0) {
				dfs(i);
				curColor++;
			}
		}
		out.println(curColor - 1);
		for (int i = 0; i < n; i++) {
			out.print(color[i] + " ");
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("components.in"));
		out = new PrintWriter("components.out");
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
		new TaskA().run();
	}
}