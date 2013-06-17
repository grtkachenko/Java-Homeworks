import java.io.*;
import java.util.*;

public class TaskB {
	ArrayList<Integer>[] a, aT;
	int[] color, ans;
	int curColor = 1;
	ArrayList<Integer> order = new ArrayList<Integer>();

	void dfs(int u) {
		color[u] = 1;
		for (int i = 0; i < a[u].size(); i++) {
			if (color[a[u].get(i)] == 0) {
				dfs(a[u].get(i));
			}
		}
		color[u] = 2;
		order.add(u);
	}

	void dfsT(int u) {
		color[u] = 1;
		for (int i = 0; i < aT[u].size(); i++) {
			if (color[aT[u].get(i)] == 0) {
				dfsT(aT[u].get(i));
			}
		}
		color[u] = 2;
		ans[u] = curColor;
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		a = new ArrayList[n];
		aT = new ArrayList[n];
		color = new int[n];
		ans = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<Integer>();
			aT[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1, y = nextInt() - 1;
			a[x].add(y);
			aT[y].add(x);
		}
		for (int i = 0; i < n; i++) {
			if (color[i] == 0) {
				dfs(i);
			}
		}
		Arrays.fill(color, 0);
		for (int i = 0; i < n; i++) {
			int v = order.get(order.size() - i - 1);
			if (color[v] == 0) {
				dfsT(v);
				curColor++;
			}

		}
		out.println(curColor - 1);
		for (int i = 0; i < n; i++) {
			out.print(ans[i] + " ");
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("cond.in"));
		out = new PrintWriter("cond.out");
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
		new TaskB().run();
	}
}