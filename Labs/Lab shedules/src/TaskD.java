import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;

    public static void main(String[] args) throws IOException {
        new TaskD().run();
    }

    void solve() throws IOException {
        int n = nextInt();
        final int[] p = new int[n];
        final int[] d = new int[n];
        Integer[] job = new Integer[n];
        for (int i = 0; i < n; i++) {
            job[i] = i;
            p[i] = nextInt();
            d[i] = nextInt();
        }
        Arrays.sort(job, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer integer2) {
                return d[integer] - d[integer2];
            }
        });
        long time = 0;
        TreeSet<Integer> ts = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer integer2) {
                if (p[integer] == p[integer2]) {
                    return integer - integer2;
                }
                return p[integer2] - p[integer];
            }
        });




        for (int i = 0; i < n; i++) {
            ts.add(job[i]);
            time += p[job[i]];
            if (time > d[job[i]]) {
                int j = ts.pollFirst();
                time -= p[j];
            }
        }

        ArrayList<Integer> byDeadline = new ArrayList<Integer>();

        for (int i : ts) {
            byDeadline.add(i);
        }

        Collections.sort(byDeadline, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer integer2) {
                return d[integer] - d[integer2];
            }
        });

        long[] ans = new long[n];
        Arrays.fill(ans, -1);

        time = 0;
        out.println(ts.size());
        for (int i : byDeadline) {
            ans[i] = time;
            time += p[i];
        }
        for (int i = 0; i < n; i++) {
            out.print(ans[i] + " ");
        }

    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("p1sumu.in"));
        out = new PrintWriter("p1sumu.out");
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
