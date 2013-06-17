import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class TaskH {
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;
    int curNumOfBlock;
    boolean[] was;

    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskH().run();
    }

    void topSort(Job v, List<Job> topSorted) {
        was[v.id] = true;
        for (Job u : v.successors) {
            if (!was[u.id]) {
                topSort(u, topSorted);
            }
        }
        topSorted.add(v);
    }

    List<Block> getBlocks(List<Job> jobs) {
        List<Block> res = new ArrayList<Block>();
        int i = 0;
        while (i < jobs.size()) {
            curNumOfBlock++;
            long curTime = jobs.get(i).r;
            Block curBlock = new Block();
            while (i < jobs.size() && jobs.get(i).r <= curTime) {
                curTime += jobs.get(i).p;
                curBlock.jobs.add(jobs.get(i));
                jobs.get(i).numOfBlock = curNumOfBlock;
                i++;
            }
            res.add(curBlock);
            curBlock.t = curTime;
        }
        return res;
    }

    long decompose(List<Job> jobs, Job fillJob, long timeEnd) {
        if (fillJob != null) {
            long start = fillJob.r, end = !jobs.isEmpty() ? jobs.get(0).r : timeEnd;
            if (start < end) {
                fillJob.ans.add(start);
                fillJob.ans.add(end);
            }
        }
        if (jobs.size() <= 1) {
            if (jobs.isEmpty()) {
                return Long.MIN_VALUE;
            } else {
                if (fillJob != null) {
                    long start = jobs.get(0).r + jobs.get(0).p, end = timeEnd;
                    if (start < end) {
                        fillJob.ans.add(start);
                        fillJob.ans.add(end);
                    }
                }
                jobs.get(0).ans.add(jobs.get(0).r);
                jobs.get(0).ans.add(jobs.get(0).r + jobs.get(0).p);
                return jobs.get(0).polynom.calc(jobs.get(0).r + jobs.get(0).p);
            }
        } else {

            List<Block> blocks = getBlocks(jobs);
            long res = 0;
            for (int i = 0; i < blocks.size(); i++) {
                Block block = blocks.get(i);
                Job l = null;
                long curF = 0;
                for (Job job : block.jobs) {
                    boolean okJob = true;
                    for (Job x : job.successors) {
                        if (x.numOfBlock == job.numOfBlock) {
                            okJob = false;
                        }
                    }
                    if (okJob == false) {
                        continue;
                    }
                    if (l == null || job.polynom.calc(block.t) < curF) {
                        curF = job.polynom.calc(block.t);
                        l = job;
                    }
                }
                block.jobs.remove(l);
                res = Math.max(curF, Math.max(decompose(block.jobs, l, block.t), res));
                if (fillJob != null) {
                    long start = block.t, end = i + 1 < blocks.size() ? blocks.get(i + 1).jobs.get(0).r : timeEnd;
                    if (start < end) {
                        fillJob.ans.add(start);
                        fillJob.ans.add(end);
                    }
                }
            }
            return res;
        }
    }

    public void solve() throws IOException {
        int n = nextInt();
        Job[] jobs = new Job[n];
        long[] p = new long[n];
        long[] r = new long[n];

        for (int i = 0; i < n; i++) {
            p[i] = nextLong();
        }

        for (int i = 0; i < n; i++) {
            r[i] = nextLong();
        }

        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(p[i], r[i], i);
        }
        int m = nextInt();
        for (int i = 0; i < m; i++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            jobs[u].successors.add(jobs[v]);
        }
        for (int i = 0; i < n; i++) {
            jobs[i].polynom = new Polynom(nextLong(), nextLong(), nextLong());
        }

        was = new boolean[n];
        List<Job> topSorted = new ArrayList<Job>();
        for (int i = 0; i < n; i++) {
            if (!was[i]) {
                topSort(jobs[i], topSorted);
            }
        }
        Collections.reverse(topSorted);
        for (Job u : topSorted) {
            for (Job v : u.successors) {
                v.r = Math.max(v.r, u.r + u.p);
            }
        }

        List<Job> help = new ArrayList<Job>();
        for (Job job : jobs) {
            help.add(job);
        }
        Collections.sort(help);
        out.println(decompose(help, null, 0));
        for (Job curJob : jobs) {
            out.print((curJob.ans.size() / 2) + " ");
            for (int i = 0; i < curJob.ans.size(); i++) {
                out.print(curJob.ans.get(i) + " ");
            }
            out.println();
        }

    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("p1precpmtnrifmax.in"));
        out = new PrintWriter("p1precpmtnrifmax.out");
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

    class Polynom {
        long a;
        long b;
        long c;

        Polynom(long a, long b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        long calc(long val) {
            return a * val * val + b * val + c;
        }
    }

    class Job implements Comparable<Job> {
        int id;
        int numOfBlock;
        long p;
        long r;
        List<Long> ans = new ArrayList<Long>();
        List<Job> successors = new ArrayList<Job>();
        Polynom polynom;

        Job(long p, long r, int id) {
            this.id = id;
            this.p = p;
            this.r = r;
        }

        @Override
        public int compareTo(Job job) {
            if (r == job.r) {
                return id - job.id;
            }

            if (r > job.r) {
                return 1;
            } else {
                return -1;
            }
        }

    }

    class Block {
        List<Job> jobs = new ArrayList<Job>();
        long s;
        long t;

        Block() {
        }

        ;

    }
}
