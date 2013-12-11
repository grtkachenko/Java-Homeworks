import java.util.*;
import java.io.*;

public class TaskH {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        out.println("start: very_start");
        out.println("accept: y");
        out.println("reject: n");
        out.println("blank: _");
        out.println("very_start 0 -> very_start 0 >");
        out.println("very_start 1 -> ok_start 1 >");
        out.println("very_start 2 -> ok_start 2 >");
        out.println("very_start _ -> y _ <");
        out.println("ok_start 0 -> ok_start 0 >");
        out.println("ok_start 1 -> ok_start 1 >");
        out.println("ok_start 2 -> ok_start 2 >");
        out.println("ok_start _ -> back_to_s _ <");
        out.println("s 0 -> s 0 >");
        out.println("s 1 -> b 1 >");
        out.println("s 2 -> a 2 >");
        out.println("s _ -> b1 _ <");
        out.println("a 0 -> a 0 >");
        out.println("a 1 -> b 1 >");
        out.println("a 2 -> a 2 >");
        out.println("a _ -> w0 _ <");
        out.println("b 0 -> b 0 >");
        out.println("b 1 -> a 1 >");
        out.println("b 2 -> b 2 >");
        out.println("b _ -> w1 _ <");
        out.println("w0 0 -> w0 0 <");
        out.println("w0 1 -> w0 1 <");
        out.println("w0 2 -> w0 2 <");
        out.println("w0 _ -> w00 _ <");
        out.println("w1 0 -> w1 0 <");
        out.println("w1 1 -> w1 1 <");
        out.println("w1 2 -> w1 2 <");
        out.println("w1 _ -> w11 _ <");
        out.println("w00 0 -> w00 0 <");
        out.println("w00 1 -> w00 1 <");
        out.println("w00 _ -> to_start 0 >");
        out.println("w11 0 -> w11 0 <");
        out.println("w11 1 -> w11 1 <");
        out.println("w11 _ -> to_start 1 >");
        out.println("to_start 0 -> to_start 0 >");
        out.println("to_start 1 -> to_start 1 >");
        out.println("to_start _ -> div_by_2 _ >");
        out.println("div_by_2 0 -> div_by_2 0 >");
        out.println("div_by_2 1 -> div_by_2_have 0 >");
        out.println("div_by_2 2 -> div_by_2 1 >");
        out.println("div_by_2 _ -> back_to_s _ <");
        out.println("div_by_2_have 0 -> div_by_2_have 1 >");
        out.println("div_by_2_have 1 -> div_by_2 2 >");
        out.println("div_by_2_have 2 -> div_by_2_have 2 >");
        out.println("div_by_2_have _ -> back_to_s _ <");
        out.println("back_to_s 0 -> back_to_s 0 <");
        out.println("back_to_s 1 -> back_to_s 1 <");
        out.println("back_to_s 2 -> back_to_s 2 <");
        out.println("back_to_s _ -> s _ >");
        out.println("b1 0 -> b1 _ <");
        out.println("b1 1 -> b1 _ <");
        out.println("b1 2 -> b1 _ <");
        out.println("b1 _ -> b2 _ <");
        out.println("b2 0 -> b2 0 <");
        out.println("b2 1 -> b2 1 <");
        out.println("b2 2 -> b2 2 <");
        out.println("b2 _ -> y _ >");
    }

    public void run() {
        try {
//            in = new FastScanner(new File("convertto2.in"));
            out = new PrintWriter(new File("convertto2.out"));
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
        new TaskH().run();
    }
}