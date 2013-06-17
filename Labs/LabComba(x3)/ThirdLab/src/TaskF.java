import java.io.*;
import java.util.*;

public class TaskF {
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
		int n = nextInt();
		int[] data = new int[n];
		ArrayList<Integer> help = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			data[i] = nextInt();
		}
		int num = -1;
		for (int i = n - 2; i >= 0; i--) {
			if (data[i] < data[i + 1]) {
				num = i;
				break;
			}
		}
		if (num == -1) {
			for (int i = 0; i < n; i++)
				out.print("0 ");
			return;
		}
		for (int i = 0; i < num; i++)
			out.print(data[i] + " ");
		for (int i = num + 1; i < n; i++)
			help.add(data[i]);
		Collections.sort(help);
		for (int i = 0; i < help.size(); i++)
			if (help.get(i) > data[num]) {
				out.print(help.get(i) + " ");
				help.remove(i);
				help.add(data[num]);
				break;
			}
		Collections.sort(help);
		for (int i = 0; i < help.size(); i++)
			out.print(help.get(i) + " ");

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("nextmultiperm.in"));
		out = new PrintWriter("nextmultiperm.out");
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
		new TaskF().run();
	}
}
