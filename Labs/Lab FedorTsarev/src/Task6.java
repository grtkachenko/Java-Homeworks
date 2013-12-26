import java.util.*;
import java.io.*;

public class Task6 {
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
            dataInput.add(curInput);
            if (!isOk) {
                break;
            }
        }

        int n = dataInput.size();
        String[][] input = new String[n][2];
        for (int i = 0; i < n; i++) {
            input[i][0] = dataInput.get(i);
            input[i][1] = getReverse(dataInput.get(i));
        }

        for (int i = 0; i < n; i++) {
            boolean isOk = false;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    for (int k = 0; k < 2; k++) {
                        if (input[i][0].equals(input[j][k])) {
                            isOk = true;
                        }
                    }
                }
            }
            if (!isOk) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < 2; k++) {
                        if (i != j && hammingDistance(input[i][0], input[j][k]) == 1) {
                            out.println(input[i][0] + "->" + input[j][k]);
                            input[i] = input[j];
                            break;
                        }
                    }
                }
            }
        }


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

    int hammingDistance(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count;
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
        new Task6().run();
    }
}