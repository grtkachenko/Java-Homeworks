import java.util.*;
import java.io.*;

public class Test {
    private FastScanner in;

    public void solve() throws IOException {
        double res = 1;
        int
        for (int i = 0; i < 25; i++) {
            res *= 0.99;
        }
        System.out.println(1 - Math.pow(Math.E, -4) );
    }

    public void run() {
        try {
            in = new FastScanner();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class FastScanner {
        private BufferedReader br;
        private StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
        new Test().run();
    }
}