import java.util.*;
import java.io.*;

public class Task2 {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        String bestString = null;
        double bestPerc = -1;

        String curName = in.nextLine();
        while (true) {
            int okChars = 0, totalChars = 0;

            while (true) {
                String curString = in.nextLine();
                if (curString == null || curString.charAt(0) == '>') {
                    if ((.0 + okChars) / totalChars > bestPerc) {
                        bestPerc = (.0 + okChars) / totalChars;
                        bestString = curName;
                    }
                    curName = curString;
                    break;
                }
                char[] stringArray = curString.toCharArray();
                totalChars += stringArray.length;
                for (char c : stringArray) {
                    if (c == 'C' || c == 'G') {
                        okChars++;
                    }
                }
            }

            if (curName == null) {
                break;
            }
        }

        assert bestString != null;
        out.println(bestString.substring(1));
        out.printf("%.6f", 100 * bestPerc);
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
        new Task2().run();
    }
}