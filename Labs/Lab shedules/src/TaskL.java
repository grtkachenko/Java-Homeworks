import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TaskL {
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;
    class Job implements Comparable<Job>{
        int d, dnew, index;
        Job son = null;
        ArrayList<Job> pr = new ArrayList<Job>();

        Job(int d, int index) {
            this.d = d;
            this.dnew = d;
            this.index = index;
        }

        @Override
        public int compareTo(Job job) {
            if (dnew == job.dnew) {
                return index - job.index;
            }
            return dnew - job.dnew;
        }
    }


    void solve() throws IOException {
        int n = nextInt(), m = nextInt();
        int[]d = new int[n];
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            d[i] = nextInt();
            jobs[i] = new Job(d[i], i);
        }
        for (int i = 0; i < n - 1; i++) {
            int x = nextInt() - 1, y = nextInt() - 1;
            jobs[x].son = jobs[y];
            jobs[y].pr.add(jobs[x]);
        }
        TreeSet<Job> ts = new TreeSet<Job>();
        for (int i = 0; i < n; i++) {
            if (jobs[i].son == null) {
                ts.add(jobs[i]);
            }
        }

        while (!ts.isEmpty()) {
            Job cur = ts.pollFirst();
            for (Job job : cur.pr) {
                job.dnew = Math.min(job.dnew, cur.dnew - 1);
                ts.add(job);
            }
        }

        Arrays.sort(jobs);

        int f = 0;
        int[] numberOfJobs = new int[n + 1];
        int[] start = new int[n + 1];
        int[] r = new int[n + 1];
        for (int it = 0; it < n; it++) {
            int i = jobs[it].index;
            int time = Math.max(f, r[i]);
            start[i] = time;
            numberOfJobs[time]++;
            if (numberOfJobs[time] == m) {
                f = time + 1;
            }
            if (jobs[it].son != null) {
                r[jobs[it].son.index] = Math.max(r[jobs[it].son.index], time + 1);
            }
        }

        int ansValue = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            ansValue = Math.max(ansValue, start[i] - d[i] + 1);
        }
        out.println(ansValue);
        for (int i = 0; i < n; i++) {
            out.print(start[i] + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskL().run();
    }


    void run() throws IOException {
//        br = new BufferedReader(new FileReader("pintreep1l.in"));
//        out = new PrintWriter("pintreep1l.out");
        br = new BufferedReader(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
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
