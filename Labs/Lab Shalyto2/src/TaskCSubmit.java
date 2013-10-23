import java.io.*;
import java.util.StringTokenizer;

public class TaskCSubmit {
    private final static String[] ans = {
            "4 1 R M\n2 3 R M\n4 4 L M\n2 2 M M\n", "1 1 R M\n", "3 3 R M\n3 3 L M\n2 1 M M\n", "4 4 R M\n4 2 L M\n1 2 M M\n3 4 M M\n", "3 2 M M\n1 1 R M\n2 3 M L\n", "4 2 M M\n5 3 M M\n5 1 M M\n2 1 R M\n3 1 L M\n", "4 4 L M\n4 3 M M\n1 2 M M\n4 3 R M\n", "5 5 R M\n4 2 M M\n1 2 R M\n3 3 R M\n1 2 M M\n", "3 5 R M\n1 5 M M\n6 6 L M\n2 1 R M\n4 6 M M\n4 2 L M\n", "3 2 L M\n3 5 R M\n5 5 M M\n1 5 R M\n6 4 R M\n2 1 R M\n"
    };
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] arg) {
        new TaskCSubmit().run();
    }

    public void solve() throws IOException {
        out.print(ans[in.nextInt() - 1]);
    }

    public void run() {
        try {
            in = new FastScanner(new File("artificial.in"));
            out = new PrintWriter(new File("artificial.out"));
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

        public String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}