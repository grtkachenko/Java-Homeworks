import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TaskD {
    private FastScanner in;
    private static final double MAX_T = 10;
    private static final double COS_KOEF = 2;
    private static final int NUM_INTERVALS = (int) (MAX_T * COS_KOEF);
    private static final double DELTA = 1.0 / NUM_INTERVALS;
    private static final int MAX_QUERY_CONST = 1000;
    private static double totalAns = Double.MAX_VALUE;

    private static class Interval {
        int numVar;
        double curLeft = 0;
        double curRight = 1;
        double curM1;
        double curM2;
        double currentAns = Double.MAX_VALUE;

        private Interval(int numVar, double leftT) {
            this.numVar = numVar;
            curLeft = leftT;
            curRight = curLeft + DELTA;
        }

        double[] getQuery() {
            return new double[] {curM1 = curLeft + (curRight - curLeft) / 3, curM2 = curRight - (curRight - curLeft) / 3};
        }

        public void handleRes(double[] res) {
            if (res[0] < res[1]) {
                curRight = curM2;
            } else {
                curLeft = curM1;
            }
            currentAns = Math.min(currentAns, Math.min(res[0], res[1]));
            totalAns = Math.min(totalAns, currentAns);
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        Interval[][] intervals = new Interval[n][NUM_INTERVALS];
        for (int i = 0; i < n; i++) {
            double curT = 0;
            for (int j = 0; j < NUM_INTERVALS; j++) {
                intervals[i][j] = new Interval(i, curT);
                curT += DELTA;
            }
        }
        int totalQueries = 1;
        int curInterval = 0;
        double[][] query = new double[n][];

        while (totalQueries < MAX_QUERY_CONST * n) {
            for (int j = 0; j < n; j++) {
                query[j] = intervals[j][curInterval].getQuery();
            }
            double[] res = new double[2];
            for (int cur = 0; cur < n; cur++) {
                boolean isOk = true;
                for (int k = 0; k < 2; k++) {
                    if (totalQueries >= MAX_QUERY_CONST * n) {
                        isOk = false;
                        continue;
                    }
                    for (int j = 0; j < n; j++) {
                        System.out.print((cur == j ? query[j][k] : 0.000000000) + " ");
                    }
                    System.out.println();
                    res[k] = in.nextDouble();
                    totalQueries++;
                }
                if (isOk) {
                    intervals[cur][curInterval].handleRes(res);
                }
            }
            curInterval = (curInterval + 1) % NUM_INTERVALS;
        }
        int[] topIntervalNumber = new int[n];
        for (int i = 0; i < n; i++) {
            double curMin = Double.MAX_VALUE;
            for (int j = 0; j < NUM_INTERVALS; j++) {
                if (intervals[i][j].currentAns < curMin) {
                    curMin = intervals[i][j].currentAns;
                    topIntervalNumber[i] = j;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            System.out.print(intervals[j][topIntervalNumber[j]].curLeft + " ");
        }
        System.out.println();
        totalAns = Math.min(in.nextDouble(), totalAns);
        System.out.println("minimum " + totalAns);
    }

    public void run() {
        try {
            in = new FastScanner();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class FastScanner {
        private BufferedReader br;
        private StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(String[] arg) {
        new TaskD().run();
    }
}