import java.util.*;
import java.io.*;

public class Task1 {
    private FastScanner in;
    private PrintWriter out;
    private final int MAX_CHAR = 30;

    public void solve() throws IOException {
        String word = in.next();
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        Set<Integer> admit = new HashSet<Integer>();
        for (int i = 0; i < k; i++) {
            admit.add(in.nextInt() - 1);
        }
        int[][] a = new int[n][MAX_CHAR];
        for (int i = 0; i < n; i++) {
            Arrays.fill(a[i], -1);
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1, c = in.nextChar() - 'a';
            a[from][c] = to;
        }
        int cur = 0;
        for (int i = 0; i < word.length(); i++) {
            if (a[cur][word.charAt(i) - 'a'] != -1) {
                cur =  a[cur][word.charAt(i) - 'a'];
            } else {
                out.println("Rejects");
                return;
            }
        }
        if (admit.contains(cur)) {
            out.println("Accepts");
        } else {
            out.println("Rejects");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem1.in"));
            out = new PrintWriter(new File("problem1.out"));
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

        public char nextChar() {
            return next().charAt(0);
        }


    }

    public static void main(String[] arg) {
        new Task1().run();
    }
}