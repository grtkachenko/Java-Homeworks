import java.util.*;
import java.io.*;

public class TaskK2 {
    FastScanner in;
    PrintWriter out;

    int n;
    int[] d;
    int[][] a, c;
    int[][] connected;
    int countBlocks;
    boolean[] was;

    void dfs(int v) {
        was[v] = true;
        for (int i = 0; i < n; i++) {
            if (c[v][i] == 1) {
                if (!was[i]) dfs(i);
                for (int j = 0; j < countBlocks; j++) {
                    connected[v][j] |= connected[i][j];
                }
            }
        }
    }

    public void solve() throws IOException {
        n = in.nextInt();
        d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = in.nextInt();
        }
        a = new int[n][n];
        c = new int[n][n];
        countBlocks = (n + 31) / 32;
        connected = new int[n][countBlocks];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = in.nextInt();
                if (c[i][j] == 1) {
                    int numOfBlock = j / 32;
                    connected[i][numOfBlock] |= (1 << (j % 32));
                }
            }
        }
        was = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!was[i]) {
                dfs(i);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((connected[i][j / 32] & (1 << (j % 32))) != 0) {
                    a[i][j] = 1;
                }
            }
        }
        int[] countSuc = new int[n];
        int[] countPre = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                countSuc[i] += c[i][j];
                countPre[j] += c[i][j];
            }
        }
        ArrayList<Integer> L = new ArrayList<Integer>();
        ArrayList<Integer> S = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            S.add(i);
        }
        while (S.size() > 0) {
            int cur = -1;
            for (int x : S) {
                if (countSuc[x] == 0) {
                    cur = x;
                    break;
                }
            }
            int count = 1;

            for (int x : L) {
                if (a[cur][x] == 1) {
                    d[cur] = Math.min(d[cur], d[x] - (int)Math.ceil(count * 0.5));
                    count++;
                }
            }
            for (int i = 0; i <= L.size(); i++) {
                if (i == L.size() || d[L.get(i)] > d[cur]) {
                    L.add(i, cur);
                    break;
                }
            }

            for (int i = 0; i < n; i++) {
                countSuc[i] -= c[i][cur];
            }
            S.remove((Integer)cur);
        }
        System.out.println(L);
        ArrayList<Integer>[] shedule = new ArrayList[2];
        for (int i = 0; i < 2; i++) {
            shedule[i] = new ArrayList<Integer>();
        }

        int T = 0;
        ArrayList<Integer> M = new ArrayList<Integer>();
        int numberOfWork = 0;
        while (L.size() > 0) {
            int cur = -1;
            for (int x : L) {
                if (countPre[x] == 0) {
                    cur = x;
                    break;
                }
            }
            if (M.size() == 2 || cur == -1) {
                for (int i : M) {
                    for (int j = 0; j < n; j++) {
                        countPre[j] -= c[i][j];
                    }
                }
                if (M.size() == 1) {
                    shedule[numberOfWork % 2].add(-1);
                    ++numberOfWork;
                }
                T++;
                M.clear();
            }
            for (int x : L) {
                if (countPre[x] == 0) {
                    cur = x;
                    break;
                }
            }
            M.add(cur);
            shedule[numberOfWork % 2].add(cur + 1);
            numberOfWork++;
            L.remove((Integer)cur);
        }
        if (numberOfWork % 2 == 1) {
            shedule[1].add(-1);
            numberOfWork++;
        }

        int ans = -Integer.MIN_VALUE;
        for (int i = 0; i < 2; i++) {
            for (int j = 0 ; j < shedule[i].size(); j++) {
                if (shedule[i].get(j) != -1) {
                    ans = Math.max(ans, 1 + j - d[shedule[i].get(j) - 1]);
                }
            }
        }
        out.println(ans + " " + (numberOfWork / 2));
        for (int i = 0; i < 2; i++) {
            for (int x : shedule[i]) {
                out.print(x + " ");
            }
            out.println();
        }
    }

    void run() {
        try {
            in = new FastScanner(new File("input.txt"));
            out = new PrintWriter(new File("output.txt"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        boolean hasNext() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String s = br.readLine();
                    if (s == null) {
                        return false;
                    }
                    st = new StringTokenizer(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(String[] arg) {
        new TaskK2().run();
    }
}