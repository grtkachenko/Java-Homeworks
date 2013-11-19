import java.util.*;
import java.io.*;

public class TaskC {
    private FastScanner in;
    private PrintWriter out;

    private static class Automata {
        private static final char MIN_CHAR = 'a';
        private static final char MAX_TRANSITIONS = 31;
        private static final int INVALID_VALUE = -1;
        private int size;
        private List<Integer> admit = new ArrayList<Integer>();
        private boolean[] admitBoolean;
        private List<Integer>[][] transitionsTo;
        private int[][] transitionsFrom;
        private int totalTransitions = 0;


        private Automata(int size) {
            this.size = size;
            transitionsTo = new List[size][MAX_TRANSITIONS];
            transitionsFrom = new int[size][MAX_TRANSITIONS];

            admitBoolean = new boolean[size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < MAX_TRANSITIONS; j++) {
                    transitionsFrom[i][j] = INVALID_VALUE;
                    transitionsTo[i][j] = new ArrayList<Integer>();
                }
            }
        }

        private static class State {
            private Set<Integer> set;
            private int c;

            private State(Set<Integer> set, int c) {
                this.set = set;
                this.c = c;
            }
        }

        public static Automata minimize(Automata automata) {
            Set<Set<Integer>> classes = new HashSet<Set<Integer>>();
            Set<Integer>[] help = new HashSet[2];
            for (int i = 0; i < 2; i++) {
                help[i] = new HashSet<Integer>();
                classes.add(help[i]);
            }
            for (int i = 0; i < automata.size; i++) {
                help[automata.admitBoolean[i] ? 0 : 1].add(i);
            }
            ArrayDeque<State> q = new ArrayDeque<State>();

            for (int i = 0; i < MAX_TRANSITIONS; i++) {
                for (int j = 0; j < 2; j++) {
                    q.addLast(new State(help[j], i));
                }
            }
            Set<Set<Integer>> tmp;

            while (!q.isEmpty()) {
                State cur = q.pollFirst();
                tmp = new HashSet<Set<Integer>>();
                for (Set<Integer> curSet : classes) {
                    Set<Integer> r1 = new HashSet<Integer>();
                    Set<Integer> r2 = new HashSet<Integer>();
                    for (int i : cur.set) {
                        if (cur.set.contains(automata.transitionsFrom[i][cur.c])) {
                            r1.add(i);
                        } else {
                            r2.add(i);
                        }
                    }
                    if (!r1.isEmpty() && !r2.isEmpty()) {
                        tmp.add(r1);
                        tmp.add(r2);
                        q.add(new State(r1, cur.c));
                        q.add(new State(r2, cur.c));
                    } else {
                        tmp.add(curSet);
                    }
                }
                classes = tmp;
            }

            Automata newAutomata = new Automata(classes.size());

            System.out.println(classes.size());


            return automata;
        }

        public void addTransition(int from, int to, char symbol) {
            addTransition(from, to, symbol - MIN_CHAR);
        }

        public void addTransition(int from, int to, int symbol) {
            totalTransitions++;
            transitionsTo[to][symbol].add(from);
            transitionsFrom[from][symbol] = to;
        }

        public void addAdmit(int vertex) {
            admit.add(vertex);
            admitBoolean[vertex] = true;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(size + " " + totalTransitions + " " + admit.size() + "\n");
            for (int i : admit) {
                stringBuilder.append((i + 1) + " ");
            }
            stringBuilder.append("\n");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < MAX_TRANSITIONS; j++) {
                    if (transitionsFrom[i][j] != INVALID_VALUE) {
                        stringBuilder.append((i + 1) + " " + (transitionsFrom[i][j] + 1) + " " + ((char) (MIN_CHAR + j)) + "\n");
                    }
                }
            }
            return stringBuilder.toString();
        }
    }


    public void solve() throws IOException {
        Automata automata = new Automata(in.nextInt());
        int m = in.nextInt(), k = in.nextInt();
        for (int j = 0; j < k; j++) {
            automata.addAdmit(in.nextInt() - 1);
        }
        for (int j = 0; j < m; j++) {
            automata.addTransition(in.nextInt() - 1, in.nextInt() - 1, in.next().charAt(0));
        }
        out.print(Automata.minimize(automata));
    }

    public void run() {
        try {
            in = new FastScanner(new File("minimization.in"));
            out = new PrintWriter(new File("minimization.out"));
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
        new TaskC().run();
    }
}