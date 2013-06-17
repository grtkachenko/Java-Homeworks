import java.util.*;
import java.io.*;

public class TaskG {
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

	int n, k;
	int[] part;
	int[][] ans = new int[20][20];
	boolean[] was;

	void getans(int last) {
		if (last == n + 1) {
			for (int i = 0; i < k; i++) {
				for (int j = 1; j <= part[i]; j++) {
					out.print((ans[i][j]) + " ");
				}
				out.println();
			}
			out.println();
			return;
		}
		int i = 0;
		while (i < k) {
			if (ans[i][0] == part[i]) {
				ans[i][part[i] - ans[i][0] + 1] = last;
				ans[i][0]--;
				getans(last + 1);
				ans[i][0]++;
				int num = i;
				while (part[i] == part[num])
					i++;
				continue;
			}
			if (ans[i][0] != 0) {
				ans[i][part[i] - ans[i][0] + 1] = last;
				ans[i][0]--;
				getans(last + 1);
				ans[i][0]++;
			}
			i++;
		}
	}

	void getNewPartition(int mincan, int numsum, int ostsum) {
		if (ostsum < 0)
			return;
		if (numsum == 0) {
			if (ostsum != 0)
				return;
			// for (int i = 0; i < k; i++)
			// out.print(part[i] + " ");
			// out.println();
			for (int i = 0; i < k; i++) {
				ans[i][0] = part[i];
			}
			for (int i = 0; i < n; i++) {
				was[i] = false;
			}
			getans(1);
			return;
		}
		for (int i = mincan; i <= n; i++) {
			part[k - numsum] = i;
			getNewPartition(i, numsum - 1, ostsum - i);
		}
	}

	void solve() throws IOException {
		n = nextInt();
		k = nextInt();
		part = new int[k + 1];
		was = new boolean[n];
		getNewPartition(1, k, n);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("part2sets.in"));
		out = new PrintWriter("part2sets.out");
		solve();
		br.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		new TaskG().run();
	}
}
