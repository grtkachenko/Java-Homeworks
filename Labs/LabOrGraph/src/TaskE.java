import java.io.*;
import java.util.*;

public class TaskE {
	int[] color, from;
	boolean haveCycle = false;
	ArrayList<Integer>[] a;

	void dfs(int u) {
		if (haveCycle) {
			return;
		}
		color[u] = 1;
		for (int i = 0; i < a[u].size(); i++) {
			if (haveCycle) {
				return;
			}
			int v = a[u].get(i);
			if (color[v] == 0) {
				from[v] = u;
				dfs(v);
			} else {
				if (color[v] == 1 && !haveCycle) {
					out.println("YES");
					int cur = u;
					ArrayList<Integer> ans = new ArrayList<Integer>();
					ans.add(u + 1);
					while (cur != v) {
						cur = from[cur];
						ans.add(cur + 1);
					}
					for (int j = 0; j < ans.size(); j++) {
						out.print(ans.get(ans.size() - j - 1) + " ");
					}
					haveCycle = true;
					return;
				}
			}
		}
		color[u] = 2;
	}

	void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		a = new ArrayList[n];
		from = new int[n];
		color= new int[n];
		
		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			a[nextInt() - 1].add(nextInt() - 1);
		}
		for (int i = 0; i < n; i++) {
			if (color[i] == 0 && !haveCycle) {
				from[i] = -1;
				dfs(i);
				if (haveCycle) {
					return;
				}
			}
		}
		out.print("NO");
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("cycle.in"));
		out = new PrintWriter("cycle.out");
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
		new TaskE().run();
	}
}