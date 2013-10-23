import java.util.*;
import java.io.*;

public class TaskC {
    // not so good coding. have to be fast rather than accurate :(
    private FastScanner in;
    private PrintWriter out;
    private static final int GEN_SIZE = 1000;
    private static final int NUMBER_FITTABLE = 130;
    private static final double MUTATION_PROBABILITY = 0.4;
    private static Field testingField;
    private static int numMovements;
    private static int totalFood;


    private static class Utils {
        private static final Random random = new Random();

        public static int randomInt(int n) {
            return random.nextInt(n);
        }

        public static boolean randomBool() {
            return random.nextBoolean();
        }

        public static double randomDouble() {
            return random.nextDouble();
        }

        public static int nextDirection(int direction, boolean turnLeft) {
            if (turnLeft) {
                return (direction + 3) % 4;
            } else {
                return (direction + 1) % 4;
            }
        }

        public static int nextCoordinate(int val, int n) {
            return (val + 1) % n;
        }

        public static int prevCoordinate(int val, int n) {
            return (val + n - 1) % n;
        }

        public static int[] coordinatesAfterMovement(int i, int j, int direction, int size) {
            int[] ans = {i, j};
            if (direction % 2 == 0) {
                ans[1] = direction == 0 ? Utils.prevCoordinate(j, size) : Utils.nextCoordinate(j, size);
            } else {
                ans[0] = direction == 1 ? Utils.prevCoordinate(i, size) : Utils.nextCoordinate(i, size);
            }
            return ans;
        }

    }

    private static class Field {
        private int size;
        private boolean[][] field;

        public Field() {
        }

        public Field(Field f) {
            size = f.size;
            field = new boolean[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    field[i][j] = f.field[i][j];
                }
            }
        }

        private Field(int size) {
            this.size = size;
            field = new boolean[size][size];
        }

        /**
         * @param direction 0 - left, 1 - up, 2 - right, 3 - down
         */
        private boolean haveFood(int i, int j, int direction) {
            int[] next = Utils.coordinatesAfterMovement(i, j, direction, size);
            return field[next[0]][next[1]];
        }

    }

    private static class Automata implements Comparable<Automata> {
        private static int MAX_FOOD = Integer.MIN_VALUE;
        private int n;
        private int start = 0;
        // 0 - have food, 1 - don't have
        private int[][] toState;
        // 0 - left, 1 - right, 2 - forward
        private int[][] out;
        private int currentFood;
        private boolean[][] inFirstPart;

        private Automata(int n) {
            this.n = n;
            toState = new int[n][2];
            out = new int[n][2];
            inFirstPart = new boolean[n][2];
        }

        private Automata(Automata a) {
            this(a.n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    toState[i][j] = a.toState[i][j];
                    out[i][j] = a.out[i][j];
                }
            }
        }

        public static Automata mutate(Automata a) {
            Automata res = new Automata(a);
            int state = Utils.randomInt(a.n);
            int haveFood = Utils.randomInt(2);
            if (Utils.randomBool()) {
                if (Utils.randomBool()) {
                    res.start = state;
                    return res;
                } else {
                    int tmp = res.toState[state][haveFood];
                    res.toState[state][haveFood] = res.toState[state][haveFood ^ 1];
                    res.toState[state][haveFood ^ 1] = tmp;
                    tmp = res.out[state][haveFood];
                    res.out[state][haveFood] = res.out[state][haveFood ^ 1];
                    res.out[state][haveFood ^ 1] = tmp;
                    return res;
                }
            } else {
                if (Utils.randomBool()) {
                    res.out[state][haveFood] = Utils.randomInt(3);
                    return res;
                } else {
                    res.toState[state][haveFood] = Utils.randomInt(a.n);
                    return res;
                }
            }
        }

        public static Automata[] cross(Automata a, Automata b) {
            Automata newA = new Automata(a);
            Automata newB = new Automata(b);
            if (Utils.randomBool()) {
                newA.start = b.start;
                newB.start = a.start;
            }

            return crossFirstWay(a, b, newA, newB);
        }

        private void setTransitionFromAnotherAutomata(int i, int j, Automata automata) {
            toState[i][j] = automata.toState[i][j];
            out[i][j] = automata.out[i][j];
        }

        private static Automata[] crossFirstWay(Automata p1, Automata p2, Automata s1, Automata s2) {
            boolean firstType = Utils.randomBool();
            for (int i = 0; i < s1.n; i++) {
                for (int j = 0; j < 2; j++) {
                    if (firstType == p1.inFirstPart[i][j]) {
                        s1.toState[i][j] = p1.toState[i][j];
                        s2.toState[i][j] = p2.toState[i][j];
                        s1.out[i][j] = p1.out[i][j];
                        s2.out[i][j] = p2.out[i][j];
                    } else {
                        s1.toState[i][j] = p2.toState[i][j];
                        s2.toState[i][j] = p1.toState[i][j];
                        s1.out[i][j] = p2.out[i][j];
                        s2.out[i][j] = p1.out[i][j];
                    }
                }
            }

            return new Automata[] {s1, s2};
        }


        public void genRandom() {
            start = Utils.randomInt(n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    toState[i][j] = Utils.randomInt(n);
                    out[i][j] = Utils.randomInt(3);
                }
                out[i][0] = 2;
            }
        }

        public double getFitnessValue(Field field, int numMove) {
            Field help = new Field(field);
            int[] curPositionInField = {0, 0};
            int curState = start;
            int food = 0;
            int curDirection = 2;
            int lastMove = -1;
            for (int i = 0; i < n; i++) {
                Arrays.fill(inFirstPart[i], false);
            }

            for (int i = 0; i < numMove; i++) {
                int indexHave = help.haveFood(curPositionInField[0], curPositionInField[1], curDirection) ? 0 : 1;
                int action = out[curState][indexHave];
                curState = toState[curState][indexHave];
                if (i < numMove / 5) {
                    inFirstPart[curState][indexHave] = true;
                }
                if (action != 2) {
                    curDirection = Utils.nextDirection(curDirection, action == 0);
                } else {
                    curPositionInField = Utils.coordinatesAfterMovement(curPositionInField[0], curPositionInField[1], curDirection, field.size);
                    if (indexHave == 0) {
                        food++;
                        lastMove = i + 1;
                        help.field[curPositionInField[0]][curPositionInField[1]] = false;
                    }
                }
            }
            MAX_FOOD = Math.max(MAX_FOOD, food);
            currentFood = food;
            return food + (numMove - lastMove + .0) / numMove;
        }

        private char getCharByAction(int action) {
            switch (action) {
                case 0 : return 'L';
                case 1 : return 'R';
                case 2 : return 'M';
            }
            return '@';
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                stringBuilder.append((toState[i][1] + 1) + " " + (toState[i][0] + 1) + " " + getCharByAction(out[i][1]) + " " + getCharByAction(out[i][0]) + "\n");
            }
            return stringBuilder.toString();
        }

        @Override
        public int compareTo(Automata o) {
            return - (int) Math.signum(getFitnessValue(testingField, numMovements) - o.getFitnessValue(testingField, numMovements));
        }
    }

    public void solve() throws IOException {
        for (int test = 0; test < 10; test++) {
            int m = in.nextInt(), n = in.nextInt();
            numMovements = in.nextInt();
            testingField = new Field(m);
            totalFood = 0;
            Automata.MAX_FOOD = Integer.MIN_VALUE;
            for (int i = 0; i < m ; i++) {
                char[] line = in.next().toCharArray();
                for (int j = 0; j < m; j++) {
                    testingField.field[i][j] = line[j] == '*';
                    if (testingField.field[i][j]) {
                        totalFood++;
                    }
                }
            }
            System.out.println("Test number " + (test + 1) + ". Total food : " + totalFood);

            int currentCounter = 0;
            Automata[][] generation = new Automata[2][GEN_SIZE];
            for (int i = 0; i < GEN_SIZE; i++) {
                generation[0][i] = new Automata(n);
                generation[0][i].genRandom();
            }

            int prevMax = -1;
            while (prevMax != totalFood) {
                Arrays.sort(generation[currentCounter]);
                if (prevMax != Automata.MAX_FOOD) {
                    prevMax = Automata.MAX_FOOD;
                    System.out.println(Automata.MAX_FOOD);
                    if (prevMax == totalFood) {
                        out.println((generation[currentCounter][0]));
                        break;
                    }
                }

                currentCounter ^= 1;
                for (int i = 0; i < NUMBER_FITTABLE; i++) {
                    generation[currentCounter][i] = generation[currentCounter ^ 1][i];
                }

                for (int i = NUMBER_FITTABLE; i < GEN_SIZE; i += 2) {
                    int first = Utils.randomInt(i);
                    int second = first;
                    while (second == first) {
                        second = Utils.randomInt(i);
                    }
                    Automata a = generation[currentCounter][first], b = generation[currentCounter][second];
                    if (Utils.randomDouble() <= MUTATION_PROBABILITY) {
                        generation[currentCounter][i] = Automata.mutate(a);
                        generation[currentCounter][i + 1] = Automata.mutate(b);
                    } else {
                        Automata[] res = Automata.cross(a, b);
                        generation[currentCounter][i] = res[0];
                        generation[currentCounter][i + 1] = res[1];
                    }
                }

            }
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("artificial.in"));
            out = new PrintWriter(new File("artificial.out"));
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