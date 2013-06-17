import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TaskH1 {
    FastScanner in;
    PrintWriter out;

    int n, m;
    int curNumOfBlock;
    ArrayList<Integer>[] g;
    boolean[] was;
    Job[] jobs;
    ArrayList<Long>[] ans;
    ArrayList<Integer> topSorted = new ArrayList<Integer>();

    public static void main(String[] arg) {
        new TaskH1().run();
    }

    void topSort(int v) {
        was[v] = true;
        for (int x : g[v]) {
            if (!was[x]) {
                topSort(x);
            }
        }
        topSorted.add(v);
    }

    void modifyR(int v) {
        was[v] = true;
        for (int x : g[v]) {
            jobs[x].r = Math.max(jobs[x].r, jobs[v].r + jobs[v].p);
        }
    }

    ArrayList<ArrayList<Job>> divideBlocks(ArrayList<Job> S) {
        ArrayList<ArrayList<Job>> res = new ArrayList<ArrayList<Job>>();
        int i = 0;
        while (i < S.size()) {
            long curTime = S.get(i).r;
            ArrayList<Job> curBlock = new ArrayList<Job>();
            curNumOfBlock++;
            while (i < S.size() && S.get(i).r <= curTime) {
                curBlock.add(S.get(i));
                S.get(i).numOfBlock = curNumOfBlock;
                curTime += S.get(i).p;
                i++;
            }
            res.add(curBlock);
        }
        return res;
    }

    long T(ArrayList<Job> block) {
        long sumP = 0, minR = Long.MAX_VALUE;
        for (Job job : block) {
            minR = Math.min(minR, job.r);
            sumP += job.p;
        }
        return minR + sumP;
    }

    long decompose(ArrayList<Job> S, Job fillJob, long timeEnd) {
        if (fillJob != null) {
            long start = fillJob.r;
            long end = timeEnd;
            if (S.size() != 0) {
                end = S.get(0).r;
            }
            if (start < end) {
                ans[fillJob.num].add(start);
                ans[fillJob.num].add(end);
            }
        }
        if (S.size() == 0) {
            return Long.MIN_VALUE;
        }
        if (S.size() == 1) {
            if (fillJob != null) {
                long start = S.get(0).r + S.get(0).p;
                long end = timeEnd;
                if (start < end) {
                    ans[fillJob.num].add(start);
                    ans[fillJob.num].add(end);
                }
            }
            Job lastJob = S.get(0);
            ans[lastJob.num].add(lastJob.r);
            ans[lastJob.num].add(lastJob.r + lastJob.p);
            return S.get(0).calc();
        }
        ArrayList<ArrayList<Job>> blocks = divideBlocks(S);
        long res = 0;
        for (int i = 0; i < blocks.size(); i++) {
            ArrayList<Job> block = blocks.get(i);
            Job l = null;
            long best = 0;
            long Tblock = T(block);
            for (Job job : block) {
                boolean good = true;
                for (int x : g[job.num]) {
                    if (jobs[x].numOfBlock == job.numOfBlock) {
                        good = false;
                        break;
                    }
                }
                if (!good) {
                    continue;
                }
                if (l == null || job.calc(Tblock) < best) {
                    best = job.calc(Tblock);
                    l = job;
                }
            }
            block.remove(l);
            long h = decompose(block, l, Tblock);
            res = Math.max(res, Math.max(h, best));
            if (fillJob != null) {
                long start = Tblock;
                long end = timeEnd;
                if (i != blocks.size() - 1) {
                    end = blocks.get(i + 1).get(0).r;
                }
                if (start < end) {
                    ans[fillJob.num].add(start);
                    ans[fillJob.num].add(end);
                }
            }
        }
        return res;
    }

    public void solve() throws IOException {
        n = in.nextInt();
        jobs = new Job[n];
        ans = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new ArrayList<Long>();
        }
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(i);
            jobs[i].p = in.nextLong();
        }
        for (int i = 0; i < n; i++) {
            jobs[i].r = in.nextLong();
        }
        m = in.nextInt();
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            g[a].add(b);
        }
        for (int i = 0; i < n; i++) {
            jobs[i].a = in.nextLong();
            jobs[i].b = in.nextLong();
            jobs[i].c = in.nextLong();
        }


        was = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!was[i]) {
                topSort(i);
            }
        }
        was = new boolean[n];
        Collections.reverse(topSorted);
        for (int x : topSorted) {
            if (!was[x]) {
                modifyR(x);
            }
        }

        ArrayList<Job> S = new ArrayList<Job>();
        for (Job job : jobs) {
            S.add(job);
        }
        Collections.sort(S);
        long fmax = decompose(S, null, 0);
        out.println(fmax);
        for (ArrayList<Long> curJob : ans) {
            out.print((curJob.size() / 2) + " ");
            for (long x : curJob) {
                out.print(x + " ");
            }
            out.println();
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("input.txt"));
            out = new PrintWriter(new File("output.txt"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Job implements Comparable<Job> {
        int num, numOfBlock;
        long p, r, a, b, c;

        public Job(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Job o) {
            if (r - o.r == 0) return num - o.num;
            if (r > o.r) {
                return 1;
            } else {
                return -1;
            }
        }

        long calc() {
            return calc(r + p);
        }

        long calc(long x) {
            return a * x * x + b * x + c;
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
}

