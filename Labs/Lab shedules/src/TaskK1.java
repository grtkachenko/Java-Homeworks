//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.*;
//
//public class TaskK {
//    BufferedReader br;
//    PrintWriter out;
//    StringTokenizer stok;
//
//    public static void main(String[] args) throws IOException {
//        // Locale.setDefault(Locale.US);
//        new TaskK().run();
//    }
//
//    class Job implements Comparable<Job> {
//        int n = 0, index, deadline, dnew;
//
//        List<Job> suc = new ArrayList<Job>();
//
//        Job(int index, int deadline) {
//            this.deadline = deadline;
//            this.dnew = deadline;
//            this.index = index;
//        }
//
//        @Override
//        public int compareTo(Job job) {
//            if (dnew == job.dnew) {
//                return index - job.index;
//            }
//            return dnew - job.dnew;
//        }
//    }
//
//    void solve() throws IOException {
//        int n = nextInt();
//        Job[] jobs = new Job[n];
//        Job[] nonSort = new Job[n];
//
//        boolean[][] a = new boolean[n][n];
//        for (int i = 0; i < n; i++) {
//            int deadline = nextInt();
//            jobs[i] = new Job(i, deadline);
//            nonSort[i] = new Job(i, deadline);
//
//        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                a[i][j] = nextInt() == 1;
//                if (a[i][j]) {
//                    jobs[i].suc.add(jobs[j]);
//                    jobs[i].n++;
//                    nonSort[i].suc.add(jobs[j]);
//                    nonSort[i].n++;
//
//                }
//            }
//        }
//        TreeSet<Job> ts = new TreeSet<Job>(new Comparator<Job>() {
//            @Override
//            public int compare(Job a, Job b) {
//                if (a.n == b.n) {
//                    return a.index - b.index;
//                }
//                return a.n - b.n;
//            }
//        });
//        TreeSet<Job> l = new TreeSet<Job>();
//        for (int i = 0; i < n; i++) {
//            ts.add(jobs[i]);
//            l.add(jobs[i]);
//        }
//        Arrays.sort(jobs);
//        while (!ts.isEmpty()) {
//            Job cur = ts.pollFirst();
//            int count = 1;
//            for (Job son : cur.suc) {
//                cur.dnew = Math.min(cur.dnew, son.dnew - count / 2);
//            }
//            l.remove(cur);
//            l.add(cur);
//            for (int j = 0; j < n; j++) {
//                if (a[j][cur.index]) {
//                    ts.remove(nonSort[j]);
//                    nonSort[j].n--;
//                    ts.add(nonSort[j]);
//                }
//            }
//        }
//
//        int[] v = new int[n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                v[i] += a[i][j] ? 1 : 0;
//            }
//        }
//        int time = 0;
//        int[] start = new int[n];
//        TreeSet<Job> m = new TreeSet<Job>();
//        while (!l.isEmpty()) {
//            Job first = null;
//            for (Job cur : l) {
//                if (v[cur.index] == 0) {
//                    first = cur;
//                    break;
//                }
//            }
//            if (m.size() == 2 || first == null) {
//                time++;
//                while (!m.isEmpty()) {
//                    Job currentFromM = m.pollFirst();
//                    for (int j = 0; j < n; j++) {
//                        if (a[currentFromM.index][j]) {
//                            v[j]--;
//                        }
//                    }
//                }
//            }
//            for (Job cur : l) {
//                if (v[cur.index] == 0) {
//                    first = cur;
//                    break;
//                }
//            }
//            start[first.index] = time;
//            m.add(first);
//            l.remove(first);
//        }
//
//
//
//    }
//
//    void run() throws IOException {
////        br = new BufferedReader(new FileReader("p2precp1lmax.in"));
////        out = new PrintWriter("p2precp1lmax.out");
//        br = new BufferedReader(new FileReader("input.txt"));
//        out = new PrintWriter("output.txt");
////        br = new BufferedReader(new InputStreamReader(System.in));
////        out = new PrintWriter(System.out);
//        solve();
//        br.close();
//        out.close();
//    }
//
//    String nextToken() throws IOException {
//        while (stok == null || !stok.hasMoreTokens()) {
//            String s = br.readLine();
//            if (s == null) {
//                return null;
//            }
//            stok = new StringTokenizer(s);
//        }
//        return stok.nextToken();
//    }
//
//    int nextInt() throws IOException {
//        return Integer.parseInt(nextToken());
//    }
//
//    long nextLong() throws IOException {
//        return Long.parseLong(nextToken());
//    }
//
//    double nextDouble() throws IOException {
//        return Double.parseDouble(nextToken());
//    }
//
//    char nextChar() throws IOException {
//        return (char) (br.read());
//    }
//
//    String nextLine() throws IOException {
//        return br.readLine();
//    }
//
//
//}
