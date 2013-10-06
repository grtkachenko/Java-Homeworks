import java.util.*;
import java.io.*;

public class TaskD {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        int m = in.nextInt(), n = in.nextInt();
        final int[][] to = new int[n][2];
        final char[] stateChar = new char[n];
        final boolean[][] can = new boolean[n][2];
        int curCan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                to[i][j] = in.nextInt() - 1;

            }
            stateChar[i] = in.next().charAt(0);
            can[i][curCan] = true;
        }
        String target = in.next();
        final char[] targetChar = target.toCharArray();

        for (int i = m - 1; i >= 0; i--) {
            final int nextCan = (curCan + 1) % 2;
            for (int j = 0; j < n; j++) {
                can[j][nextCan] = can[to[j][0]][curCan] && stateChar[to[j][0]] == targetChar[i] || can[to[j][1]][curCan] && stateChar[to[j][1]] == targetChar[i];
            }
            curCan = nextCan;
        }

        int total = 0;
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (can[i][curCan]) {
                total++;
                ans.add(i + 1);
            }
        }
        out.print(total + " ");
        for (int cur : ans) {
            out.print(cur + " ");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("start.in"));
            out = new PrintWriter(new File("start.out"));
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
        new TaskD().run();
    }
}