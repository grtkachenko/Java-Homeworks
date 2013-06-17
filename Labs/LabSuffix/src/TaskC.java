import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskC {
    public final int MAX_VALUE = Integer.MAX_VALUE / 3;
    public final int LETTER_NUMBER = 26;
    public final int MAX_VER = (int) 6e5;
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;

    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskC().run();
    }

    void solve() throws IOException {
        String data = nextLine();
        SuffixTree st = new SuffixTree(data);
        out.println(st);
    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("tree.in"));
        out = new PrintWriter("tree.out");
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

    private class SuffixTree {
        private final int ROOT = 0;
        private String data = null;
        private int n = 0;
        private int[] suf = null;
        private int[] parent = null;
        private int[] start = null;
        private int[] end = null;
        private int[] len = null;
        private int[][] to = null;
        private boolean leafWasCreated = false;
        private int currentIndex = 1;
        private int notSatisfied = -1;
        private int currentNode = ROOT;

        public SuffixTree(String s) {
            data = s;
            n = data.length();
            suf = new int[MAX_VER];
            parent = new int[MAX_VER];
            start = new int[MAX_VER];
            end = new int[MAX_VER];
            len = new int[MAX_VER];

            to = new int[MAX_VER][LETTER_NUMBER];
            for (int i = 0; i < MAX_VER; i++) {
                suf[i] = -1;
                for (int j = 0; j < LETTER_NUMBER; j++) {
                    to[i][j] = -1;
                }
            }
            suf[ROOT] = ROOT;
            int j = 0, i = 0;
            while (i < n && j < n) {
                if (j > i) {
                    i = j;
                }
                leafWasCreated = false;
                insert(j, i);
                if (notSatisfied != -1) {
                    suf[notSatisfied] = parent[currentNode];
                    notSatisfied = -1;
                }
                if (leafWasCreated) {
                    j++;
                    currentNode = parent[currentNode];
                    if (suf[currentNode] == -1) {
                        notSatisfied = currentNode;
                        currentNode = parent[currentNode];
                    }

                    currentNode = suf[currentNode];
                    while (len[currentNode] < i - j) {
                        currentNode = to[currentNode][data.charAt(j + (len[currentNode])) - 'a'];
                    }
                } else {
                    i++;
                }
            }

        }

        private int createNode(int par, int left, int right) {
            parent[currentIndex] = par;
            start[currentIndex] = left;
            len[currentIndex] = right;
            to[par][data.charAt(left) - 'a'] = currentIndex;

            return currentIndex++;
        }

        private void insert(int l, int r) {
            if (len[currentNode] > r - l) {
                if (data.charAt(start[currentNode] + r - l - len[parent[currentNode]]) != data.charAt(r)) {
                    int ver = createNode(parent[currentNode], start[currentNode], r - l);
                    to[ver][data.charAt(start[currentNode] + r - l - len[parent[currentNode]]) - 'a'] = currentNode;
                    start[currentNode] += r - l - len[parent[currentNode]];
                    parent[currentNode] = ver;
                    currentNode = ver;
                    leafWasCreated = true;
                }
            }
            if (len[currentNode] == r - l) {
                if (to[currentNode][data.charAt(r) - 'a'] == -1) {
                    to[currentNode][data.charAt(r) - 'a'] = createNode(currentNode, r, MAX_VALUE);
                    currentNode = to[currentNode][data.charAt(r) - 'a'];
                    leafWasCreated = true;
                } else {
                    currentNode = to[currentNode][data.charAt(r) - 'a'];
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(currentIndex + " ");
            stringBuilder.append(+(currentIndex - 1) + "\n");
            dfs(ROOT, stringBuilder);
            return stringBuilder.toString();
        }


        void dfs(int u, StringBuilder stringBuilder) {
            for (int i = 0; i < LETTER_NUMBER; i++) {
                int v = to[u][i];
                if (v == -1) {
                    continue;
                }

                stringBuilder.append((u + 1) + " " + (v + 1) + " ");
                int left = start[v] + 1, right = n;
                if (len[v] != MAX_VALUE) {
                    right = start[v] + len[v] - len[u];
                }
                stringBuilder.append(left+ " " + right + "\n");
                dfs(v, stringBuilder);
            }
        }
    }


}
