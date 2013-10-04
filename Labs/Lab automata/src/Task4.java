import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Task4 {
    private FastScanner in;
    private PrintWriter out;

    private int[][] to;
    private long[][] ans;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt(), l = in.nextInt();
        Set<Integer> admit = new HashSet<Integer>();
        for (int i = 0; i < k; i++) {
            admit.add(in.nextInt() - 1);
        }
        DKA dka = new DKA(n, m, k, admit);

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            char c = in.nextChar();
            dka.addEdge(from, to, c);
        }

        out.print(dka.getNumberOfWordsWithLength(l));

    }

    class DKA {
        private final int MAX_CHAR = 31;
        private final long MOD = (long) 1e9 + 7;
        private int n, m, k;
        private Set<Integer> admit = new HashSet<Integer>();
        private int start = 0;
        private int[][] to;

        DKA(int n, int m, int k, Set<Integer> admit) {
            this.n = n;
            this.m = m;
            this.k = k;
            this.admit = admit;
            to = new int[n][MAX_CHAR];
            for (int i = 0; i < n; i++) {
                Arrays.fill(to[i], -1);
            }
        }

        void addEdge(int from, int to, char c) {
            this.to[from][c - 'a'] = to;
        }

        long getNumberOfWordsWithLength(int l) {
            ans = new long[n][l + 1];
            ans[0][0] = 1;
            for (int depth = 0; depth < l; depth++) {
                for (int i = 0; i < n; i++) {
                    for (int c = 0; c < MAX_CHAR; c++) {
                        if (to[i][c] != -1) {
                            ans[to[i][c]][depth + 1] = (ans[to[i][c]][depth + 1] + ans[i][depth]) % MOD;
                        }
                    }
                }
            }
            long total = 0;
            for (int cur : admit) {
                total = (total + ans[cur][l]) % MOD;
            }
            return total;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem4.in"));
            out = new PrintWriter(new File("problem4.out"));
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

        public char nextChar() {
            return next().charAt(0);
        }
    }

    public static void main(String[] arg) {
        new Task4().run();
    }
}