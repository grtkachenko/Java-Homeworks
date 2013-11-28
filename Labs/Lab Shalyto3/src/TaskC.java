import java.util.*;
import java.io.*;

public class TaskC {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        int k = in.nextInt(), n = in.nextInt();
        Map<String, Integer> ans = new TreeMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j <= s.length() - k; j++) {
                String cur = s.substring(j, j + k);
                if (!ans.containsKey(cur)) {
                    ans.put(cur, 0);
                }
                ans.put(cur, ans.get(cur) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : ans.entrySet()) {
            out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("count-entries.in"));
            out = new PrintWriter(new File("count-entries.out"));
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
        new TaskC().run();
    }
}