import java.util.*;
import java.io.*;

public class Task5 {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        in.nextLine();

        List<String> dataInput = new ArrayList<String>();
        while (true) {
            String curInput = "";
            boolean isOk = true;
            while (true) {
                String cur = in.nextLine();
                if (cur == null) {
                    isOk = false;
                    break;
                }
                if (cur.charAt(0) == '>') {
                    break;
                } else {
                    curInput += cur;
                }
            }
            if (!isOk) {
                break;
            }
            dataInput.add(curInput);
        }

        int n = dataInput.size();
        String[] input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = dataInput.get(i);
        }

        String res = input[0];
        for (int i = 1; i < n; i++) {
            int maxK = 0;
            for (int k = 0; k < input[i].length(); k++) {
                if (res.substring(res.length() - k).equals(input[i].substring(0, k))) {
                    maxK = k;
                }
            }
            res += input[i].substring(maxK);
        }
        out.println(res);
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
        new Task5().run();
    }
}