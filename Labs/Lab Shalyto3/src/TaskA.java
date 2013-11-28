import java.util.*;
import java.io.*;

public class TaskA {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        List<Integer> len = new ArrayList<Integer>();
        int sum = 0, N50 = 0, N90 = 0, count = 0;
        while (true) {
            String s = in.nextLine();
            if (s == null) {
                break;
            }
            if (s.charAt(0) == '>') {
                len.add(0);
                continue;
            }
            len.set(len.size() - 1, len.get(len.size() - 1) + s.length());
            sum += s.length();

        }
        Collections.sort(len);
        for (int i = len.size() - 1; i >= 0; i--){
            count += len.get(i);
            if (sum <= 2 * count){
                N50 = i;
                break;
            }
        }
        count = 0;
        for (int i = len.size() - 1; i >= 0; i--){
            count += len.get(i);
            if (10 * count >= 9 * sum){
                N90 = i;
                break;
            }
        }
        out.println(len.get(N50));
        out.println(len.get(N90));
        out.println(len.get(0));
        out.println(len.get(len.size() - 1));
        out.println((sum + .0) / len.size());

    }

    public void run() {
        try {
            in = new FastScanner(new File("fasta-statistics.fasta"));
            out = new PrintWriter(new File("fasta-statistics.out"));
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
        new TaskA().run();
    }
}