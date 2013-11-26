import java.util.*;
import java.io.*;

public class TaskE {
    private static final int MAX_IT = 100;
    private FastScanner in;
    private PrintWriter out;
    private static int lastNotTerm = 27;



    private static class Item {
        int value;
        boolean isTerm;

        private Item(int value, boolean term) {
            this.value = value;
            isTerm = term;
        }

        @Override
        public String toString() {
            return "" + ((char)((isTerm ? 'a' : 'A') + value));
        }
    }

    private static class Rule {
        int from;
        List<Item> to = new ArrayList<Item>();

        public Rule(int from) {
            this.from = from;
        }

        public List<Rule> deleteChain() {
            List<Rule> res = new ArrayList<Rule>();
            if (to.size() <= 2) {

                res.add(this);
                return res;
            }
            int lastFrom = from;
            for (int i = 0; i < to.size() - 1; i++) {
                Rule cur = new Rule(lastFrom);
                if (to.get(i).isTerm) {
                    Rule helpRule = new Rule(lastNotTerm);
                    helpRule.to.add(to.get(i));
                    res.add(helpRule);
                    cur.to.add(new Item(lastNotTerm++, false));
                } else {
                    cur.to.add(to.get(i));
                }
                if (i == to.size() - 2) {
                    if (to.get(i + 1).isTerm) {
                        Rule helpRule = new Rule(lastNotTerm);
                        helpRule.to.add(to.get(i + 1));
                        res.add(helpRule);
                        cur.to.add(new Item(lastNotTerm++, false));
                    } else {
                        cur.to.add(to.get(i + 1));
                    }
                } else {
                    cur.to.add(new Item(lastNotTerm++, false));
                }
                lastFrom = lastNotTerm - 1;
                res.add(cur);
            }
            return res;
        }

        @Override
        public String toString() {
            String res = ((char)('A' + from)) + " -> ";
            for (Item item : to) {
                res += item;
            }
            return res;
        }
    }

    private void printRules(List<Rule> rules) {
        for (Rule rule : rules) {
            System.out.println(rule);
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt(), start = in.next().charAt(0) - 'A';
        List<Rule> rules = new ArrayList<Rule>();

        for (int i = 0; i < n; i++) {
            String input = in.nextLine();
            int from = input.charAt(0) - 'A';
            Rule rule = new Rule(from);
            rules.add(rule);
            if (input.length() > 4) {
                String to = input.substring(5);
                for (int j = 0; j < to.length(); j++) {
                    if (to.charAt(j) < 'A' || to.charAt(j) > 'Z') {
                        rule.to.add(new Item(to.charAt(j) - 'a', true));
                    } else {
                        rule.to.add(new Item(to.charAt(j) - 'A', false));
                    }
                }

            }
        }

        List<Rule> newRules = new ArrayList<Rule>();
        for (Rule rule : rules) {
            newRules.addAll(rule.deleteChain());
        }

//        printRules(newRules);

        epsSet = new HashSet<Integer>();
        for (int i = 0; i < MAX_IT; i++) {
            for (Rule rule : newRules) {
                boolean ok = true;
                for (Item cur : rule.to) {
                    if (cur.isTerm || !epsSet.contains(cur.value)) {
                        ok = false;
                    }
                }
                if (ok) {
                    epsSet.add(rule.from);
                }
            }
        }


        String w = in.next();
        final int MAX_CHAR = lastNotTerm;
        final int size = w.length();
        ans = new int[MAX_CHAR][size][size];
        input = new int[w.length()];
        for (int i = 0; i < w.length(); i++) {
            input[i] = w.charAt(i) - 'a';
        }
        rulesFrom = new List[MAX_CHAR];
        for (int i = 0; i < MAX_CHAR; i++) {
            rulesFrom[i] = new ArrayList<Rule>();
            for (int j = 0; j < size; j++) {
                Arrays.fill(ans[i][j], -1);
            }
        }

        for (Rule rule : newRules) {
            if (rule.to.isEmpty()) {
                continue;
            }
            rulesFrom[rule.from].add(rule);
        }


        out.print(isGenerates(start, 0, size - 1) ? "yes" : "no");
    }

    // -1 - not calculated; 0 - false; 1 - true
    int[][][] ans;
    int[] input;
    Set<Integer> epsSet;
    List<Rule>[] rulesFrom;



    boolean isGenerates(int from, int left, int right) {
        if (left == -1) {
            return epsSet.contains(from);
        }

        if (ans[from][left][right] != -1) {
            return ans[from][left][right] != 0;
        }


        ans[from][left][right] = 0;
        for (Rule rule : rulesFrom[from]) {
            boolean ok = false;
            if (rule.to.size() == 1) {
                if (rule.to.get(0).isTerm) {
                    if (right == left && input[left] == rule.to.get(0).value) {
                        ok = true;
                    }
                } else {
                    ok |= isGenerates(rule.to.get(0).value, left, right);
                }
            } else {
                if (rule.to.get(0).isTerm && rule.to.get(1).isTerm) {
                    if (right == left + 1 && input[left] == rule.to.get(0).value && input[right] == rule.to.get(1).value) {
                        ok = true;
                    }
                } else {
                    if (rule.to.get(0).isTerm) {
                        if (input[left] != rule.to.get(0).value) {
                            continue;
                        }
                        if (right == left) {
                            ok |= isGenerates(rule.to.get(1).value, -1, -1);
                        } else {
                            ok |= isGenerates(rule.to.get(1).value, left + 1, right);
                        }
                    } else {
                        if (rule.to.get(1).isTerm) {
                            if (input[right] != rule.to.get(1).value) {
                                continue;
                            }
                            if (right == left) {
                                ok |= isGenerates(rule.to.get(0).value, -1, -1);
                            } else {
                                ok |= isGenerates(rule.to.get(0).value, left, right - 1);
                            }
                        } else {
                            ok |= isGenerates(rule.to.get(0).value, left, right) && isGenerates(rule.to.get(1).value, -1, -1);
                            ok |= isGenerates(rule.to.get(1).value, left, right) && isGenerates(rule.to.get(0).value, -1, -1);
                            for (int i = left; i < right; i++) {
                                ok |= isGenerates(rule.to.get(0).value, left, i) && isGenerates(rule.to.get(1).value, i + 1, right);
                                if (ok) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (ok) {
                ans[from][left][right] = 1;
                return true;
            }
        }
        return false;
    }

    public void run() {
        try {
            in = new FastScanner(new File("cf.in"));
            out = new PrintWriter(new File("cf.out"));
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
        new TaskE().run();
    }
}