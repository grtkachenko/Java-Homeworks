import java.io.*;
import java.util.*;

public class TaskEThirdVersion {
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
		int n, num;

		int[] u, v, w;

		public Graph(int n, int m) {
			u = new int[m];
			v = new int[m];
			w = new int[m];
			this.n = n;
			num = 0;
		}

		public Graph(int n, int[] u, int[] v, int[] w) {
			this.u = u;
			this.v = v;
			this.w = w;
			this.n = n;
			num = u.length;
		}

		public void add(int u, int v, int w) {
			(this.u)[num] = u;
			(this.v)[num] = v;
			(this.w)[num] = w;
			num++;
		}
	}

	void dfsTree(int u, Graph k) {
		used[u] = true;
		for (int i = 0; i < curv[u].size(); i++) {
			int cur = curv[u].get(i);
			if (!used[cur]) {
				k.add(u, cur, curw[u].get(i));
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
	TreeMap<Integer, Integer> hm = new TreeMap<Integer, Integer>();
	TreeMap<Integer, Integer> hmHelp = new TreeMap<Integer, Integer>();

	Graph getGraph(Graph graph) {
		int[] u, v, w;
		u = graph.u;
		v = graph.v;
		w = graph.w;
		int n = graph.n;

		int[] minVer = new int[n];
		Arrays.fill(minVer, Integer.MAX_VALUE);

		for (int i = 0; i < graph.num; i++) {
			minVer[v[i]] = Math.min(minVer[v[i]], w[i]);
		}
		for (int i = 0; i < n; i++) {
			curv[i].clear();
			curw[i].clear();
			curRevV[i].clear();
			curRevW[i].clear();
		}
		for (int i = 0; i < graph.num; i++) {
			if (w[i] == minVer[v[i]]) {
				curv[u[i]].add(v[i]);
				curw[u[i]].add(w[i]);
				curRevV[v[i]].add(u[i]);
				curRevW[v[i]].add(w[i]);
			}
		}

		Arrays.fill(used, false);
		ansu.clear();
		ansv.clear();
		answ.clear();
		Graph k = new Graph(n, graph.num);
		dfsTree(0, k);

		boolean f = true;
		for (int i = 0; i < n; i++) {
			f = f && used[i];
		}

		if (f) {
			return k;
		}

		k.num = 0;
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

		hm.clear();
		hmHelp.clear();
		for (int i = 0; i < graph.num; i++) {
			int tmpu = graph.u[i], tmpv = graph.v[i], tmpw = graph.w[i]
					- minVer[tmpv];

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
			k.add(uu, vv, hm.get(key));
		}
		// !!!!!!!!!!!!!!!!
		// !!!!!!!!!!!!!!!!
		k.n = newN;
		Graph gr = getGraph(k);
		Arrays.fill(used, false);

		hm.clear();
		hmHelp.clear();
		for (int i = 0; i < graph.num; i++) {
			int tmpu = graph.u[i], tmpv = graph.v[i], tmpw = graph.w[i]
					- minVer[tmpv];

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

		for (int i = 0; i < n; i++) {
			av[i].clear();
			aw[i].clear();
		}
		for (int i = 0; i < graph.num; i++) {
			if (graph.w[i] == minVer[graph.v[i]]) {
				av[graph.u[i]].add(graph.v[i]);
				aw[graph.u[i]].add(graph.w[i]);
			}
		}
		k.num = 0;
		k.n = n;
		addComponent(0, color[0], color, minVer, k);
		for (int j = 0; j < gr.num; j++) {
			int uu = gr.u[j], vv = gr.v[j], ww = gr.w[j];
			int key = uu * newN + vv;
			int val = hmHelp.get(key);
			int u1 = val / n, v1 = val - u1 * n;

			k.add(u1, v1, ww + minVer[v1]);
			addComponent(v1, color[v1], color, minVer, k);
		}
		return k;
	}

	void addComponent(int u, int startColor, int[] color, int[] minVer, Graph k) {
		used[u] = true;
		for (int i = 0; i < av[u].size(); i++) {
			int curv = av[u].get(i);
			if (!used[curv] && color[curv] == startColor) {
				k.add(u, curv, aw[u].get(i));
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
		int[] u1, v1, w1;
		u1 = new int[m];
		v1 = new int[m];
		w1 = new int[m];
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
			u1[i] = u;
			v1[i] = v;
			w1[i] = w;

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
		for (int i = 0; i < ans.num; i++) {
			sum += ans.w[i];
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
		new TaskEThirdVersion().run();
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
