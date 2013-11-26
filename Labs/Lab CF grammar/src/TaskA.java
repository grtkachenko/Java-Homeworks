import java.util.*;
import java.io.*;

public class TaskA {
    private FastScanner in;
    private PrintWriter out;
    private static final int MAX_CHAR = 31;

    public void solve() throws IOException {
        int n = in.nextInt();
        int start = in.next().charAt(0) - 'A';
        List<Integer>[][] to = new List[MAX_CHAR][MAX_CHAR];
        for (int i = 0; i < MAX_CHAR; i++) {
            for (int j = 0; j < MAX_CHAR; j++) {
                to[i][j] = new ArrayList<Integer>();
            }
        }

        for (int i = 0; i < n; i++) {
            String from = in.next();
            in.next();
            String toString = in.next();
            to[from.charAt(0) - 'A'][toString.charAt(0) - 'a'].add(toString.length() == 1 ? -1 : (toString.charAt(1) - 'A'));
        }

        int m = in.nextInt();
        Set<Integer>[] where = new Set[2];
        for (int j = 0; j < 2; j++) {
            where[j] = new HashSet<Integer>();
        }

        for (int i = 0; i < m; i++) {
            String s = in.next();
            int cnt = 0;
            where[0].clear();
            where[0].add(start);

            for (int j = 0; j < s.length() - 1; j++) {
                where[cnt ^ 1].clear();
                for (int cur : where[cnt]) {
                    for (int curChar : to[cur][s.charAt(j) - 'a']) {
                        if (curChar != -1) {
                            where[cnt ^ 1].add(curChar);
                        }
                    }
                }
                cnt ^= 1;
            }

            boolean ok = false;
            for (int cur : where[cnt]) {
                for (int curChar : to[cur][s.charAt(s.length() - 1) - 'a']) {
                    if (curChar == -1) {
                        ok = true;
                    }
                }
            }
            out.println(ok ? "yes" : "no");
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("automaton.in"));
            out = new PrintWriter(new File("automaton.out"));
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
    }

    public static void main(String[] arg) {
        new TaskA().run();
    }
}