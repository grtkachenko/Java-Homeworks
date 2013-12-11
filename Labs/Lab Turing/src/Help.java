import java.util.*;
import java.io.*;

public class Help {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        while (true) {
            String cur = in.nextLine();
            if (cur == null) break;
            cur = cur.toUpperCase();
            out.println("out.println(\"" + cur + "\");");
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("zero.out"));
            out = new PrintWriter(new File("help.out"));
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

        public String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] arg) {
        new Help().run();
    }
}