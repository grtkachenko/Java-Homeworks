import java.io.*;
import java.util.*;

public class TaskENew {
	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;
	int n, m;
	boolean[] used;

	class Item {
		int v, w;

		Item(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	void dfs(int u) {
		used[u] = true;
		for (Item tmp : to[u]) {
			if (!used[tmp.v]) {
				dfs(tmp.v);
			}
		}
	}

	class Graph {
		int n;
		ArrayList<Integer> u, v, w;

		public Graph(int n) {
			u = new ArrayList<Integer>();
			v = new ArrayList<Integer>();
			w = new ArrayList<Integer>();
			this.n = n;
		}

		public Graph(int n, ArrayList<Integer> u, ArrayList<Integer> v,
				ArrayList<Integer> w) {
			this.u = u;
			this.v = v;
			this.w = w;
			this.n = n;
		}
	}

	void dfsTree(int u, Graph k) {
		used[u] = true;
		for (int i = 0; i < curv[u].size(); i++) {
			int cur = curv[u].get(i);
			if (!used[cur]) {
				k.u.add(u);
				k.v.add(cur);
				k.w.add(curw[u].get(i));
				dfsTree(cur, k);
			}
		}
	}

	void dfs1(int u) {
		used[u] = true;
		for (int i = 0; i < curv[u].size(); i++) {
			int cur = curv[u].get(i);
			if (!used[cur]) {
				dfs1(cur);
			}
		}
		order.add(u);
	}

	void dfs2(int u) {
		used[u] = true;
		component.add(u);
		for (int i = 0; i < curRevV[u].size(); i++) {
			int cur = curRevV[u].get(i);
			if (!used[cur]) {
				dfs2(cur);
			}
		}
	}

	ArrayList<Integer> order = new ArrayList<Integer>();
	ArrayList<Integer> component = new ArrayList<Integer>();
	ArrayList<Item>[] in, to;

	ArrayList<Integer>[] curv, curw, curRevV, curRevW, av, aw;

	ArrayList<Integer> ansu = new ArrayList<Integer>();
	ArrayList<Integer> ansv = new ArrayList<Integer>();
	ArrayList<Integer> answ = new ArrayList<Integer>();

	Graph getGraph(Graph graph) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> hmHelp = new HashMap<Integer, Integer>();
		ArrayList<Integer> u, v, w;
		u = graph.u;
		v = graph.v;
		w = graph.w;
		int n = graph.n;

		int[] minVer = new int[n];
		Arrays.fill(minVer, Integer.MAX_VALUE);

		for (int i = 0; i < w.size(); i++) {
			minVer[v.get(i)] = Math.min(minVer[v.get(i)], w.get(i));
		}
		for (int i = 0; i < n; i++) {
			curv[i].clear();
			curw[i].clear();
			curRevV[i].clear();
			curRevW[i].clear();
		}

		for (int i = 0; i < w.size(); i++) {
			if (w.get(i) == minVer[v.get(i)]) {
				curv[u.get(i)].add(v.get(i));
				curw[u.get(i)].add(w.get(i));
				curRevV[v.get(i)].add(u.get(i));
				curRevW[v.get(i)].add(w.get(i));
			}
		}

		Arrays.fill(used, false);
		ansu.clear();
		ansv.clear();
		answ.clear();
		Graph k = new Graph(n);
		dfsTree(0, k);

		boolean f = true;
		for (int i = 0; i < n; i++) {
			f = f && used[i];
		}

		if (f) {
			return k;
		}

		k.u.clear();
		k.v.clear();
		k.w.clear();
		order.clear();
		component.clear();
		Arrays.fill(used, false);
		for (int i = 0; i < n; i++) {
			if (!used[i]) {
				dfs1(i);
			}
		}
		Arrays.fill(used, false);
		int[] color = new int[n];

		int curColor = 0;
		for (int i = 0; i < n; i++) {
			int vv = order.get(n - 1 - i);
			if (!used[vv]) {
				dfs2(vv);
				for (Integer cur : component) {
					color[cur] = curColor;
				}
				curColor++;
				component.clear();
			}
		}
		int newN = 0;
		if (color[0] != 0) {
			int tmp = color[0];
			for (int i = 0; i < n; i++) {
				newN = Math.max(newN, color[i] + 1);
				if (color[i] == tmp) {
					color[i] = 0;
				} else {
					if (color[i] == 0) {
						color[i] = tmp;
					}
				}
			}
		}

		ArrayList<Integer> helpu = k.u;
		ArrayList<Integer> helpv = k.v;
		ArrayList<Integer> helpw = k.w;

		hm.clear();
		hmHelp.clear();
		for (int i = 0; i < graph.u.size(); i++) {
			int tmpu = graph.u.get(i), tmpv = graph.v.get(i), tmpw = graph.w
					.get(i) - minVer[tmpv];

			if (color[tmpu] != color[tmpv]) {
				int val = color[tmpu] * newN + color[tmpv];
				if (!hm.containsKey(val)) {
					hm.put(val, tmpw);
					hmHelp.put(val, tmpu * n + tmpv);
				} else {
					if (tmpw < hm.get(val)) {
						hm.put(val, tmpw);
						hmHelp.put(val, tmpu * n + tmpv);
					}
				}
			}
		}
		for (Integer key : hm.keySet()) {
			int uu = key / newN;
			int vv = key - uu * newN;
			helpu.add(uu);
			helpv.add(vv);
			helpw.add(hm.get(key));
		}
		// !!!!!!!!!!!!!!!!
		// !!!!!!!!!!!!!!!!
		k.n = newN;
		Graph gr = getGraph(k);
		Arrays.fill(used, false);

		for (int i = 0; i < n; i++) {
			av[i].clear();
			aw[i].clear();
		}
		for (int i = 0; i < graph.u.size(); i++) {
			if (graph.w.get(i) == minVer[graph.v.get(i)]) {
				av[graph.u.get(i)].add(graph.v.get(i));
				aw[graph.u.get(i)].add(graph.w.get(i));
			}
		}
		k.v.clear();
		k.u.clear();
		k.w.clear();
		k.n = n;
		addComponent(0, color[0], color, minVer, k);
		for (int j = 0; j < gr.u.size(); j++) {
			int uu = gr.u.get(j), vv = gr.v.get(j), ww = gr.w.get(j);
			int key = uu * newN + vv;
			int val = hmHelp.get(key);
			int u1 = val / n, v1 = val - u1 * n;

			k.u.add(u1);
			k.v.add(v1);
			k.w.add(ww + minVer[v1]);
			addComponent(v1, color[v1], color, minVer, k);
		}
		return k;
	}

	void addComponent(int u, int startColor, int[] color, int[] minVer, Graph k) {
		used[u] = true;
		for (int i = 0; i < av[u].size(); i++) {
			int curv = av[u].get(i);
			if (!used[curv] && color[curv] == startColor) {
				k.u.add(u);
				k.v.add(curv);
				k.w.add(aw[u].get(i));
				addComponent(curv, startColor, color, minVer, k);
			}
		}
	}

	@SuppressWarnings("unchecked")
	void solve() throws IOException {
		n = nextInt();
		m = nextInt();
		in = new ArrayList[n];
		to = new ArrayList[n];
		ArrayList<Integer> u1, v1, w1;
		u1 = new ArrayList<Integer>();
		v1 = new ArrayList<Integer>();
		w1 = new ArrayList<Integer>();
		curv = new ArrayList[n];
		curw = new ArrayList[n];
		curRevV = new ArrayList[n];
		curRevW = new ArrayList[n];
		av = new ArrayList[n];
		aw = new ArrayList[n];

		used = new boolean[n];
		for (int i = 0; i < n; i++) {
			in[i] = new ArrayList<Item>();
			to[i] = new ArrayList<Item>();
			curv[i] = new ArrayList<Integer>();
			curw[i] = new ArrayList<Integer>();
			curRevV[i] = new ArrayList<Integer>();
			curRevW[i] = new ArrayList<Integer>();
			av[i] = new ArrayList<Integer>();
			aw[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			int u = nextInt() - 1, v = nextInt() - 1, w = nextInt();
			to[u].add(new Item(v, w));
			in[v].add(new Item(u, w));
			u1.add(u);
			v1.add(v);
			w1.add(w);

		}
		dfs(0);

		for (int i = 0; i < n; i++) {
			if (!used[i]) {
				out.println("NO");
				return;
			}
		}

		Graph ans = getGraph(new Graph(n, u1, v1, w1));
		long sum = 0;
		for (Integer j : ans.w) {
			sum += j;
		}
		out.println("YES");
		out.print(sum);
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("chinese.in"));
		out = new PrintWriter("chinese.out");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		// br = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		solve();
		br.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		// Locale.setDefault(Locale.US);
		new TaskENew().run();
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