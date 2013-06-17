import java.util.*;
import java.io.*;

public class TaskI1 {
    FastScanner in;
    PrintWriter out;

    class Job implements Comparable<Job> {
        int num, d;

        public Job(int num, int d) {
            super();
            this.num = num;
            this.d = d;
        }

        @Override
        public int compareTo(Job o) {
            if (d != o.d) return d - o.d;
            return num - o.num;
        }
    }

    int n, m, v;
    int maxDeadline;
    int lateJobTime = 10000;
    Job[] jobs;

    int[][] colors;
    int[][] ans;

    int colorNotExist(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    int[][] row, col;

    void colorize() {
        row = new int[ans.length][m];
        col = new int[maxDeadline + 1][m];
        for (int i = 0; i < ans.length; i++) {
            Arrays.fill(row[i], -1);
        }
        for (int i = 0; i < maxDeadline + 1; i++) {
            Arrays.fill(col[i], -1);
        }
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j <= maxDeadline; j++) {
                if (colors[i][j] == -1) {
                    int c = colorNotExist(col[j]);
                    col[j][c] = i;
                    colors[i][j] = c + 1;
                    if (row[i][c] != -1) {
                        int c1 = colorNotExist(row[i]);
                        conflict(i, j, c, c1);
                    } else {
                        row[i][c] = j;
                    }
                }
            }
        }
    }

    void conflict(int i, int j, int c, int c1) {
        int j1 = row[i][c];
        row[i][c] = j;
        if (j1 != -1 && j1 != j) {
            colors[i][j1] = c1 + 1;
            col[j1][c] = -1;
            row[i][c1] = j1;
        } else {
            return;
        }
        int i1 = col[j1][c1];
        col[j1][c1] = i;
        if (i1 != -1 && i1 != i) {
            colors[i1][j1] = c + 1;
            row[i1][c1] = -1;
            col[j1][c] = i1;
            conflict(i1, j1, c, c1);
        }
    }

    Set<Job>[] h;

    int ok(int countJobs) {
        if (countJobs == 0) {
            return 0;
        }
        h = new HashSet[maxDeadline + 1];
        for (int i = 0; i < h.length; i++) {
            h[i] = new HashSet<Job>();
        }
        int[] hsize = new int[maxDeadline + 1];
        for (int i = n - 1; i >= 0; i--) {
            if (jobs[i].d < m) {
                return n - i - 1;
            }

            //preCheck
            for (int j = jobs[i].d - m + 1; j <= jobs[i].d; j++) {
                hsize[j]++;
                int p = j;
                while (p > 1 && hsize[p] > m) {
                    hsize[p]--;
                    hsize[p - 1]++;
                    p--;
                }
                if (hsize[1] > m) {
                    return n - i - 1;
                }
            }

            for (int j = jobs[i].d - m + 1; j <= jobs[i].d; j++) {
                h[j].add(jobs[i]);
                int p = j;
                while (p > 1 && h[p].size() > m) {
                    for (Job job : h[p]) {
                        if (!h[p - 1].contains(job)) {
                            h[p].remove(job);
                            h[p - 1].add(job);
                            break;
                        }
                    }
                    p--;
                }
            }
        }
        return n;
    }

    public void solve() throws IOException {
        long start = System.currentTimeMillis();
        n = in.nextInt();
        m = in.nextInt();
        v = in.nextInt();
        ans = new int[n][m];
        jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(i, in.nextInt());
        }
        Arrays.sort(jobs);
        maxDeadline = jobs[jobs.length - 1].d;

        int l = ok(n);

        colors = new int[l][maxDeadline + 1];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j <= maxDeadline; j++) {
                if (h[j].contains(jobs[n - l  + i])) {
                    colors[i][j] = -1;
                }
            }
        }
        colorize();

        out.println((n - l) * v);
        for (int i = 0; i < l; i++) {
            for (int time = 0; time <= maxDeadline; time++) {
                if (colors[i][time] != 0) {
                    ans[jobs[i + n - l].num][colors[i][time] - 1] = time;
                }
            }
        }
        for (int i = 0; i < n - l; i++) {
            for (int j = 0; j < m; j++) {
                ans[jobs[i].num][j] = lateJobTime++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.print(ans[i][j] + " ");
            }
            out.println();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public void run() {
        try {
            in = new FastScanner(new File("input.txt"));
            out = new PrintWriter(new File("output.txt"));

            // long time = System.currentTimeMillis();
            solve();
            // System.out.println(System.currentTimeMillis() - time);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        boolean hasNext() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String s = br.readLine();
                    if (s == null) {
                        return false;
                    }
                    st = new StringTokenizer(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(String[] arg) {
        new TaskI1().run();
    }
}