import java.util.*;
import java.io.*;

public class TaskA {
    private PrintWriter out;

    public void solve() throws IOException {
        out.println("start: s");
        out.println("accept: y");
        out.println("reject: n");
        out.println("blank: B");

        out.println("s 0 -> a 0 >");
        out.println("s B -> y B ^");
        out.println("a 0 -> s 0 >");
        out.println("a B -> n B ^");
    }

    public void run() {
        try {
            out = new PrintWriter(new File("zero.out"));
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
        new TaskA().run();
    }
}