import java.io.*;
import java.util.*;
import java.math.*;

public class TaskA implements Runnable {

    public static void main(String[] args) {
        new Thread(new TaskA()).start();
    }

    BufferedReader br;
    StringTokenizer st;
    PrintWriter out;
    boolean eof = false;
    Random rand = new Random(this.hashCode());

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        try {
            br = new BufferedReader(new FileReader("input.txt"));
            out = new PrintWriter("output.txt");
            solve();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(566);
        }
        // System.out.println((System.currentTimeMillis() - time) / 1000.0);
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return "0";
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(nextToken());
    }

    void myAssert(boolean u, String message) {
        if (!u) {
            throw new Error("Assertion failed!!! " + message);
        }
    }

    int inBounds(int x, int l, int r, String name) {
        myAssert(l <= x && x <= r, name + " not in bounds!!! " + x
                + " not in [" + l + ".." + r + "]");
        return x;
    }

    String FNAME = "cheese";

    final static int ONE_HUNDRED_THOUSAND = 100000;
    final static int TEN_MILLION = 10000000;
    final static long PREC = 100000;
    final static int MAX = 30;

    class Vertex {
        public Vertex(int i) {
            n = i;
            edges = new ArrayList<Edge>();
        }

        ArrayList<Edge> edges;
        int n;
        boolean used;
    }

    class Edge {
        public Edge(Vertex v1, Vertex v2, long c2) {
            x = v1;
            y = v2;
            c = c2;
            f = 0;
            r = null;
        }

        Vertex x, y;
        Edge r;
        long f, c;
    }

    class Graph {
        public Graph(int n) {
            v = new Vertex[n];
            for (int i = 0; i < v.length; i++) {
                v[i] = new Vertex(i);
            }
        }

        Vertex[] v;

        public void addEdge(int x, int y, long c) {
            Edge e = new Edge(v[x], v[y], c);
            Edge er = new Edge(v[y], v[x], 0);
            e.r = er;
            er.r = e;
            v[x].edges.add(e);
            v[y].edges.add(er);
        }

        public long maxFlow(int s, int t) {
            long flow = 0;
            for (long scale = 1L << 60; scale > 0; scale >>= 2) {
                for (int i = 0; i < v.length; i++) {
                    v[i].used = false;
                }
                min = Long.MAX_VALUE;
                while (dfs(v[s], v[t], scale, min)) {
                    for (int i = 0; i < v.length; i++) {
                        v[i].used = false;
                    }
                    flow += min;
                }
            }
            return flow;
        }

        private long min;

        private boolean dfs(Vertex x, Vertex t, long scale, long f) {
            if (x == t) {
                min = f;
                return true;
            }
            x.used = true;
            for (Edge e : x.edges) {
                if (e.c - e.f >= scale && !e.y.used
                        && dfs(e.y, t, scale, Math.min(f, e.c - e.f))) {
                    e.f += min;
                    e.r.f -= min;
                    return true;
                }
            }
            return false;
        }
    }

    private void solve() throws IOException {
        int n = inBounds(nextInt(), 1, MAX, "n");
        int m = inBounds(nextInt(), 1, MAX, "m");
        long[] p = new long[n];
        long[] r = new long[n];
        long[] d = new long[n];
        long sp = 0;
        long left = -1L * TEN_MILLION * ONE_HUNDRED_THOUSAND;
        long right = 1L * TEN_MILLION * ONE_HUNDRED_THOUSAND;
        for (int i = 0; i < d.length; i++) {
            p[i] = inBounds(nextInt(), 1, ONE_HUNDRED_THOUSAND, "p[" + (i + 1)
                    + "]")
                    * PREC;
            sp += p[i];
            r[i] = inBounds(nextInt(), 0, TEN_MILLION, "r[" + (i + 1) + "]")
                    * PREC;
            d[i] = inBounds(nextInt(), 0, TEN_MILLION, "d[" + (i + 1) + "]")
                    * PREC;
            myAssert(r[i] < d[i], "due before release for " + (i + 1)
                    + ": r = " + r[i] / PREC + " d = " + d[i] / PREC);
        }
        long[] s = new long[m];
        for (int j = 0; j < s.length; j++) {
            s[j] = -inBounds(nextInt(), 1, ONE_HUNDRED_THOUSAND, "s[" + (j + 1)
                    + "]");
        }
        Arrays.sort(s);
        long sum = 0;
        for (int i = 0; i < s.length; i++) {
            s[i] = -s[i];
            sum += s[i];
        }
        while (right - left > 1) {
            long middle = (left + right) / 2;
            // System.out.println(middle);
            TreeSet<Long> ts = new TreeSet<Long>();
            for (int i = 0; i < p.length; i++) {
                ts.add(r[i]);
                ts.add(d[i] + middle);
            }
            long[] t = new long[ts.size()];
            {
                int k = 0;
                for (long x : ts) {
                    t[k++] = x;
                }
            }
            Graph g = new Graph(1 + n + m * (t.length - 1) + t.length - 1 + 1);
            for (int i = 0; i < p.length; i++) {
                g.addEdge(0, i + 1, p[i]);
                for (int k = 0; k + 1 < t.length; k++) {
                    if (r[i] <= t[k] && t[k + 1] <= d[i] + middle) {
                        int sh = n + 1 + k * m;
                        for (int j = 0; j < s.length; j++) {
                            long c = (s[j] - (j + 1 < s.length ? s[j + 1] : 0))
                                    * (t[k + 1] - t[k]);
                            myAssert(c >= 0, "Negative capacity!!!");
                            g.addEdge(i + 1, sh + j, c);
                        }
                    }
                }
            }
            for (int k = 0; k + 1 < t.length; k++) {
                int sh = n + 1 + k * m;
                for (int j = 0; j < s.length; j++) {
                    long c = (j + 1)
                            * (s[j] - (j + 1 < s.length ? s[j + 1] : 0))
                            * (t[k + 1] - t[k]);
                    myAssert(c >= 0, "Negative capacity!!!");
                    g.addEdge(sh + j, 1 + n + m * (t.length - 1) + k, c);
                }
                g.addEdge(1 + n + m * (t.length - 1) + k, 1 + n + m
                        * (t.length - 1) + t.length - 1, sum
                        * (t[k + 1] - t[k]));
                myAssert(sum * (t[k + 1] - t[k]) >= 0, "Negative capacity!!!");
            }
            long flow = g.maxFlow(0, 1 + n + m * (t.length - 1) + t.length - 1);
            if (flow == sp) {
                right = middle;
            } else {
                left = middle;
            }
        }
        //out.println(1.0 * Math.max(right, 0) / PREC);
        // System.out.println(right);
        out.printf("%.9f", (1.0 * Math.max(right, 0) / PREC));
    }
}
