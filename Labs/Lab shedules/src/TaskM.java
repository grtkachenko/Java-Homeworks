import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TaskM {
    final int INF = Integer.MAX_VALUE;
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;

    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskM().run();
    }

    void add_rib(List<Rib>[] g, int a, int b, int u, int c, int kk, int mm) {
        Rib r1 = new Rib(b, u, c, 0, g[b].size(), kk, mm);
        Rib r2 = new Rib(a, 0, -c, 0, g[a].size(), kk, mm);
        g[a].add(r1);
        g[b].add(r2);
    }

    void solve() throws IOException {
        int n = nextInt(), m = nextInt(), k = n;
        int size = n + 2 + m * n;
        int nold = n;
        List<Rib>[] g = new List[size];
        for (int i = 0; i < size; i++) {
            g[i] = new ArrayList<Rib>();
        }

        int[][] pp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pp[i][j] = nextInt();
            }

        }

        for (int i = 0; i < n; i++) {
            add_rib(g, 0, i + 1, 1, 0, 0, 0);
        }

        int cur = n + 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int ii = 0; ii < n; ii++) {
                    add_rib(g, ii + 1, cur, 1, (j + 1) * pp[ii][i], j, i);
                }
                add_rib(g, cur, size - 1, 1, 0, j, i);
                cur++;
            }
        }

        int s = 0, t = size - 1;
        int flow = 0, cost = 0;
        n = size;
        int[] id = new int[n];
        int[] d = new int[n];
        int[] p = new int[n];
        int[] q = new int[n];
        int[] p_rib = new int[n];

        while (flow < k) {

            for (int i = 0; i < n; i++) {
                d[i] = INF;
                id[i] = 0;
                q[i] = 0;
                p[i] = 0;
                p_rib[i] = 0;
            }


            int qh = 0, qt = 0;
            q[qt++] = s;
            d[s] = 0;
            while (qh != qt) {
                int v = q[qh];
                qh++;
                id[v] = 2;
                if (qh == n) qh = 0;
                for (int i = 0; i < g[v].size(); ++i) {
                    Rib r = g[v].get(i);
                    if (r.f < r.u && d[v] + r.c < d[r.b]) {
                        d[r.b] = d[v] + r.c;
                        if (id[r.b] == 0) {
                            q[qt] = r.b;
                            qt++;
                            if (qt == n) {
                                qt = 0;
                            }
                        } else {
                            if (id[r.b] == 2) {
                                if (--qh == -1) {
                                    qh = n - 1;
                                }
                                q[qh] =  r.b;
                            }
                        }
                        id[r.b] = 1;
                        p_rib[r.b] = i;
                        p[r.b] = v;
                    }
                }
            }
            if (d[t] == INF) break;
            int addflow = k - flow;
            for (int v = t; v != s; v = p[v]) {
                int pv = p[v];
                int pr = p_rib[v];
                addflow = Math.min(addflow, g[pv].get(pr).u - g[pv].get(pr).f);
            }
            for (int v = t; v != s; v = p[v]) {
                int pv = p[v];
                int pr = p_rib[v], r = g[pv].get(pr).back;
                g[pv].get(pr).f += addflow;
                g[v].get(r).f -= addflow;
                cost += g[pv].get(pr).c * addflow;
            }
            flow += addflow;
        }
        n = nold;
        int totalAns = 0;
        cur = n + 1;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (Rib rib : g[i + 1]) {
                if (rib.f == 1) {
                    ans[rib.mm][rib.kk] = i;
                    totalAns += pp[i][rib.mm] * (rib.kk + 1);
                }
            }
        }
        out.println(totalAns);
        for (int i = 0; i < m; i++) {
            int curSize = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (ans[i][j] == -1) {
                    continue;
                }
                curSize++;
            }
            out.print(curSize + " ");
            for (int j = n - 1; j >= 0; j--) {
                if (ans[i][j] == -1) {
                    continue;
                }
                out.print((ans[i][j] + 1) + " ");
            }
            out.println();
        }

    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("rsumc.in"));
        out = new PrintWriter("rsumc.out");
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

    class Rib {
        int b, u, c, f, kk, mm;
        int back;

        Rib(int b, int u, int c, int f, int back, int kk, int mm) {
            this.b = b;
            this.u = u;
            this.c = c;
            this.f = f;
            this.kk = kk;
            this.mm = mm;
            this.back = back;
        }
    }

    ;
}
