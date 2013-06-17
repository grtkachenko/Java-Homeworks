import java.io.*;
import java.util.*;

public class TaskG {
	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;

	String nextToken() throws IOException {
		while (stok == null || !stok.hasMoreTokens()) {
			String s = br.readLine();
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

	void solve() throws IOException {
		String s = nextToken();
		String[] arr = s.split("=");
		int x = Integer.parseInt(arr[0]);
		String[] sum = arr[1].split("\\+");
		int[] data = new int[sum.length];
		for (int i = 0; i < sum.length; i++) {
			data[i] = Integer.parseInt(sum[i]);
		}
		if (sum.length == 1) {
			out.println("No solution");
			return;
		}
		out.print(x + "=");
		int n = sum.length;
		for (int i = 0; i < n - 2; i++)
			out.print(data[i] + "+");
		if (data[n - 2] + 1 <= data[n - 1] - 1) {
			out.print((data[n - 2] + 1) + "+");
			int cur = data[n - 1] - 1;
			for (int i = 0; i < (cur / (data[n - 2] + 1)) - 1; i++)
				out.print((data[n - 2] + 1) + "+");
			out.print(data[n - 2] + 1 + cur % (data[n - 2] + 1));
		} else {
			out.print(data[n - 2] + data[n - 1]);
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("nextpartition.in"));
		out = new PrintWriter("nextpartition.out");
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
		new TaskG().run();
	}
}
