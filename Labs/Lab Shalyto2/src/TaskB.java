import java.util.*;
import java.io.*;

public class TaskB {
    private FastScanner in;
    private PrintWriter out;
    private static final int NUM_EDGES = 20;

    private static class OneSquare {
        private double out;
        private List<Integer> edges = new ArrayList<Integer>();
        private int[] haveEdge = new int[NUM_EDGES];
        private Expression expression;

        private OneSquare(double out, int edge, Expression expression) {
            this.out = out;
            this.expression = expression;
            addEdge(edge);
        }

        private OneSquare(OneSquare oneSquare, Expression expression) {
            this.out = oneSquare.out;
            this.expression = expression;
            for (Integer cur : oneSquare.edges) {
                edges.add(cur);
            }
            for (int i = 0; i < NUM_EDGES; i++) {
                haveEdge[i] = oneSquare.haveEdge[i];
            }
        }

        public void addEdge(int number) {
            edges.add(number);
            haveEdge[number]++;
        }

    }

    private static class Expression {
        private double mulPrefix;
        private List<OneSquare> squares;


        private Expression(int length) {
            squares = new ArrayList<OneSquare>();
            mulPrefix = 1.0 / length;
        }

        public void addEdge(int number, double out) {
            if (squares.isEmpty()) {
                squares.add(new OneSquare(out, number, this));
            } else {
                OneSquare cur = new OneSquare(squares.get(squares.size() - 1), this);
                cur.addEdge(number);
                cur.out = out;
                squares.add(cur);
            }
        }
    }

    private static class Row {
        private int size = 0;
        private double[] val;
        private double res;

        private Row(int size) {
            this.size = size;
            val = new double[size];
        }

        public void mul(double mulValue) {
            for (int i = 0; i < size; i++) {
                val[i] *= mulValue;
            }
            res *= mulValue;
        }

        public void sub(Row row) {
            for (int i = 0; i < size; i++) {
                val[i] -= row.val[i];
            }
            res -= row.res;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < size; i++) {
                stringBuilder.append("(" + val[i] + ")" + " * x" + i + " ");
                if (i != size - 1) {
                    stringBuilder.append("+ ");
                }
            }
            stringBuilder.append("= " + res);
            return stringBuilder.toString();
        }
    }

    private static class Gauss {
        private int size = 0;
        private Row[] matrix;

        private Gauss(int size) {
            this.size = size;
            matrix = new Row[size];
        }

        public double[] solve() {
            double[] ans = new double[size];
            for (int i = 0; i  < size; i++) {
                for (int j = i; j < size; j++) {
                    if (matrix[j].val[i] != 0) {
                        Row tmp = matrix[i];
                        matrix[i] = matrix[j];
                        matrix[j] = tmp;
                        break;
                    }
                }

                for (int j = i + 1; j < size; j++) {
                    if (matrix[j].val[i] != 0) {
                        matrix[j].mul(matrix[i].val[i] / matrix[j].val[i]);
                        matrix[j].sub(matrix[i]);
                    }
                }
            }

            for (int i = size - 1; i >= 0; i--) {
                if (matrix[i].val[i] == 0) {
                    ans[i] = 0;
                    continue;
                }
                double res = matrix[i].res;
                for (int j = i; j < size; j++) {
                    res -= ans[j] * matrix[i].val[j];
                }
                ans[i] = res / matrix[i].val[i];
            }

            return ans;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < size; i++) {
                stringBuilder.append(matrix[i] + "\n");
            }
            return stringBuilder.toString();
        }
    }

    private int numberByEdge(int from, int to) {
        return from * 2 + to;
    }

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                a[i][j] = in.nextInt() - 1;
            }
        }
        Expression[] expressions = new Expression[m];

        for (int i = 0; i < m; i++) {
            final int len = in.nextInt();
            expressions[i] = new Expression(len);
            char[] inString = in.next().toCharArray();
            int curVertex = 0;
            for (int j = 0; j < len; j++) {
                expressions[i].addEdge(numberByEdge(curVertex, inString[j] - '0'), in.nextDouble());
                curVertex = a[curVertex][inString[j] - '0'];
            }
        }

        Gauss gauss = new Gauss(2 * n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                final int numberEdge = numberByEdge(i, j);
                Row currentRow = new Row(2 * n);
                double currentX = 0;
                for (Expression expression : expressions) {
                    for (OneSquare oneSquare : expression.squares) {
                        if (oneSquare.haveEdge[numberEdge] != 0) {
                            currentX += 2 * oneSquare.haveEdge[numberEdge] * oneSquare.haveEdge[numberEdge] * oneSquare.expression.mulPrefix;
                            currentRow.res -= oneSquare.out * 2 * oneSquare.haveEdge[numberEdge] * oneSquare.expression.mulPrefix;
                            for (int edge : oneSquare.edges) {
                                if (edge != numberEdge) {
                                    currentRow.val[edge] -= 2 * oneSquare.haveEdge[numberEdge] * oneSquare.expression.mulPrefix;
                                }
                            }
                        }
                    }
                }
                currentRow.val[numberEdge] = -currentX;
                gauss.matrix[numberEdge] = currentRow;
            }
        }

//        out.print(gauss);
        double[] ans = gauss.solve();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                final int numberEdge = numberByEdge(i, j);
                out.print(ans[numberEdge] + " ");
            }
            out.println();
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("continuous.in"));
            out = new PrintWriter(new File("continuous.out"));
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(String[] arg) {
        new TaskB().run();
    }
}