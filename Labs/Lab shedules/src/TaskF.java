import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class TaskF {
    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer stok;


    private class Polynom {
        private int deg;
        private int[] k;

        public Polynom(int deg) {
            this.deg = deg;
            k = new int[deg + 1];
        }

        public BigInteger calc(int p) {
            BigInteger pBig = BigInteger.valueOf(p);
            BigInteger ans = BigInteger.valueOf(k[k.length - 1]);
            BigInteger curDeg = BigInteger.ONE;
            for (int i = k.length - 2; i >= 0; i--) {
                curDeg = curDeg.multiply(pBig);
                ans = ans.add(curDeg.multiply(BigInteger.valueOf(k[i])));
            }
            return ans;
        }


    }

    private class Job implements Comparable<Job> {
        private int index;
        private int numberOfSuc = 0;
        private int p;
        private Polynom polynom;

        public Job(int index, int p) {
            this.index = index;
            this.p = p;
        }

        @Override
        public int compareTo(Job job) {
            return index - job.index;
        }
    }

    void solve() throws IOException {
        int n = nextInt();
        boolean[][] a = new boolean[n][n];
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(i, nextInt());
        }
        for (int i = 0; i < n; i++) {
            jobs[i].polynom = new Polynom(nextInt());
            for (int j = 0; j < jobs[i].polynom.deg + 1; j++) {
                jobs[i].polynom.k[j] = nextInt();
            }
        }
        int d = nextInt();
        for (int i = 0; i < d; i++) {
            int x = nextInt() - 1, y = nextInt() - 1;
            a[x][y] = true;
            jobs[x].numberOfSuc++;
        }
        int curP = 0;
        Set<Job> s = new TreeSet<Job>();
        for (int i = 0; i < n; i++) {
            curP += jobs[i].p;
            s.add(jobs[i]);
        }
        Job[] order = new Job[n];
        for (int i = 0; i < n; i++) {
            BigInteger minValue = null;
            Job curMin = null;
            for (Job job : s) {
                if (job.numberOfSuc == 0) {
                    BigInteger cur = job.polynom.calc(curP);
                    if (minValue == null || minValue.compareTo(cur) > 0) {
                        minValue = cur;
                        curMin = job;
                    }
                }
            }
            s.remove(curMin);
            curMin.numberOfSuc = Integer.MAX_VALUE;
            order[n - i - 1] = curMin;
            curP -= curMin.p;
            for (Job job : s) {
                if (a[job.index][curMin.index]) {
                    job.numberOfSuc--;
                }
            }
        }
        BigInteger fMax = null;
        int[] start = new int[n];
        int curTime = 0;
        for (Job job : order) {
            int i = job.index;
            start[i] = curTime;
            curTime += jobs[i].p;
            BigInteger cur = jobs[i].polynom.calc(curTime);
            if (fMax == null || fMax.compareTo(cur) < 0) {
                fMax = cur;
            }
        }
        out.println(fMax);
        for (int i = 0; i < n; i++) {
            out.print(start[i] + " ");
        }

    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("p1precfmax.in"));
        out = new PrintWriter("p1precfmax.out");
//        br = new BufferedReader(new FileReader("input.txt"));
//        out = new PrintWriter("output.txt");
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        solve();
        br.close();
        out.close();
    }


    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskF().run();
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
