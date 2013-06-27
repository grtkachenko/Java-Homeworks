/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 01.03.13
 * Time: 20:04
 * To change this template use File | Settings | File Templates.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class TaskG {
    public final int SZ = 26, MAX_VER = (int) 1e6 + 31;
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;
    int root = 0, curMax = 1;
    int[][] bor = null;
    int[] suf, number;
    Queue<Integer> q = new ArrayDeque<Integer>();
    boolean[] used;

    public static void main(String[] args) throws IOException {
// Locale.setDefault(Locale.US);
        new TaskG().run();
    }


    char getCharByInt(int num) {
        char c = 'a';
        c += num;
        return c;
    }

    int getIntByChar(char c) {
        return c - 'a';
    }

    void addString(String s, int numberOfPatter) {
        int cur = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = getIntByChar(s.charAt(i));
            if (bor[cur][num] == -1) {
                bor[cur][num] = curMax++;
            }
            cur = bor[cur][num];
        }
        number[numberOfPatter] = cur;
    }

    void build() {
        suf[root] = root;
        for (int i = 0; i < SZ; i++) {
            if (bor[root][i] == -1) {
                bor[root][i] = root;
            } else {
                q.add(bor[root][i]);
            }
            suf[bor[root][i]] = root;
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int i = 0; i < SZ; i++) {
                int cur = suf[x];
                while (bor[cur][i] == -1 && cur != root) {
                    cur = suf[cur];
                }
                if (bor[x][i] != -1) {
                    q.add(bor[x][i]);
                    suf[bor[x][i]] = bor[cur][i];
                } else {
                    bor[x][i] = bor[cur][i];
                }
            }
        }

    }

    void dfs(int u) {
        if (!used[u]) {
            used[u] = true;
            dfs(suf[u]);
        }
    }

    void solve() throws IOException {
        int n = nextInt();
        root = 0;
        bor = new int[MAX_VER][SZ];
        number = new int[n];

        suf = new int[MAX_VER];
        used = new boolean[MAX_VER];


        for (int i = 0; i < MAX_VER; i++) {
            suf[i] = -1;
            used[i] = false;
            for (int j = 0; j < SZ; j++) {
                bor[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            addString(nextLine(), i);
        }

        build();
        String t = nextLine();

        int cur = root;
        used[root] = true;
        for (int i = 0; i < t.length(); i++) {
            cur = bor[cur][getIntByChar(t.charAt(i))];
            dfs(cur);
        }

        for (int i = 0; i < n; i++) {
            out.println(used[number[i]] ? "YES" : "NO");
        }
    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("search4.in"));
        out = new PrintWriter("search4.out");
//        br = new BufferedReader(new FileReader("input.txt"));
//        out = new PrintWriter("output.txt");
//br = new BufferedReader(new InputStreamReader(System.in));
// out = new PrintWriter(System.out);
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
