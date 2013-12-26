import java.util.*;
import java.io.*;

public class Task13 {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        out.print(getReverse(in.next()));
    }

    String getReverse(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char toChar;
            switch (s.charAt(i)) {
                case 'A' :
                    toChar = 'T';
                    break;
                case 'T' :
                    toChar = 'A';
                    break;
                case 'G' :
                    toChar = 'C';
                    break;
                default:
                    toChar = 'G';
                    break;
            }
            res.append(toChar);
        }
        return res.toString();
    }

    public void run() {
        try {
            in = new FastScanner(new File("input.txt"));
            out = new PrintWriter(new File("output.txt"));
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
        new Task13().run();
    }
}