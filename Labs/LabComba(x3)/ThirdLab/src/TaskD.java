import java.io.*;
import java.util.*;

public class TaskD {
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
		ArrayList<Integer>[] data = new ArrayList[3000];
		ArrayList<Integer> help = new ArrayList<Integer>();
		for (int i = 0; i < 3000; i++) { // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11111111
			data[i] = new ArrayList<Integer>();
		}
		while (true) {
			int n = nextInt(), k = nextInt();
			if (n * n + k * k == 0)
				break;
			if (k == 1 && n >= 3) {
				out.println(n + " 2");
				for (int i = 1; i <= n - 2; i++)
					out.print(i + " ");
				out.println(n);
				out.println(n - 1);
				out.println();
				nextLine();
				continue;
			}
			if (k == 1) {
				if (n == 1) {
					out.println("1 1");
					out.println("1");
					out.println();
					nextLine();
					continue;
				}
				if (n == 2) {
					out.println("2 2");
					out.println("1");
					out.println("2");
					out.println();
					nextLine();
					continue;
				}
			}

			for (int i = 0; i < k; i++) {
				data[i].clear();
				String s = br.readLine();
				stok = new StringTokenizer(s);
				while (stok.hasMoreTokens()) {
					data[i].add((int) Integer.parseInt(stok.nextToken()));
				}
			}
			nextLine();
			int kstart = k;
			boolean getans = false;
			for (int kk = k; kk > 1; kk--) {
				k = kk;
				if (data[k - 1].size() > 2) {
					out.println(n + " " + (k + 1));
					for (int i = 0; i < k - 1; i++) {
						for (int j = 0; j < data[i].size(); j++)
							out.print(data[i].get(j) + " ");
						out.println();
					}
					for (int i = 0; i < data[k - 1].size() - 2; i++)
						out.print(data[k - 1].get(i) + " ");
					out.println(data[k - 1].get(data[k - 1].size() - 1));
					out.println(data[k - 1].get(data[k - 1].size() - 2));
					out.println();
					getans = true;
					break;
				}
				if (kk != kstart) {
					help.clear();
					for (int i = 0; i < data[k - 1].size(); i++)
						help.add(data[k - 1].get(i));
					for (int i = 0; i < data[k - 0].size(); i++)
						help.add(data[k - 0].get(i));
					Collections.sort(help);
					data[k - 1].clear();
					data[k - 0].clear();
					for (int i = 0; i < help.size(); i++)
						data[k - 1].add(help.get(i));

				}
				boolean f1 = true;
				int cur = data[k - 2].get(data[k - 2].size() - 1);
				for (int i = 0; i < data[k - 1].size(); i++) {
					if (cur < data[k - 1].get(i)) {
						out.println(n + " " + (k - 1 + data[k - 1].size() - 1));
						for (int i1 = 0; i1 < (k - 2); i1++) {
							for (int j = 0; j < data[i1].size(); j++)
								out.print(data[i1].get(j) + " ");
							out.println();
						}
						for (int j = 0; j < data[k - 2].size(); j++)
							out.print(data[k - 2].get(j) + " ");
						out.println(data[k - 1].get(i));
						for (int j = 0; j < data[k - 1].size(); j++)
							if (j != i) {
								out.println(data[k - 1].get(j));
							}
						out.println();
						f1 = false;
						break;

					}
				}
				if (!f1) {
					getans = true;
					break;
				}
				boolean f = false;
				for (int i = data[k - 2].size() - 2; i >= data[k - 2].size() - 2; i--) {
					if (data[k - 2].size() <= 2)
						break;
					for (int j = 0; j < data[k - 1].size(); j++) {
						if (data[k - 1].get(j) > data[k - 2].get(i)) {

							help.clear();
							for (int i1 = i; i1 < data[k - 2].size(); i1++)
								help.add(data[k - 2].get(i1));
							for (int i1 = 0; i1 < data[k - 1].size(); i1++)
								if (i1 != j)
									help.add(data[k - 1].get(i1));
							Collections.sort(help);
							out.println(n + " " + (k - 1 + help.size()));
							for (int i1 = 0; i1 < k - 2; i1++) {
								for (int j1 = 0; j1 < data[i1].size(); j1++)
									out.print(data[i1].get(j1) + " ");
								out.println();
							}
							for (int i1 = 0; i1 < i; i1++)
								out.print(data[k - 2].get(i1) + " ");
							out.println(data[k - 1].get(j));
							for (int i1 = 0; i1 < help.size(); i1++)
								out.println(help.get(i1));
							out.println();
							f = true;
							break;
						}
					}
				}
				if (!f && data[k - 2].size() > 2) {
					help.clear();
					for (int i = 0; i < data[k - 1].size(); i++)
						help.add(data[k - 1].get(i));
					help.add(data[k - 2].get(data[k - 2].size() - 2));
					out.println(n + " " + (k - 1 + help.size()));
					for (int i1 = 0; i1 < k - 2; i1++) {
						for (int j1 = 0; j1 < data[i1].size(); j1++)
							out.print(data[i1].get(j1) + " ");
						out.println();
					}
					for (int i = 0; i < data[k - 2].size() - 2; i++)
						out.print(data[k - 2].get(i) + " ");

					out.println(data[k - 2].get(data[k - 2].size() - 1));
					Collections.sort(help);
					for (int i1 = 0; i1 < help.size(); i1++)
						out.println(help.get(i1));
					out.println();
					f = true;
				}
				if (f) {
					getans = true;
					break;
				}
			}
			if (!getans) {
				out.println(n + " " + n);
				for (int i = 1; i <= n; i++)
					out.println(i);
				out.println();
			}
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("nextsetpartition.in"));
		out = new PrintWriter("nextsetpartition.out");
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
		new TaskD().run();
	}
}
