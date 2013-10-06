import java.util.*;
import java.io.*;

public class TaskB {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        int m = in.nextInt(), n = in.nextInt();
        String[] set = new String[m];
        for (int i = 0; i < m; i++) {
            set[i] = in.next();
        }
        String target = in.next();
        boolean[] ans = new boolean[3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                boolean ok = true;
                for (int k = 0; k < n; k++) {
                    if (set[i].charAt(k) != target.charAt(k) && set[j].charAt(k) != target.charAt(k)) {
                        ok = false;
                    }
                }
                ans[2] |= ok;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int maxPrefixLen = 0, maxSuffixLen = 0;
                while (maxPrefixLen < n && set[i].charAt(maxPrefixLen) == target.charAt(maxPrefixLen)) {
                    maxPrefixLen++;
                }
                while (maxSuffixLen < n && set[j].charAt(n - maxSuffixLen - 1) == target.charAt(n - maxSuffixLen - 1)) {
                    maxSuffixLen++;
                }
                if (maxPrefixLen + maxSuffixLen >= n) {
                    ans[0] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int maxPrefixLen = 0, maxSuffixLen = 0, maxPrefixLenSecond = 0;
                while (maxPrefixLen < n && set[i].charAt(maxPrefixLen) == target.charAt(maxPrefixLen)) {
                    maxPrefixLen++;
                }
                while (maxPrefixLenSecond + maxPrefixLen < n && set[j].charAt(maxPrefixLenSecond + maxPrefixLen) == target.charAt(maxPrefixLenSecond + maxPrefixLen)) {
                    maxPrefixLenSecond++;
                }
                while (maxSuffixLen < n && set[i].charAt(n - maxSuffixLen - 1) == target.charAt(n - maxSuffixLen - 1)) {
                    maxSuffixLen++;
                }
                if (maxPrefixLen + maxPrefixLenSecond + maxSuffixLen >= n) {
                    ans[1] = true;
                }
            }
        }
        printAns(ans);
    }


    void printAns(boolean[] ans) {
        for (int i = 0; i < ans.length; i++) {
            out.println(!ans[i] ? "NO" : "YES");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("crossover.in"));
            out = new PrintWriter(new File("crossover.out"));
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
        new TaskB().run();
    }
}