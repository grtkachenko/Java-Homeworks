import java.util.*;
import java.io.*;

public class TaskJ {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        out.println("2");
        out.println("S 0 _ -> S 0 > 0 >");
        out.println("S 1 _ -> S 1 > 1 >");
        out.println("S a _ -> AND a ^ _ <");
        out.println("S o _ -> OR o ^ _ <");
        out.println("S _ _ -> WRITE _ ^ _ <");
        out.println("AND a 0 -> FAIL a ^ _ <");
        out.println("AND a 1 -> OK a ^ _ <");
        out.println("FAIL a 0 -> S a > 0 >");
        out.println("FAIL a 1 -> S a > 0 >");
        out.println("OK a 0 -> S a > 0 >");
        out.println("OK a 1 -> S a > 1 >");
        out.println("OR o 0 -> FAIL o ^ _ <");
        out.println("OR o 1 -> OK o ^ _ <");
        out.println("FAIL o 0 -> S o > 0 >");
        out.println("FAIL o 1 -> S o > 1 >");
        out.println("OK o 0 -> S o > 1 >");
        out.println("OK o 1 -> S o > 1 >");
        out.println("WRITE _ 0 -> AC 0 ^ _ ^");
        out.println("WRITE _ 1 -> AC 1 ^ _ ^");

    }

    public void run() {
        try {
            out = new PrintWriter(new File("postfixlogic.out"));
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
        new TaskJ().run();
    }
}