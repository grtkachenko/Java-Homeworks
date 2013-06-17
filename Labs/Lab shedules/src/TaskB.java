import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class TaskB {
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;
    int n;
    long[] a, b;
    boolean isChanged = false;

    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskB().run();
    }

    void solve() throws IOException {
        List<Integer> I, J;
        I = new ArrayList<Integer>();
        J = new ArrayList<Integer>();

        long cmax = Long.MIN_VALUE, suma = 0, sumb = 0;
        calcIJ(I, J);
        for (int i = 0; i < n; i++) {
            suma += a[i];
            sumb += b[i];
            cmax = Math.max(cmax, a[i] + b[i]);
        }
        cmax = Math.max(cmax, Math.max(suma, sumb));

        int x = maxIndex(I, a), y = maxIndex(J, b);
        if (x == -1 || (y != -1 && a[x] < b[y])) {
            isChanged = true;
            for (int i = 0; i < n; i++) {
                long tmp = a[i];
                a[i] = b[i];
                b[i] = tmp;
            }
            calcIJ(I, J);
            x = maxIndex(I, a);
        }

        ArrayList<Integer>[] order = new ArrayList[2];
        order[0] = new ArrayList<Integer>();
        order[1] = new ArrayList<Integer>();
        order[1].add(x);
        for (int i : I) {
            if (i != x) {
                order[1].add(i);
                order[0].add(i);
            }

        }
        long[][] ans = new long[2][n];
        for (int i = 0; i < 2; i++) {
            long curTime = 0;
            for (int j : order[i]) {
                ans[i][j] = curTime;
                if (i == 0) {
                    curTime += a[j];
                } else {
                    curTime += b[j];
                }
            }
        }
        order[0].clear();
        order[1].clear();

        for (int i : J) {
            order[0].add(i);
            order[1].add(i);
        }
        order[0].add(x);
        Collections.reverse(order[0]);
        Collections.reverse(order[1]);

        for (int i = 0; i < 2; i++) {
            long curTime = cmax;

            for (int j : order[i]) {
                if (i == 0) {
                    curTime -= a[j];
                } else {
                    curTime -= b[j];
                }
                ans[i][j] = curTime;
            }
        }

        out.println(cmax);

        if (isChanged) {
            long[] tmp = ans[0];
            ans[0] = ans[1];
            ans[1] = tmp;
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                out.print(ans[i][j] + " ");
            }
            out.println();
        }


    }

    int maxIndex(List<Integer> curList, long[] arr) {
        long maxa = Long.MIN_VALUE;
        int ans = -1;
        for (int i : curList) {
            if (maxa < arr[i]) {
                ans = i;
                maxa = arr[i];
            }
        }
        return ans;

    }

    void calcIJ(List<Integer> I, List<Integer> J) {
        I.clear();
        J.clear();
        for (int i = 0; i < n; i++) {
            if (a[i] <= b[i]) {
                I.add(i);
            } else {
                J.add(i);
            }
        }

    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("o2cmax.in"));
        out = new PrintWriter("o2cmax.out");
//        br = new BufferedReader(new FileReader("input.txt"));
//        out = new PrintWriter("output.txt");
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        n = nextInt();
        a = new long[n];
        b = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = nextLong();
        }

        solve();
        br.close();
        out.close();
    }

    String nextToken() throws IOException {
        while (stok == null || !stok.hasMoreTokens()) {
            String s = br.readLine();
            if (s == null) {
                return null;
            }
            stok = new StringTokenizer(s);
        }
        return stok.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    char nextChar() throws IOException {
        return (char) (br.read());
    }

    String nextLine() throws IOException {
        return br.readLine();
    }
}
