import java.util.*;
import java.io.*;

public class TaskA {
    private FastScanner in;
    private PrintWriter out;

    private static class Automata {
        private static final char MIN_CHAR = 'a';
        private static final char MAX_TRANSITIONS = 31;
        private static final int INVALID_VALUE = -1;
        private int size;
        private List<Integer> admit = new ArrayList<Integer>();
        private boolean[] admitBoolean;
        private int[][] transitions;

        private Automata(int size) {
            this.size = size;
            transitions = new int[size][MAX_TRANSITIONS];
            admitBoolean = new boolean[size];
            for (int i = 0; i < size; i++) {
                Arrays.fill(transitions[i], INVALID_VALUE);
            }
        }

        public void addTransition(int from, int to, char symbol) {
            transitions[from][symbol - MIN_CHAR] = to;
        }

        public void addAdmit(int vertex) {
            admit.add(vertex);
            admitBoolean[vertex] = true;
        }

        private static boolean dfs(boolean[][] used, Automata[] automatas, int[] currentStates, int[][] newStates) {
            for (int i = 0; i < automatas.length; i++) {
                used[i][currentStates[i]] = true;
            }

            boolean res = true;

            for (int i = 0; i < MAX_TRANSITIONS; i++) {
                int numTransition = 0;
                for (int j = 0; j < automatas.length; j++) {
                    numTransition += automatas[j].transitions[currentStates[j]][i] != INVALID_VALUE ? 1 : 0;
                }
                if (numTransition != 0) {
                    if (numTransition < automatas.length) {
                        return false;
                    }

                    int[] newCurrentStates = new int[currentStates.length];

                    boolean was = false;
                    for (int j = 0; j < automatas.length; j++) {
                        newCurrentStates[j] = automatas[j].transitions[currentStates[j]][i];
                        was |= used[j][newCurrentStates[j]];
                    }

                    for (int j = 0; j < automatas.length; j++) {
                        if (was && !used[j][newCurrentStates[j]]) {
                            return false;
                        }

                        if (newStates[j][newCurrentStates[j]] == INVALID_VALUE) {
                            newStates[j][newCurrentStates[j]] = newCurrentStates[1 - j];
                        } else {
                            if (newStates[j][newCurrentStates[j]] != newCurrentStates[1 - j]) {
                                return false;
                            }
                        }
                    }
                    if (!was) {
                        res &= dfs(used, automatas, newCurrentStates, newStates);
                    }
                }

            }

            return res;
        }

        public static boolean isIsomorphic(Automata a, Automata b) {
            if (a.size != b.size || a.admit.size() != b.admit.size()) {
                return false;
            }

            boolean[][] used = new boolean[2][a.size];
            int[] currentStates = {0, 0};
            int[][] newStates = new int[2][a.size];
            Automata[] automatas = {a, b};
            for (int i = 0; i < 2; i++) {
                Arrays.fill(newStates[i], INVALID_VALUE);
                newStates[i][0] = 0;
            }

            if (!dfs(used, automatas, currentStates, newStates)) {
                return false;
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < a.size; j++) {
                    int number = newStates[i][j];
                    if (number == INVALID_VALUE || (automatas[i].admitBoolean[j] ^ automatas[1 - i].admitBoolean[number])) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public void solve() throws IOException {
        Automata[] automatas = new Automata[2];
        for (int i = 0; i < 2; i++) {
            automatas[i] = new Automata(in.nextInt());
            int m = in.nextInt(), k = in.nextInt();
            for (int j = 0; j < k; j++) {
                automatas[i].addAdmit(in.nextInt() - 1);
            }
            for (int j = 0; j < m; j++) {
                automatas[i].addTransition(in.nextInt() - 1, in.nextInt() - 1, in.next().charAt(0));
            }
        }

        out.println(TaskA.Automata.isIsomorphic(automatas[0], automatas[1]) ? "YES" : "NO");
    }

    public void run() {
        try {
            in = new FastScanner(new File("isomorphism.in"));
            out = new PrintWriter(new File("isomorphism.out"));
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
    }

    public static void main(String[] arg) {
        new TaskA().run();
    }
}