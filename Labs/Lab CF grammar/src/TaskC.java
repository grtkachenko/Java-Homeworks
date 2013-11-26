import java.util.*;
import java.io.*;

public class TaskC {
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

        boolean[][] notUseless = new boolean[MAX_CHAR][2];
        boolean[] mentioned = new boolean[MAX_CHAR];


        Set<Integer> birthSet = new TreeSet<Integer>();

        for (int i = 0; i < n; i++) {
            String input = in.nextLine();
            int from = input.charAt(0) - 'A';
            mentioned[from] = true;
            if (input.length() == 4) {
                birthSet.add(from);
                continue;
            }

            String to = input.substring(5);
            boolean isOk = true;
            for (int j = 0; j < to.length(); j++) {
                if (to.charAt(j) >= 'A' && to.charAt(j) <= 'Z') {
                    isOk = false;
                    mentioned[to.charAt(j) - 'A'] = true;
                }
            }
            if (isOk) {
                birthSet.add(from);
                continue;
            }

            Rule cur = new Rule();
            for (int j = 0; j < to.length(); j++) {
                if (to.charAt(j) >= 'A' && to.charAt(j) <= 'Z') {
                    cur.add(to.charAt(j) - 'A');
                }
            }
            rules[from].add(cur);
        }

        for (int i = 0; i < MAX_IT; i++) {
            for (int j = 0; j < MAX_CHAR; j++) {
                if (birthSet.contains(j)) {
                    continue;
                }

                for (Rule rule : rules[j]) {
                    boolean ok = true;
                    for (int cur : rule.to) {
                        if (!birthSet.contains(cur)) {
                            ok = false;
                        }
                    }
                    if (ok) {
                        birthSet.add(j);
                        break;
                    }
                }
            }
        }

        for (int ans : birthSet) {
            notUseless[ans][0] = true;
        }

        Set<Integer> reachable = new TreeSet<Integer>();
        Set<Integer> addReachable = new TreeSet<Integer>();


        reachable.add(start);
        for (int i = 0; i < MAX_IT; i++) {
            addReachable.clear();
            for (int cur : reachable) {
                for (Rule rule : rules[cur]) {
                    for (int curTo : rule.to) {
                        addReachable.add(curTo);
                    }
                }
            }
            for (int cur : addReachable) {
                reachable.add(cur);
            }
        }
        for (int cur : reachable) {
            notUseless[cur][1] = true;
        }

        for (int i = 0; i < MAX_CHAR; i++) {
            if (mentioned[i] && (!notUseless[i][0] || !notUseless[i][1])) {
                out.print(((char)('A' + i)) + " ");
            }

        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("useless.in"));
            out = new PrintWriter(new File("useless.out"));
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
        new TaskC().run();
    }
}