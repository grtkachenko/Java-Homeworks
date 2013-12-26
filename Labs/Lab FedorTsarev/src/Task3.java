import java.util.*;
import java.io.*;

public class Task3 {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        List<String[]> data = new ArrayList<String[]>();

        String[] curData = new String[2];
        curData[0] = in.nextLine();
        curData[1] = "";
        while (true) {
            boolean isOk = true;

            while (true) {
                String cur = in.nextLine();

                if (cur == null) {
                    data.add(curData);
                    isOk = false;
                    break;
                }

                if (cur.charAt(0) == '>') {
                    data.add(curData);
                    curData = new String[2];
                    curData[0] = cur;
                    curData[1] = "";
                    break;
                }
                curData[1] += cur;
            }
            if (!isOk) break;


        }
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (i != j) {
                    if (data.get(i)[1].length() >= 3 && data.get(j)[1].length() >= 3) {
                        if (data.get(i)[1].substring(data.get(i)[1].length() - 3).equals(data.get(j)[1].substring(0, 3))) {
                            out.println(data.get(i)[0].substring(1) + " " + data.get(j)[0].substring(1));
                        }
                    }
                }
            }
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
        new Task3().run();
    }
}