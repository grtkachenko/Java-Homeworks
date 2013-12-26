import java.util.*;
import java.io.*;

public class Task12 {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        String curName = in.nextLine();
        int[][] ans = null;
        char[] chars = {'A', 'C', 'G', 'T'};

        while (true) {
            String curS = "";
            while (true) {
                String curString = in.nextLine();
                if (curString == null || curString.charAt(0) == '>') {
                    char[] stringArray = curS.toCharArray();
                    if (ans == null) {
                        ans = new int[stringArray.length][4];
                    }
                    for (int i = 0; i < stringArray.length; i++) {
                        char c = stringArray[i];
                        switch (c) {
                            case 'A' :
                                ans[i][0]++;
                                break;
                            case 'C' :
                                ans[i][1]++;
                                break;
                            case 'G' :
                                ans[i][2]++;
                                break;
                            default:
                                ans[i][3]++;
                                break;

                        }
                    }

                    curName = curString;
                    break;
                }
                curS += curString;
            }

            if (curName == null) {
                break;
            }
        }
        String cons = "";
        for (int i = 0; i < ans.length; i++) {
            int max = -1;
            int maxIndex = -1;
            for (int j = 0; j < 4; j++) {
                if (ans[i][j] > max) {
                    max = ans[i][j];
                    maxIndex = j;
                }
            }
            cons += chars[maxIndex];
        }
        out.println(cons);
        for (int i = 0; i < 4; i++) {
            out.print(chars[i] + ": ");
            for (int j = 0; j < ans.length; j++) {
                out.print(ans[j][i]);
                if (j != ans.length - 1) {
                    out.print(" ");
                }
            }
            out.println();
        }

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
        new Task12().run();
    }
}