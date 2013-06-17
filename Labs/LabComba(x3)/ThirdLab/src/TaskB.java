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
		ArrayList<Integer> data = new ArrayList<Integer>();
		ArrayList<Integer> help = new ArrayList<Integer>();

		for (int i = 0; i < n; i++)
			data.add(nextInt());
		int num = -1;
		for (int i = n - 2; i >= 0; i--)
			if (data.get(i) > data.get(i + 1)) {
				num = i;
				break;
			}
		if (num == -1) {
			for (int i = 0; i < n; i++)
				out.print("0 ");
			out.println();
		} else {
			int cur = data.get(num);
			for (int i = num; i < n; i++)
				help.add(data.get(i));
			Collections.sort(help);
			for (int i = 0; i < num; i++)
				out.print(data.get(i) + " ");
			for (int i = 0; i < help.size(); i++)
				if (help.get(i) == cur) {
					out.print(help.get(i - 1) + " ");
					cur = help.get(i - 1);
					break;
				}
			for (int i = help.size() - 1; i >= 0; i--)
				if (help.get(i) != cur)
					out.print(help.get(i) + " ");
			out.println();
		}
		num = -1;
		for (int i = n - 2; i >= 0; i--)
			if (data.get(i) < data.get(i + 1)) {
				num = i;
				break;
			}
		if (num == -1) {
			for (int i = 0; i < n; i++)
				out.print("0 ");
			out.println();
		} else {
			int cur = data.get(num);
			help.clear();
			for (int i = num; i < n; i++)
				help.add(data.get(i));
			Collections.sort(help);
			for (int i = 0; i < num; i++)
				out.print(data.get(i) + " ");
			for (int i = 0; i < help.size(); i++)
				if (help.get(i) == cur) {
					out.print(help.get(i + 1) + " ");
					cur = help.get(i + 1);
					break;
				}
			for (int i = 0; i < help.size(); i++)
				if (help.get(i) != cur)
					out.print(help.get(i) + " ");
			out.println();
		}

	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("nextperm.in"));
		out = new PrintWriter("nextperm.out");
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
