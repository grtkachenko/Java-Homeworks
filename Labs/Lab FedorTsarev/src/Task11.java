import java.util.*;
import java.io.*;

public class Task11 {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        in.nextLine();
        String s = "";
        while (true) {
            String cur = in.nextLine();
            if (cur == null) {
                break;
            }
            s += cur;
        }
        final int n = 4;
        char[] a = {'A', 'C', 'G', 'T'};
        int[] start = new int[n];
        List<String> kMers = new ArrayList<String>();
        while (true) {
            kMers.add(arrayToString(start, a));
            int[] cur = next(start, a.length);
            if (cur == null) {
                break;
            }
        }
        for (String cur : kMers) {
            int count = 0;
            for (int j = 0; j < s.length() - n + 1; j++) {
                if (cur.equals(s.substring(j, j + n))) {
                    count++;
                }
            }
            out.print(count + " ");
        }
    }

    int[] next(int[] arr, int n) {
        int cur = arr.length - 1;
        while (cur >= 0 && arr[cur] == n - 1) {
            arr[cur--] = 0;
        }
        if (cur < 0) {
            return null;
        }
        arr[cur]++;
        return arr;
    }

    String arrayToString(int[] start, char[] a) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int cur : start) {
            stringBuilder.append(a[cur]);
        }
        return stringBuilder.toString();
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
        new Task11().run();
    }
}