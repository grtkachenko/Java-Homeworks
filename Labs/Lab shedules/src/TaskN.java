import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TaskN {
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;

    public static void main(String[] args) throws IOException {
        new TaskN().run();
    }

    void solve() throws IOException {
        int n = nextInt();
        int[][] p = new int[n][2];
        Integer[] jobs = new Integer[n];
        final int[] minp = new int[n];
        for (int i = 0; i < n; i++) {
            minp[i] = Integer.MAX_VALUE;
            jobs[i] = i;
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < n; i++) {
                p[i][j] = nextInt();
                minp[i] = Math.min(minp[i], p[i][j]);
                jobs[i] = i;
            }

        }
        Deque<Integer> left = new ArrayDeque<Integer>();
        Deque<Integer> right = new ArrayDeque<Integer>();
        Arrays.sort(jobs, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return minp[a] - minp[b];
            }
        });
        for (int i = 0; i < n; i++) {
            if (minp[jobs[i]] == p[jobs[i]][0]) {
                left.addLast(jobs[i]);
            } else {
                right.addFirst(jobs[i]);
            }
        }
        left.addAll(right);
        long[][] jobStart = new long[n][2];
        long curStart = 0;
        for (int i : left) {
            jobStart[i][0] = curStart;
            curStart += p[i][0];
        }
        curStart = 0;
        for (int i : left) {
            curStart = Math.max(curStart, jobStart[i][0] + p[i][0]);
            jobStart[i][1] = curStart;
            curStart += p[i][1];
        }
        out.println(jobStart[left.getLast()][1] + p[left.getLast()][1]);
        for (int i : left) {
            out.print((i + 1) + " ");
        }
        out.println();
        for (int i : left) {
            out.print((i + 1) + " ");
        }
    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("f2cmax.in"));
        out = new PrintWriter("f2cmax.out");
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
