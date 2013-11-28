import java.util.*;
import java.io.*;

public class TaskD {
    private FastScanner in;
    private PrintWriter out;
    private final static int MIN_VALUE = Integer.MIN_VALUE / 3131;
    private final static int MAX_VALUE = Integer.MAX_VALUE / 3131;


    public void solve() throws IOException {

//        for (int i = 0; i < 20; i++) {
//            for (int j = 0; j < 100; j++) {
//                out.print((char) ('A' + i));
//            }
//            out.println();
//        }
//        if (true) return;

        int n = in.nextInt();
        String[] input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = in.next();
        }

        List<String> single = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                if (i != j && input[j].contains(input[i]) && !input[j].equals(input[i])) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                single.add(input[i]);
            }
        }
        final int size = single.size();
        String[] strings = single.toArray(new String[size]);
        char[][] data = new char[size][];
        for (int i = 0; i < size; i++) {
            data[i] = strings[i].toCharArray();
        }


        int[][] to = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                to[i][j] = 0;
                for (int k = 0; k < Math.min(data[i].length, data[j].length); k++) {
                    if (strings[j].substring(0, k + 1).equals(strings[i].substring(data[i].length - 1 - k, data[i].length))) {
                        to[i][j] = k + 1;
                    }

                }
            }
        }

        final int[][] masks = new int[(1 << size)][size];
        final int[][] prevLast = new int[(1 << size)][size];
        final int[] lengths = new int[size];
        for (int i = 0; i < masks.length; i++) {
            Arrays.fill(masks[i], MAX_VALUE);
        }

        for (int i = 0; i < size; i++) {
            masks[(1 << i)][i] = data[i].length;
            lengths[i] = data[i].length;
        }

        final int[] left = new int[size];
        final int[] right = new int[size];
        int leftSize, rightSize, curLeft, curRight;


        for (int i = 1; i < (1 << size); i++) {
            leftSize = rightSize = 0;
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) != 0) {
                    left[leftSize++] = j;
                } else {
                    right[rightSize++] = j;
                }
            }
            for (int j = 0; j < leftSize; j++) {
                curLeft = left[j];
                for (int k = 0; k < rightSize; k++) {
                    curRight = right[k];
                    if (masks[i | (1 << curRight)][curRight] > masks[i][curLeft] + lengths[curRight] - to[curLeft][curRight]) {
                        masks[i | (1 << curRight)][curRight] = masks[i][curLeft] + lengths[curRight] - to[curLeft][curRight];
                        prevLast[i | (1 << curRight)][curRight] = curLeft;
                    }
                }
            }

        }
        int max = MAX_VALUE;
        int maxIndex = -1;

        for (int i = 0; i < size; i++) {
            if (masks[(1 << size) - 1][i] < max) {
                max = masks[(1 << size) - 1][i];
                maxIndex = i;
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        int curMask = (1 << size) - 1;
        int curVertex = maxIndex;
        while (curMask != 0) {
            ans.add(curVertex);
            int backUpMask = curMask;
            curMask = curMask & (~(1 << curVertex));
            curVertex = prevLast[backUpMask][curVertex];
        }

        int prev = ans.get(ans.size() - 1);
        String res = strings[prev];
        for (int i = ans.size() - 2; i >= 0; i--) {
            res += strings[ans.get(i)].substring(to[prev][ans.get(i)]);
            prev = ans.get(i);
        }
        out.println(res);
    }

    public void run() {
        try {
            in = new FastScanner(new File("scs.in"));
            out = new PrintWriter(new File("scs.out"));
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
        new TaskD().run();
    }
}