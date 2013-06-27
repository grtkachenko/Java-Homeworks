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

public class TaskE {
    final int MAX = Integer.MAX_VALUE;
    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;

    public static void main(String[] args) throws IOException {
// Locale.setDefault(Locale.US);
        new TaskE().run();
    }

    ArrayList<Integer> prefixFunction(String s) {
        int n = s.length();
        ArrayList<Integer> pi = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            pi.add(i);
        }

        for (int i = 1; i < n; ++i) {
            int j = pi.get(i - 1);
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = pi.get(j - 1);
            if (s.charAt(i) == s.charAt(j)) ++j;
            pi.set(i, j);
        }
        return pi;
    }

    void solve() throws IOException {
        String s = nextLine();
        ArrayList<Integer> ans = prefixFunction(s);
        int tmp = s.length() - ans.get(s.length() - 1);
        if (s.length() % tmp == 0) {
            out.println(tmp);
        } else {
            out.println(s.length());
        }
    }

    void run() throws IOException {
        br = new BufferedReader(new FileReader("period.in"));
        out = new PrintWriter("period.out");
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
