import java.util.*;
import java.io.*;

public class Task1 {
    private FastScanner in;

    public void solve() throws IOException {
        String s = in.next();
        int[] ans = new int[4];
        for (char c : s.toCharArray()) {
            switch (c) {
                case 'A' :
                    ans[0]++;
                    break;
                case 'C' :
                    ans[1]++;
                    break;
                case 'G' :
                    ans[2]++;
                    break;
                default:
                    ans[3]++;
                    break;
            }
        }
        for (int curAns : ans) {
            System.out.print(curAns + " ");
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
        new Task1().run();
    }
}