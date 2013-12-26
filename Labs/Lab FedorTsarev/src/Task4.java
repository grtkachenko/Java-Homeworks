import java.util.*;
import java.io.*;

public class Task4 {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        String s = in.nextLine();
        int n = in.nextInt();
        char[] a = new char[(s.length() + 1) / 2];
        for (int i = 0; i < s.length(); i += 2) {
            a[i / 2] = s.charAt(i);
        }
        int[] start = new int[n];
        while (true) {
            out.println(arrayToString(start, a));

            int[] cur = next(start, a.length);
            if (cur == null) {
                break;
            }
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
        new Task4().run();
    }
}