/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 01.03.13
 * Time: 20:04
 * To change this template use File | Settings | File Templates.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TaskA {
    final int MAX = Integer.MAX_VALUE;
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;

    public static void main(String[] args) throws IOException {
// Locale.setDefault(Locale.US);
        new TaskA().run();
    }

    int[] zFunction(String s) {
        int[] z = new int[s.length()];
        z[0] = 0;
        int l = 0, r = 0, n = s.length();
        for (int i = 1; i < n; i++) {
            if (i > r) {
                l = r = i;
                while (r < n && s.charAt(r - l) == s.charAt(r)) r++;
                z[i] = r - l;
                r--;
            } else {
                int k = i - l;
                if (z[k] < r - i + 1) z[i] = z[k];
                else {
                    l = i;
                    while (r < n && s.charAt(r - l) == s.charAt(r)) r++;
                    z[i] = r - l;
                    r--;
                }
            }
        }
        return z;
    }

    void solve() throws IOException {
        String p = nextLine(), t = nextLine();

        String s = p + "#" + t;
        StringBuilder builder = new StringBuilder();
        for (int i = p.length() - 1; i >= 0; i--) {
            builder.append(p.charAt(i));
        }
        builder.append("#");
        for (int i = t.length() - 1; i >= 0; i--) {
            builder.append(t.charAt(i));
        }


        int[] z = zFunction(s);
        int[] zrev = zFunction(builder.toString());

        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int i = p.length() + 1; i < s.length() - p.length() + 1; i++) {
            int l = i + z[i] + -p.length(), r = i + p.length() - 1 - (p.length() + 1);
            l = (p.length() + 1) + t.length() - l - 1;
            r = (p.length() + 1) + t.length() - r - 1;

            if (z[i] == p.length() || zrev[r] == l - r + 1) {
                ans.add(i - p.length());
            }
        }

        out.println(ans.size());
        for (int i : ans) {
            out.print(i + " ");
        }
    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("search3.in"));
        out = new PrintWriter("search3.out");
//        br = new BufferedReader(new FileReader("input.txt"));
//        out = new PrintWriter("output.txt");
//br = new BufferedReader(new InputStreamReader(System.in));
// out = new PrintWriter(System.out);
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
