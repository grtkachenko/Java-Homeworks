import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TaskK {
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;
    int numBlocks;
    boolean[][] c;
    boolean[] was;
    int n, works = 0;

    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskK().run();
    }

    void dfs(int u, int[][] helpa) {
        was[u] = true;
        for (int i = 0; i < n; i++) {
            if (c[u][i]) {
                if (!was[i]) {
                    dfs(i, helpa);
                }
                for (int j = 0; j < numBlocks; j++) {
                    helpa[u][j] |= helpa[i][j];
                }
            }
        }
    }

    void solve() throws IOException {
        n = nextInt();
        int[] d = new int[n];
        boolean[][] a = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            d[i] = nextInt();
        }
        numBlocks = (n + 31) / 32;
        int[][] helpa = new int[n][numBlocks];
        c = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = nextInt() == 1;
                if (c[i][j]) {
                    helpa[i][j / 32] |= (1 << (j % 32));
                }
            }
        }
        was = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!was[i]) {
                dfs(i, helpa);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = (helpa[i][j / 32] & (1 << (j % 32))) != 0;
            }
        }


        final int[] number = new int[n];

        List<Integer> s = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j]) {
                    number[i]++;
                }
            }
            s.add(i);
        }

        List<Integer> l = new ArrayList<Integer>();


        while (!s.isEmpty()) {
            int cur = 0;
            for (int i = 0; i < n; i++) {
                if (number[s.get(i)] == 0) {
                    cur = s.get(i);
                    break;
                }
            }
            int count = 1;
            for (int i : l) {
                if (a[cur][i]) {
                    d[cur] = Math.min(d[cur], d[i] - (int) Math.ceil(count * 0.5));
                    count++;
                }
            }

            for (int i = 0; i <= l.size(); i++) {
                if (i == l.size()) {
                    l.add(i, cur);
                    break;
                }

                if (d[l.get(i)] > d[cur]) {
                    l.add(i, cur);
                    break;
                }
            }
            Integer deleteObj = cur;
            s.remove(deleteObj);
            for (int i = 0; i < n; i++) {
                if (c[i][cur]) {
                    number[i]--;
                }
            }
        }
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                v[j] += c[i][j] ? 1 : 0;
            }
        }
        List<Integer>[] ans;
        ans = new ArrayList[2];
        ans[0] = new ArrayList<Integer>();
        ans[1] = new ArrayList<Integer>();

        List<Integer> m  = new ArrayList<Integer>();
        while (!l.isEmpty()) {
            int first = -31;
            for (int x : l) {
                if (v[x] == 0) {
                    first = x;
                    break;
                }
            }
            if (m.size() == 2 || first == -31) {
                for (int i : m) {
                    for (int j = 0; j < n; j++) {
                        v[j] -= c[i][j] ? 1 : 0;
                    }
                }
                boolean isEmpty = false;
                if (m.size() == 1) {
                    ans[works % 2].add(-1);
                    isEmpty = true;
                }

                if (isEmpty) {
                    works++;
                }


                m.clear();
            }

            for (int x : l) {
                if (v[x] == 0) {
                    first = x;
                    l.remove((Integer) first);
                    break;
                }
            }
            ans[works % 2].add(first + 1);
            works++;
            m.add(first);
        }


        if (works % 2 != 0) {
            ans[1].add(-1);
            works = (works + 1) / 2;
        } else {
            works /= 2;
        }

        int LMax = Integer.MIN_VALUE;
        for (int i = 0; i < 2; i++) {
            int pos = 0;
            for (int j : ans[i]) {
                if (j != -1) {
                    LMax = Math.max(LMax, pos - d[j - 1] + 1);
                }
                pos++;
            }
        }
        out.print(LMax + " ");
        out.println(works);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < ans[i].size(); j++) {
                int x = ans[i].get(j);
                out.print(x + " ");
            }
            out.println();
        }


    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("p2precp1lmax.in"));
        out = new PrintWriter("p2precp1lmax.out");
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
