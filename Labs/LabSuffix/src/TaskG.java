import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskG {
    final int MAX_VALUE = Integer.MAX_VALUE / 3;
    final int LETTER_NUMBER = 31;
    final int MAX_VER = (int) 4e5 + 31;
    final char MIN_CHAR1 = 'a' - 1, MIN_CHAR2 = 'a' - 2;
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;
    int firstMinChar, secondMinChar;

    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskG().run();
    }

    void solve() throws IOException {

        String a = nextLine(), b = nextLine();
        String data = a + MIN_CHAR1 + b + MIN_CHAR2;
        firstMinChar = a.length();
        secondMinChar = data.length() - 1;
        SuffixTree st = new SuffixTree(data);
        st.getAns();
    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("common.in"));
        out = new PrintWriter("common.out");
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

    class SuffixTree {
        private final int ROOT = 0;
        int pos = 0;
        private boolean isFirst = true;
        private String data = null;
        private int n = 0;
        private int[] suf = null;
        private int[] parent = null;
        private int[] start = null;
        private int[] len = null;
        private int[] ans = null;
        private int[][] to = null;
        private boolean leafWasCreated = false;
        private int currentIndex = 1;
        private int notSatisfied = -1;
        private int currentNode = ROOT;

        SuffixTree(String s) {
            data = s;
            n = data.length();
            suf = new int[MAX_VER];
            parent = new int[MAX_VER];
            start = new int[MAX_VER];
            len = new int[MAX_VER];
            ans = new int[MAX_VER];


            to = new int[MAX_VER][LETTER_NUMBER];
            for (int i = 0; i < MAX_VER; i++) {
                suf[i] = -1;
                ans[i] = 0;
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
                        currentNode = to[currentNode][data.charAt(j + (len[currentNode])) - MIN_CHAR2];
                    }
                } else {
                    i++;
                }
            }

        }

        int createNode(int par, int left, int right) {
            parent[currentIndex] = par;
            start[currentIndex] = left;
            len[currentIndex] = right;
            to[par][data.charAt(left) - MIN_CHAR2] = currentIndex;

            return currentIndex++;
        }

        void insert(int l, int r) {
            if (len[currentNode] > r - l) {
                if (data.charAt(start[currentNode] + r - l - len[parent[currentNode]]) != data.charAt(r)) {
                    int ver = createNode(parent[currentNode], start[currentNode], r - l);
                    to[ver][data.charAt(start[currentNode] + r - l - len[parent[currentNode]]) - MIN_CHAR2] = currentNode;
                    start[currentNode] += r - l - len[parent[currentNode]];
                    parent[currentNode] = ver;
                    currentNode = ver;
                    leafWasCreated = true;
                }
            }
            if (len[currentNode] == r - l) {
                if (to[currentNode][data.charAt(r) - MIN_CHAR2] == -1) {
                    to[currentNode][data.charAt(r) - MIN_CHAR2] = createNode(currentNode, r, MAX_VALUE);
                    currentNode = to[currentNode][data.charAt(r) - MIN_CHAR2];
                    leafWasCreated = true;
                } else {
                    currentNode = to[currentNode][data.charAt(r) - MIN_CHAR2];
                }
            }
        }

        public String getAns() {
            for (int i = 0; i < MAX_VER; i++) {
                ans[i] = 0;
            }
            dfs(ROOT, 0);

            dfsAns(ROOT, 0);
            int left = start[ansV] + 1, right = n;
            if (len[ansV] != MAX_VALUE) {
                right = start[ansV] + len[ansV] - len[parent[ansV]];
            }
            for (int i = right - ansDepp; i < right; i++) {
                out.print(data.charAt(i));
            }
            return "";
        }
        int ansV = 0, ansDepp = -1;
        void dfs(int u, int deep) {
            for (int i = 0; i < LETTER_NUMBER; i++) {
                int v = to[u][i];
                if (v == -1) {
                    continue;
                }

                if (len[v] == MAX_VALUE) {
                    pos = (n - (deep + (n - (start[v] + 1))));
                    pos--;
                    if (pos != secondMinChar && pos != firstMinChar) {
                        if (pos < firstMinChar) {
                            ans[v] = 1;
                        } else {
                            ans[v] = 2;
                        }
//                        out.println((pos + 1) + " " + ans[v]);
                    }


                }
                dfs(v, deep + (start[v] + len[v] - len[u] - (start[v] + 1) + 1));
            }

        }
        int dfsAns(int u, int deep) {
            for (int i = 0; i < LETTER_NUMBER; i++) {
                int v = to[u][i];
                if (v == -1) {
                    continue;
                }


                if (len[v] == MAX_VALUE) {
                    ans[u] |= ans[v];
                } else {
                    ans[u] |= dfsAns(v, deep + (start[v] + len[v] - len[u] - (start[v] + 1) + 1));
                }
            }
            if (ansDepp < deep && ans[u] == 3) {
                ansDepp = deep;
                ansV = u;
            }
            return ans[u];
        }
    }


}
