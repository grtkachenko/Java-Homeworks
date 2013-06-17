import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TaskO {
    final double EPS = 1e-8;
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;

    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskO().run();
    }

    class Job implements Comparable<Job>{
        int index, p;
        Job(int index, int p) {
            this.index = index;
            this.p = p;
        }

        @Override
        public int compareTo(Job job) {
            return job.p - p;
        }
    }

    void solve() throws IOException {
        int n = nextInt(), m = nextInt();
        int[] p = new int[n];
        int[] t = new int[m];
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            p[i] = nextInt();
            jobs[i] = new Job(i, p[i]);
        }

        for (int i = 0; i < m; i++) {
            t[i] = nextInt();
        }

        Deque<Integer>[] machines = new ArrayDeque[m];
        int[] ans = new int[n];
        long[] startTime = new long[n];
        final double[] w = new double[m];

        TreeSet<Integer> ts = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (Math.abs(w[a] - w[b]) < EPS) {
                    return b - a;
                } else {
                    if (w[a] > w[b]) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
        for (int i = 0; i < m; i++) {
            w[i] = t[i];
            ts.add(i);
            machines[i] = new ArrayDeque<Integer>();
        }
        Arrays.sort(jobs);
        for (int i = 0; i < n; i++) {
            int index = ts.pollFirst();
            machines[index].addFirst(jobs[i].index);
            w[index] += t[index];
            ts.add(index);
        }
        long sum = 0;
        for (int i = 0; i < m; i++) {
            long cur = 0;
            for (int j : machines[i]) {
                ans[j] = i;
                System.out.println(j);
                startTime[j] = cur;
                cur += (long) p[j] * t[i];
                sum += cur;
            }
        }
        out.println(sum);
        for (int i = 0; i < n; i++) {
            out.println((ans[i] + 1) + " " + startTime[i]);
        }


    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("qsumci.in"));
        out = new PrintWriter("qsumci.out");
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
}
