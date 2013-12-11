import java.util.*;
import java.io.*;

public class TaskF {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        out.println("start: s");
        out.println("accept: y");
        out.println("reject: n");
        out.println("blank: _");

        out.println("s 0 -> s0 _ >");
        out.println("s 1 -> s1 _ >");
        out.println("s _ -> y _ >");

        out.println("s0 0 -> s0 0 >");
        out.println("s0 1 -> s0 1 >");
        out.println("s0 _ -> w0 _ >");

        out.println("s1 0 -> s1 0 >");
        out.println("s1 1 -> s1 1 >");
        out.println("s1 _ -> w1 _ >");

        out.println("w0 0 -> w0 0 >");
        out.println("w0 1 -> w1 0 >");
        out.println("w0 _ -> b1 0 <");

        out.println("w1 0 -> w0 1 >");
        out.println("w1 1 -> w1 1 >");
        out.println("w1 _ -> b1 1 <");

        out.println("b1 0 -> b1 0 <");
        out.println("b1 1 -> b1 1 <");
        out.println("b1 _ -> b2 _ <");

        out.println("b2 0 -> b2 0 <");
        out.println("b2 1 -> b2 1 <");
        out.println("b2 _ -> s _ >");
    }

    public void run() {
        try {
//            in = new FastScanner(new File("reverse.in"));
            out = new PrintWriter(new File("reverse.out"));
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
        new TaskF().run();
    }
}