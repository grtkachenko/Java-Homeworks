import java.io.*;
import java.util.*;

public class TaskC {
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;

    class Job implements Comparable<Job>{
        Item data;
        long p;
        long w;
        long wFirst;
        long pFirst;
        int index;
        Job pred = null;
        List<Job> sons = new ArrayList<Job>();

        Job(long p, int index) {
            this.p = pFirst = p;
            this.index = index;
            data = new Item(this);
        }

        @Override
        public int compareTo(Job job) {
            long cur = w * job.p - p * job.w;
            if (cur < 0) {
                return 1;
            }
            if (cur > 0) {
                return -1;
            } else {
                return index - job.index;
            }
        }

        void addJob(Job job) {
            data.data.add(job.data);
            w += job.w;
            p += job.p;
        }

        @Override
        public String toString() {
            return Integer.toString(index);
        }
    }

    class Item {
        boolean isLeaf;
        Job leafJob = null;
        List<Item> data = new ArrayList<Item>();

        Item() {}

        Item(Job leafJob) {
            Item item = new Item();
            item.isLeaf = true;
            item.leafJob = leafJob;
            data.add(item);
        }

        List<Job> getList() {
            if (isLeaf) {
                return Collections.singletonList(leafJob);
            }
            List<Job> res = new ArrayList<Job>();
            for (Item item : data) {
                res.addAll(item.getList());
            }
            return res;
        }
    }

    void solve() throws IOException {
        int n = nextInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(nextLong(), i);
        }

        for (int i = 0; i < n; i++) {
            jobs[i].w = jobs[i].wFirst = nextLong();

        }
        TreeSet<Job> ts = new TreeSet<Job>();
        for (int i = 0; i < n - 1; i++) {
            int x = nextInt() - 1, y = nextInt() - 1;
            jobs[x].pred = jobs[y];
            jobs[y].sons.add(jobs[x]);
        }

        for (int i = 0; i < n; i++) {
            if (jobs[i].pred == null) {
                jobs[i].w = -Integer.MAX_VALUE;
            }
            ts.add(jobs[i]);

        }

        while (ts.size() != 1) {
            Job job = ts.pollFirst();
            Job par = job.pred;
            for (Job son : job.sons) {
                son.pred = par;
                par.sons.add(son);
            }

            ts.remove(par);
            par.addJob(job);
            ts.add(par);

        }

        long curTime = 0;
        long ans = 0;
        long[] start = new long[n];
        for (Job job : ts.pollFirst().data.getList()) {
            start[job.index] = curTime;
            curTime += job.pFirst;
            ans += curTime * job.wFirst;
        }
        out.println(ans);
        for (long i : start) {
            out.print(i + " ");
        }
    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("p1outtreewc.in"));
        out = new PrintWriter("p1outtreewc.out");
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
        new TaskC().run();
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