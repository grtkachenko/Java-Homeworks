import java.util.*;
import java.io.*;

public class Task2 {
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

        ArrayList<Integer>[][] a = new ArrayList[n][MAX_CHAR];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < MAX_CHAR; j++) {
                a[i][j] = new ArrayList<Integer>();
            }
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1, c = in.nextChar() - 'a';
            a[from][c].add(to);
        }
        boolean[][] was = new boolean[n][word.length()];
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        ArrayDeque<Integer> depth = new ArrayDeque<Integer>();

        queue.add(0);
        depth.add(0);
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst(), dep = depth.pollFirst();

            if (dep == word.length()) {
                if (admit.contains(cur)) {
                    out.println("Accepts");
                    return;
                } else {
                    continue;
                }
            }
            if (was[cur][dep]) {
                continue;
            } else {
                was[cur][dep] = true;
            }
            for (int i : a[cur][word.charAt(dep) - 'a']) {
                queue.addLast(i);
                depth.addLast(dep + 1);
            }

        }
        out.println("Rejects");
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem2.in"));
            out = new PrintWriter(new File("problem2.out"));
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
        new Task2().run();
    }
}