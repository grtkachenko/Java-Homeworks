import java.io.*;
import java.util.*;

public class TaskE {
	void solve() throws IOException {
		int n = nextInt(), m = nextInt(), k = nextInt();
		ArrayList<Integer>[] radix = new ArrayList[300];
		String[] data = new String[n];
		int[] sort = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = nextToken();
			sort[i] = i;
		}
		for (int i = 0; i < 300; i++) {
			radix[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < 300; j++) {
				radix[j].clear();
			}
			for (int j = 0; j < n; j++) {
				radix[(int) data[sort[j]].charAt(m - i - 1)].add(sort[j]);
			}
			int num = 0;
			for (int j = 0; j < 300; j++) {
				for (int kk = 0; kk < radix[j].size(); kk++) {
					sort[num++] = radix[j].get(kk);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			out.println(data[sort[i]]);
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("radixsort.in"));
		out = new PrintWriter("radixsort.out");
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