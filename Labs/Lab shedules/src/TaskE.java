import java.io.*;
import java.util.*;

public class TaskE {
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;
    private class Job implements Comparable<Job>{
        private int index;
        private int weight;
        private int deadline;

        Job(int index, int deadline, int weight) {
            this.index = index;
            this.weight = weight;
            this.deadline = deadline;
        }
        Job(Job job) {
            this.index = job.index;
            this.weight = job.weight;
            this.deadline = job.deadline;
        }


        @Override
        public int compareTo(Job job) {
            if (job.weight != weight) {
                return weight - job.weight;
            }
            return job.index - index;
        }
    }
    void solve() throws IOException {
        int n = nextInt();
        Job[] data = new Job[n];
        Job[] restore = new Job[n];
        for (int i = 0; i < n; i++) {
            data[i] = new Job(i, nextInt(), nextInt());
            restore[i] = new Job(data[i]);
        }

        Arrays.sort(data, new Comparator<Job>() {
            @Override
            public int compare(Job a, Job b) {
                return a.deadline - b.deadline;
            }
        });
        long time = 0;
        TreeSet<Job> ts = new TreeSet<Job>();
        long[] ans = new long[n];
        Arrays.fill(ans, Long.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (data[i].deadline > time) {
                ans[data[i].index] = time;
                time++;
                ts.add(data[i]);
            } else {
                if (!ts.isEmpty() && ts.first().weight < data[i].weight) {
                    ans[data[i].index] = ans[ts.first().index];
                    ans[ts.first().index] = Long.MAX_VALUE;
                    ts.remove(ts.first());
                    ts.add(data[i]);
                }
            }
        }
        long totalFail = 0;
        long curNotUsed = n;
        for (int i = 0; i < n; i++) {
            if (ans[i] == Long.MAX_VALUE) {
                ans[i] = curNotUsed++;
                totalFail += restore[i].weight;
            }
        }
        out.println(totalFail);
        for (long i : ans) {
            out.print(i + " ");
        }
    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("p1sumwu.in"));
        out = new PrintWriter("p1sumwu.out");
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
        new TaskE().run();
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
