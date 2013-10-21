import java.util.*;
import java.io.*;

public class TaskA {
    private FastScanner in;
    private PrintWriter out;
    private static final int MAX_CHAR = 31;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                a[i][j] = in.nextInt() - 1;
            }
        }
        double[][][] weight = new double[n][2][MAX_CHAR];
        for (int i = 0; i < m; i++) {
            final int len = in.nextInt();
            final double val = 1.0 / len;
            char[] inString = in.next().toCharArray(), outString = in.next().toCharArray();
            int curState = 0;
            for (int j = 0; j < len; j++) {
                weight[curState][inString[j] - '0'][outString[j] - 'a'] -= val;
                curState = a[curState][inString[j] - '0'];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                double minValue = Double.MAX_VALUE;
                int index = 0;
                for (int k = 0; k < MAX_CHAR; k++) {
                    if (minValue > weight[i][j][k]) {
                        index = k;
                        minValue = weight[i][j][k];
                    }
                }
                char ans = (char) ('a' + index);
                out.print(j == 0 ? (ans + " ") : (ans + "\n"));
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("discrete.in"));
            out = new PrintWriter(new File("discrete.out"));
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