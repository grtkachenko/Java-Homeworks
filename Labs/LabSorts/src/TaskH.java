import java.io.*;
import java.util.*;

public class TaskH {
	class Comp {
		int x, y;

		Comp(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	void solve() throws IOException {
		System.out.println((-7 % 5) > (7 % -5));
		int n = nextInt(), numWires = 0;
		int curDeg = 1, num = 0;
		while (curDeg < n) {
			curDeg *= 2;
			num++;
		}
		int numLev = 0, curNumberLevel = curDeg / 2;
		ArrayList<Comp>[] ans = new ArrayList[20];
		for (int i = 0; i < 20; i++) {
			ans[i] = new ArrayList<Comp>();
		}
		for (int i = 0; i < num; i++) {

			for (int j = 0; j < curNumberLevel; j++) {
				int l = j * curDeg / curNumberLevel;
				int r = l + curDeg / curNumberLevel - 1;
				while (l < r) {
					if (r < n) {
						ans[numLev].add(new Comp(l + 1, r + 1));
						numWires++;
					}
					l++;
					r--;

				}
			}
			numLev++;
			int helpNumberLevel = curNumberLevel * 2;
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < helpNumberLevel; k++) {
					int l = k * curDeg / helpNumberLevel;
					int r = l + curDeg / helpNumberLevel - 1;
					int finish = (r + l) / 2;
					r = finish + 1;
					while (l != finish + 1) {
						if (r < n) {
							ans[numLev].add(new Comp(l + 1, r + 1));
							numWires++;
						}
						l++;
						r++;
					}
				}
				helpNumberLevel *= 2;
				numLev++;
			}
			curNumberLevel /= 2;
		}
		out.println(n + " " + numWires + " " + numLev);
		for (int i = 0; i < numLev; i++) {
			out.print(ans[i].size() + " ");
			for (int j = 0; j < ans[i].size(); j++) {
				out.print(ans[i].get(j).x + " " + ans[i].get(j).y + " ");
			}
			out.println();
		}
	}

	void run() throws IOException {
		// br = new BufferedReader(new FileReader("netbuild.in"));
		// out = new PrintWriter("netbuild.out");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
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
		new TaskH().run();
	}
}