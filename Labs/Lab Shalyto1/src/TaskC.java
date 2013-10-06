import java.util.*;
import java.io.*;

public class TaskC {
    private FastScanner in;
    private PrintWriter out;
    private int lastNumber = 1;

    private class Node implements Comparable<Node> {
        private int predicate;
        private Node left;
        private Node right;
        private int value;
        private boolean isLeaf = false;
        private Node parent;
        private int number;

        private Node(int number) {
            this.number = number;
        }

        public void makeNotLeaf(int predicate, Node left, Node right) {
            this.predicate = predicate;
            this.left = left;
            this.right = right;
        }

        public void makeLeaf(int value) {
            isLeaf = true;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return number - o.number;
        }
    }

    private Node[] a;

    public void solve() throws IOException {
        int k = in.nextInt();
        a = new Node[k];

        for (int i = 0; i < k; i++) {
            a[i] = new Node(i + 1);
        }
        for (int i = 0; i < k; i++) {
            if (in.next().charAt(0) == 'l') {
                a[i].makeLeaf(in.nextInt());
            } else {
                a[i].makeNotLeaf(in.nextInt(), a[in.nextInt() - 1], a[in.nextInt() - 1]);
                a[i].left.parent = a[i];
                a[i].right.parent = a[i];
            }
        }

        // -1 - wasn't, 0 - left, 1 - right
        Map<Integer, Integer> was = new HashMap<Integer, Integer>();

        dfs(a[0], was, false);
        List<Node> ans = new ArrayList<Node>();
        makeAns(a[0], ans);
        Collections.sort(ans);
        out.println(ans.size());
        for (Node node : ans) {
            out.print(node.isLeaf ? "leaf " : "choice ");
            if (node.isLeaf) {
                out.println(node.value);
            } else {
                out.println(node.predicate + " " + node.left.number + " " + node.right.number);
            }
        }
    }

    private void makeAns(Node u, List<Node> ans) {
        ans.add(u);
        u.number = lastNumber++;
        if (!u.isLeaf) {
            makeAns(u.left, ans);
            makeAns(u.right, ans);
        }
    }

    private void dfs(Node u, Map<Integer, Integer> was, boolean isLeft) {
        if (u.isLeaf) {
            return;
        }
        if (was.containsKey(u.predicate)) {
            Node okNode = was.get(u.predicate) == 0 ? u.left : u.right;
            if (was.get(u.predicate) == 0) {
                u.right.parent = null;
            } else {
                u.left.parent = null;
            }
            okNode.parent = u.parent;
            if (isLeft) {
                u.parent.left = okNode;
            } else {
                u.parent.right = okNode;
            }
            dfs(okNode, was, isLeft);
            return;
        }
        was.put(u.predicate, 0);
        dfs(u.left, was, true);
        was.put(u.predicate, 1);
        dfs(u.right, was, false);
        was.remove(u.predicate);
    }

    public void run() {
        try {
            in = new FastScanner(new File("trees.in"));
            out = new PrintWriter(new File("trees.out"));
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