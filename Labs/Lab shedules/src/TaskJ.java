import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskJ {
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;

    public static void main(String[] args) throws IOException {
        new TaskJ().run();
    }

    void solve() throws IOException {
        int n = nextInt();
        int d1 = nextInt(), d2 = nextInt();
        long A = nextLong(), B = nextLong(), C = nextLong(), D = nextLong();
        int[] num = new int[n + 2];
        int ans = 0;
        int d;
        if (d1 - 1 >= n) {
            ans++;
        } else {
            if (d1 > 0) {
                num[d1]++;
            }
        }
        if (d2 - 1 >= n) {
            ans++;
        } else {
            if (d2 > 0) {
                num[d2]++;
            }
        }
        for (int i = 2; i < n; i++) {
            d = (int) ((A * d1 + B * d2 + C) % D);
            d1 = d2;
            d2 = d;
            if (d - 1 >= n) {
                ans++;
            } else {
                num[d]++;
            }

        }
        int curNum = 0, cur;
        for (int i = 0; i < n; i++) {
            cur = Math.min((i + 1) - curNum, num[i + 1]);
            curNum += cur;
            ans += cur;
        }
        out.println(ans);

    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("p1p1sumu.in"));
        out = new PrintWriter("p1p1sumu.out");
//        br = new BufferedReader(new FileReader("input.txt"));
//        out = new PrintWriter("output.txt");
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        solve();
        br.close();
        out.close();
    }

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
}
