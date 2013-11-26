import java.util.*;
import java.io.*;

public class TaskB {
    private FastScanner in;
    private PrintWriter out;
    private static final int MAX_CHAR = 31;
    private static final int MAX_IT = 500;


    private static class Rule {
        Set<Integer> to = new HashSet<Integer>();

        public void add(int value) {
            to.add(value);
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt(), start = in.next().charAt(0) - 'A';
        List<Rule>[] rules = new List[MAX_CHAR];
        for (int i = 0; i < MAX_CHAR; i++) {
            rules[i] = new ArrayList<Rule>();
        }

        Set<Integer> epsSet = new TreeSet<Integer>();

        for (int i = 0; i < n; i++) {
            String input = in.nextLine();
            int from = input.charAt(0) - 'A';
            if (input.length() == 4) {
                epsSet.add(from);
                continue;
            }

            String to = input.substring(5);
            boolean isOk = true;
            for (int j = 0; j < to.length(); j++) {
                if (to.charAt(j) < 'A' || to.charAt(j) > 'Z') {
                    isOk = false;
                }
            }
            if (!isOk) {
                continue;
            }

            Rule cur = new Rule();
            for (int j = 0; j < to.length(); j++) {
                cur.add(to.charAt(j) - 'A');
            }
            rules[from].add(cur);
        }

        for (int i = 0; i < MAX_IT; i++) {
            for (int j = 0; j < MAX_CHAR; j++) {
                if (epsSet.contains(j)) {
                    continue;
                }

                for (Rule rule : rules[j]) {
                    boolean ok = true;
                    for (int cur : rule.to) {
                        if (!epsSet.contains(cur)) {
                            ok = false;
                        }
                    }
                    if (ok) {
                        epsSet.add(j);
                        break;
                    }
                }
            }
        }

        for (int ans : epsSet) {
            out.print(((char)('A' + ans)) + " ");
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("epsilon.in"));
            out = new PrintWriter(new File("epsilon.out"));
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