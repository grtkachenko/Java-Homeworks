import java.io.*;
import java.util.*;

public class TaskLinal {
	static {
		String a = "aaaaaaaaaaaaaaaaaaaaaaaaa";
		String b = "aaaaaaaaaaaaaaaaaaaaaaaaa";
		a += "a";
		b += "b";
		System.out.println(a == b);
	}
	class V {
		int[] a = new int[5];

		V(int[] d) {
			for (int i = 0; i < 5; i++) {
				a[i] = d[i];
			}
		}

		int mul(V v) {
			int ans = 0;
			for (int i = 0; i < 5; i++) {
				ans += a[i] * v.a[i];
			}
			return ans;
		}
	}

	void solve() throws IOException {
		V[] a = new V[3];
		for (int j = 0; j < 3; j++) {
			int[] tmp = new int[5];

			for (int i = 0; i < 5; i++) {
				tmp[i] = nextInt();
			}
			a[j] = new V(tmp);

		}

		for (int i = 0; i < 100; i++) {
			int s = nextInt() - 1;
			if (s == -2) {
				break;
			}
			int s1 = nextInt() - 1;
			out.println((s + 1) + " " + (s1 + 1) + " " + a[s1].mul(a[s]));
		}
	}

	void run() throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		br = new BufferedReader(new InputStreamReader(System.in));
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
		new TaskLinal().run();
	}
}