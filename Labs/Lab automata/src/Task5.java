import java.util.*;
import java.io.*;

public class Task5 {
    private FastScanner in;
    private PrintWriter out;
    private final static int MAX_N = 1000;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt(), l = in.nextInt();
        Set<Integer> admit = new HashSet<Integer>();
        for (int i = 0; i < k; i++) {
            admit.add(in.nextInt() - 1);
        }
        NKA nka = new NKA(n, admit);

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            char c = in.nextChar();
            nka.addEdge(from, to, c - 'a');
        }

        out.print(nka.getDKA().getNumberOfWordsWithLength(l));

    }

    class NKA extends DKA  {
        private List<Integer>[][] to;
        public NKA(int n, Set<Integer> admit) {
            super(n, admit);
            to = new ArrayList[n][MAX_CHAR];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < MAX_CHAR; j++) {
                    to[i][j] = new ArrayList<Integer>();
                }
            }
        }

        @Override
        protected void addEdge(int from, int to, int c) {
            this.to[from][c].add(to);
        }

        public DKA getDKA() {
            int lastVertex = 0;
            HashMap<HashSet<Integer>, Integer> vertexMap = new HashMap<HashSet<Integer>, Integer>();

            Set<Integer> admitDKA = new HashSet<Integer>();
            DKA res = new DKA(MAX_N, admitDKA);
            ArrayDeque<HashSet<Integer>> q = new ArrayDeque<HashSet<Integer>>();
            HashSet<Integer> start = new HashSet<Integer>();
            vertexMap.put(start, lastVertex++);
            start.add(0);
            q.addLast(start);
            while (!q.isEmpty()) {
                HashSet<Integer> cur = q.pollFirst();
                for (int admitState : admit) {
                    if (cur.contains(admitState)) {
                        admitDKA.add(vertexMap.get(cur));
                        break;
                    }
                }
                for (int i = 0; i < MAX_CHAR; i++) {
                    HashSet<Integer> next = new HashSet<Integer>();
                    for (int curState : cur) {
                        for (int nextState : to[curState][i]) {
                            next.add(nextState);
                        }
                    }
                    if (next.isEmpty()) {
                        continue;
                    }
                    if (!vertexMap.containsKey(next)) {
                        vertexMap.put(next, lastVertex++);
                        res.addEdge(vertexMap.get(cur), lastVertex - 1, i);
                        q.addLast(next);
                    } else {
                        res.addEdge(vertexMap.get(cur), vertexMap.get(next), i);
                    }
                }
            }
            return res;
        }
    }

    class DKA {
        protected final int MAX_CHAR = 31;
        private final long MOD = (long) 1e9 + 7;
        private int n;
        protected Set<Integer> admit = new HashSet<Integer>();
        private int[][] to;

        public DKA(int n, Set<Integer> admit) {
            this.n = n;
            this.admit = admit;
            to = new int[n][MAX_CHAR];
            for (int i = 0; i < n; i++) {
                Arrays.fill(to[i], -1);
            }
        }

        protected void addEdge(int from, int to, int c) {
            this.to[from][c] = to;
        }

        private long getNumberOfWordsWithLength(int l) {
            long[][] ans = new long[n][l + 1];
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
            in = new FastScanner(new File("problem5.in"));
            out = new PrintWriter(new File("problem5.out"));
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
        new Task5().run();
    }
}