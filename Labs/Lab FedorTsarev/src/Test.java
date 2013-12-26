import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Test {
    private FastScanner in;

    public void solve() throws IOException {
        long startTime, endTime, k;
        for (int j = 0; j < 5; j++) {
            startTime = System.currentTimeMillis();
            for (int i = 0; i < 1e9; i++) {
                i / 2;
            }
            endTime   = System.currentTimeMillis();
            System.out.print((endTime - startTime) + " ");

            startTime = System.currentTimeMillis();
            for (int i = 0; i < 1e9; i++) {
                i >> 2;
            }
            endTime   = System.currentTimeMillis();


            System.out.println(endTime - startTime);
        }
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