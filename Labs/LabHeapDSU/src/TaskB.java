import java.io.*;
import java.util.*;

public class TaskB {
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

	int[] p, min, max, num;

	int get(int x) {
		if (p[x] != x) {
			p[x] = get(p[x]);
		} else {
			return p[x];
		}
		return p[x];
	}

	Random help = new Random();

	void union(int x, int y) {
		int r1 = get(x), r2 = get(y);
		if (r1 == r2) {
			return;
		}
		if (help.nextBoolean()) {
			p[r1] = r2;
			min[r2] = Math.min(min[r1], min[r2]);
			max[r2] = Math.max(max[r2], max[r1]);
			num[r2] += num[r1];
		} else {
			p[r2] = r1;
			min[r1] = Math.min(min[r1], min[r2]);
			max[r1] = Math.max(max[r2], max[r1]);
			num[r1] += num[r2];
		}
	}

	void solve() throws IOException {
		int n = nextInt();
		p = new int[n];
		min = new int[n];
		max = new int[n];
		num = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = i;
			min[i] = i + 1;
			max[i] = i + 1;
			num[i] = 1;
		}
		while (true) {
			String s = nextToken();
			if (s == null) {
				break;
			}
			if (s.equals("union")) {
				int x = nextInt() - 1, y = nextInt() - 1;
				union(x, y);
				continue;
			}
			int x = get(nextInt() - 1);
			out.println(min[x] + " " + max[x] + " " + num[x]);
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("dsu.in"));
		out = new PrintWriter("dsu.out");
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
		new TaskB().run();
	}
}