import java.util.*;
import java.io.*;

public class TaskD {
    private FastScanner in;
    private PrintWriter out;
    private final static int MAX_CHAR = 31;
    private final static int MAX_LEN = 101;
    private final static int MOD = 1000000007;

    public void solve() throws IOException {
        int n = in.nextInt(), start = in.next().charAt(0) - 'A';
        int[][] rules = new int[n][3];
        int rulesCount = 0;
        boolean[][] toDirect = new boolean[MAX_CHAR][MAX_CHAR];

        for (int i = 0; i < n; i++) {
            int from = in.next().charAt(0) - 'A';
            in.next();
            String toString = in.next();
            if (toString.length() == 1) {
                toDirect[from][toString.charAt(0) - 'a'] = true;
            } else {
                rules[rulesCount][0] = from;
                rules[rulesCount][1] = toString.charAt(0) - 'A';
                rules[rulesCount][2] = toString.charAt(1) - 'A';
                rulesCount++;
            }
        }

        String w = in.next();
        int[] input = new int[w.length()];
        for (int i = 0; i < w.length(); i++) {
            input[i] = w.charAt(i) - 'a';
        }
        long[][][] ans = new long[MAX_CHAR][MAX_LEN][MAX_LEN];
        for (int i = 0; i < w.length(); i++) {
            for (int j = 0; j < MAX_CHAR; j++) {
                if (toDirect[j][input[i]]) {
                    ans[j][i][i] = 1;
                }
            }
        }

        final int size = w.length();

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < rulesCount; j++) {
                for (int left = 0; left < size - i; left++) {
                    final int right = left + i;
                    for (int div = left; div < right; div++) {
                        ans[rules[j][0]][left][right] += ans[rules[j][1]][left][div] * ans[rules[j][2]][div + 1][right];
                        ans[rules[j][0]][left][right] %= MOD;
                    }
                }
            }
        }

        out.println(ans[start][0][w.length() - 1]);


    }

    public void run() {
        try {
            in = new FastScanner(new File("nfc.in"));
            out = new PrintWriter(new File("nfc.out"));
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class FastScanner {
        private BufferedReader br;
        private StringTokenizer st;

        public FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] arg) {
        new TaskD().run();
    }
}