import java.util.*;
import java.io.*;

public class TaskA {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        for (int i = 0; i < m; i++) {
            double ans = 1;
            String a = in.next(), b = in.next();
            for (int j = 0; j < n; j++) {
                ans /= n;
                if (a.charAt(j) == b.charAt(j)) {
                    ans *= (n - 1);
                }
            }
            out.println(ans);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("mutation.in"));
            out = new PrintWriter(new File("mutation.out"));
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class FastScanner {
        private BufferedReader br;
        private StringTokenizer st;

        public FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] arg) {
        new TaskA().run();
    }
}