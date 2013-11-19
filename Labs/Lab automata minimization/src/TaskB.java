
import java.io.*;
import java.util.*;

public class TaskB {
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] arg) {
        new TaskB().run();
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

        out.println(Automata.isEquivalent(automatas[0], automatas[1]) ? "YES" : "NO");
    }

    public void run() {
        try {
            in = new FastScanner(new File("equivalence.in"));
            out = new PrintWriter(new File("equivalence.out"));
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Automata {
        private static final char MIN_CHAR = 'a';
        private static final char MAX_TRANSITIONS = 31;
        private static final int INVALID_VALUE = -1;
        private int size;
        private List<Integer> admit = new ArrayList<Integer>();
        private boolean[] admitBoolean;
        private List<Integer>[][] transitionsTo;
        private int[][] transitionsFrom;


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

        public static boolean isEquivalent(Automata automata1, Automata automata2) {
            Automata sum = new Automata(automata1.size + automata2.size + 1);
            Automata[] automatas = {automata1, automata2};
            int offset = 0, failVertex = automata1.size + automata2.size;
            for (Automata automata : automatas) {
                for (int curAdmit : automata.admit) {
                    sum.addAdmit(curAdmit + offset);
                }
                for (int i = 0; i < automata.size; i++) {
                    for (int j = 0; j < MAX_TRANSITIONS; j++) {
                        if (automata.transitionsFrom[i][j] != INVALID_VALUE) {
                            sum.addTransition(i + offset, automata.transitionsFrom[i][j] + offset, j);
                        } else {
                            sum.addTransition(i + offset, failVertex, j);
                        }
                    }
                }
                offset += automata.size;
            }

            for (int i = 0; i < MAX_TRANSITIONS; i++) {
                sum.addTransition(failVertex, failVertex, i);
            }
            offset -= automata2.size;
            boolean[][] isEqual = new boolean[sum.size][sum.size];
            for (int i = 0; i < sum.size; i++) {
                Arrays.fill(isEqual[i], true);
            }
            boolean[][] used = new boolean[sum.size][sum.size];
            ArrayDeque<Integer> q = new ArrayDeque<Integer>();

            final int totalSize = sum.size;
            for (int i = 0; i < sum.size; i++) {
                for (int j = i + 1; j < sum.size; j++) {
                    if (sum.admitBoolean[i] ^ sum.admitBoolean[j]) {
                        isEqual[i][j] = false;
                        q.addLast(i * totalSize + j);
                    }
                }
            }
            int first, second;
            while (!q.isEmpty()) {
                final int cur = q.pollFirst();
                first = cur / totalSize;
                second = cur % totalSize;
                if (first == 0 && second == offset || first == offset && second == 0) {
                    break;
                }

                used[first][second] = true;
                used[second][first] = true;
                isEqual[first][second] = false;
                isEqual[second][first] = false;
                for (int i = 0; i < MAX_TRANSITIONS; i++) {
                    for (int j : sum.transitionsTo[first][i]) {
                        for (int k : sum.transitionsTo[second][i]) {
                            if (!used[j][k]) {
                                q.addLast(j * totalSize + k);
                            }
                        }
                    }

                }
            }
            return isEqual[0][offset];
        }

        public void addTransition(int from, int to, char symbol) {
            addTransition(from, to, symbol - MIN_CHAR);
        }

        public void addTransition(int from, int to, int symbol) {
            transitionsTo[to][symbol].add(from);
            transitionsFrom[from][symbol] = to;
        }

        public void addAdmit(int vertex) {
            admit.add(vertex);
            admitBoolean[vertex] = true;
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
}