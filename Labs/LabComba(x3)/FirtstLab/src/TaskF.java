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

	int n;
	int num, numx, numy;

	boolean less(int x, int y) {
		num = 0;
		while (true) {
			if (((x & (1 << num)) != 0) && ((y & (1 << num)) == 0))
				break;
			if (((x & (1 << num)) == 0) && ((y & (1 << num)) != 0))
				break;
			num++;
		}
		numx = numy = num - 1;
		for (int i = n - 1; i >= num; i--) {
			if ((x & (1 << i)) != 0)
				numx = i;
			if ((y & (1 << i)) != 0)
				numy = i;
		}
		if (numx < numy)
			return true;
		else
			return false;
	}

	class Item implements Comparable<Item> {
		int x;

		Item() {

		}

		Item(int x) {
			this.x = x;
		}

		public int compareTo(Item a) {
			if (less(this.x, a.x))
				return -1;
			else
				return 1;
		}
	}

	int kol = 0, kolhelp = 0;

	void print(int x) throws IOException {
		kol = 0;
		for (int i = 0; i < n; i++) {
			if ((x & (1 << i)) != 0)
				kol++;
		}
		kolhelp = 0;
		for (int i = 0; i < n; i++) {
			if ((x & (1 << i)) != 0) {
				kolhelp++;
				if (kol == kolhelp)
					out.print(i + 1);
				else
					out.print((i + 1) + " ");
			}
		}

	}

	void solve() throws IOException {
		n = nextInt();
		ArrayList<Item> data = new ArrayList<Item>();
		for (int i = 0; i < (1 << n); i++)
			data.add(new Item(i));
		Collections.sort(data);
		for (int i = 0; i < (1 << n); i++) {
			print(data.get(i).x);
			if (i != (1 << n) - 1)
				out.println();
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("subsets.in"));
		out = new PrintWriter("subsets.out");
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
