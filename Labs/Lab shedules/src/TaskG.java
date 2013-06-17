import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TaskG {
    final int MAX_SUM = (int) 1e5 + 31;
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;

    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskG().run();
    }

    void solve() throws IOException {
        int n = nextInt();
        int[][] p = new int[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                p[i][j] = nextInt();
            }
        }
        long[][] dp = new long[2][MAX_SUM];
        for (long[] row : dp) {
            Arrays.fill(row, Long.MAX_VALUE / 3);
        }
        int counter = 1;
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < MAX_SUM; j++) {
                dp[counter][j] = dp[1 ^ counter][j] + p[1][i - 1];
                if (j >= p[0][i - 1]) {
                    dp[counter][j] = Math.min(dp[counter][j], dp[1 ^ counter][j - p[0][i - 1]]);
                }
            }
            counter = 1 ^ counter;
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < MAX_SUM; i++) {
            ans = Math.min(ans, Math.max(dp[1 ^ counter][i], i));
        }
        out.println(ans);


    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("r2cmax.in"));
        out = new PrintWriter("r2cmax.out");
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
