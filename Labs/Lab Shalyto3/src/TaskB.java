import java.util.*;
import java.io.*;

public class TaskB {
    private FastScanner in;
    private PrintWriter out;

    public void solve() throws IOException {
        int p = in.nextInt();
        in.nextLine();

        String[] data = new String[4];
        loop : while (true) {

            for (int i = 0; i < 4; i++) {
                data[i] = in.nextLine();
                if (data[i] == null) {
                    break loop;
                }
            }
            int pos = data[3].length();
            for (int i = 0; i < data[3].length(); i++){
                if (data[3].charAt(i) - '!' < p){
                    pos = i;
                    break;
                }
            }
            out.println(data[0]);
            out.println(data[1].substring(0, pos));
            out.println(data[2]);
            out.println(data[3].substring(0, pos));
        }


    }

    public void run() {
        try {
            in = new FastScanner(new File("fastqcut.fastq"));
            out = new PrintWriter(new File("fastqcut.out"));
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
        new TaskB().run();
    }
}