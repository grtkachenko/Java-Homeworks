import java.util.*;
import java.io.*;

public class Task3 {
    private FastScanner in;
    private PrintWriter out;
    private final int MAX_CHAR = 30;
    private final int MOD = (int) 1e9 + 7;

    private int n, m, k;
    private ArrayList<Integer>[][] reverse;
    private boolean[] used;
    private int[] color;
    private int[][] a;
    private Set<Integer>[] toSet;
    private ArrayList<Integer> topSort = new ArrayList<Integer>();


    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        Set<Integer> admit = new HashSet<Integer>();
        for (int i = 0; i < k; i++) {
            admit.add(in.nextInt() - 1);
        }

        reverse = new ArrayList[n][MAX_CHAR];
        used = new boolean[n];
        color = new int[n];
        toSet = new HashSet[n];
        a = new int[n][MAX_CHAR];

        for (int i = 0; i < n; i++) {
            toSet[i] = new HashSet<Integer>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < MAX_CHAR; j++) {
                reverse[i][j] = new ArrayList<Integer>();
            }
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1, c = in.nextChar() - 'a';
            reverse[to][c].add(from);
            a[from][c] = to;
            toSet[from].add(to);

        }

        for (int cur : admit) {
            if (!used[cur]) {
                dfs(cur);
            }
        }

        if (dfsCycle(0)) {
            out.print("-1");
            return;
        }

        Arrays.fill(used, false);
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfsSort(i);
            }
        }
        Collections.reverse(topSort);
        long[] ans = new long[n];
        ans[0] = 1;
        for (int cur : topSort) {
            for (int i = 0; i < MAX_CHAR; i++) {
                for (int v : reverse[cur][i]) {
                    ans[cur] = (ans[cur] + ans[v]) % MOD;
                }
            }
        }

        long total = 0;
        for (int cur : admit) {
            total = (total + ans[cur]) % MOD;
        }
        out.println(total);
    }

    private void dfs(int u) {
        used[u] = true;
        for (int i = 0; i < MAX_CHAR; i++) {
            for (int v : reverse[u][i]) {
                if (!used[v]) {
                    dfs(v);
                }
            }
        }
    }

    private boolean dfsCycle(int u) {
        color[u] = 1;
        boolean res = false;
        for (int v : toSet[u]) {
            if (color[v] == 1 && used[u]) {
                return true;
            }
            if (color[v] == 0) {
                res |= dfsCycle(v);
            }
        }
        color[u] = 2;
        return res;
    }

    private void dfsSort(int u) {
        used[u] = true;
        for (int v : toSet[u]) {
            if (!used[v]) {
                dfsSort(v);
            }
        }
        topSort.add(u);
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem3.in"));
            out = new PrintWriter(new File("problem3.out"));
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
        new Task3().run();
    }
}