import java.util.*;
import java.io.*;

public class TaskH {

	BufferedReader br;
	PrintWriter out;
	StringTokenizer stok;
	final int MAXEXECUTION = (int) 2e5, MOD = 65536, ASCII = 256;

	String nextToken() throws IOException {
		String s = null;
		while (stok == null || !stok.hasMoreTokens()) {
			s = br.readLine();
			if (s == null) {
				return "-1";
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

	class Queue {
		private long[] data, regData;
		private int head, tail;

		Queue() {
			head = tail = 0;
			data = new long[MAXEXECUTION];
			regData = new long[26];
		}

		public void put(long x) {
			data[tail++] = x % MOD;
		}

		public long get() {
			return data[head++];
		}

		public void add() {
			put((get() + get()) % MOD);
		}

		public void sub() {
			long x = get(), y = get();
			put((x - y + MOD) % MOD);
		}

		public void mul() {
			put((get() * get()) % MOD);
		}

		public void div() {
			long x = get(), y = get();
			if (y == 0) {
				put(0);
			} else {
				put(x / y);
			}
		}

		public void mod() {
			long x = get(), y = get();
			if (y == 0) {
				put(0);
			} else {
				put(x % y);
			}
		}

		public void pushReg(char reg) {
			regData[(int) (reg - 'a')] = get();
		}

		public long getReg(char reg) {
			return regData[(int) (reg - 'a')];
		}

		public void putReg(char reg) {
			put(regData[(int) (reg - 'a')]);
		}

		public void print() throws IOException {
			out.println(get());
		}

		public void printReg(char reg) throws IOException {
			out.println(getReg(reg));
		}

		public void printC() throws IOException {
			out.print((char) (get() % ASCII));
		}

		public void printRegC(char reg) throws IOException {
			out.print((char) (getReg(reg) % ASCII));
		}
	}

	class Label implements Comparable<Label> {
		String name;
		int num;

		Label(String name, int num) {
			this.name = name;
			this.num = num;
		}

		public int compareTo(Label a) {
			if (a == null) {
				return -1;
			}
			return this.name.compareTo(a.name);
		}
	}

	Label[] labels = new Label[MAXEXECUTION];
	int numLabels = 0;

	public int getLabel(String label) {
		int l = 0, r = numLabels - 1, m = 0;
		while (l <= r) {
			m = (l + r) / 2;
			if (labels[m].name.compareTo(label) < 0) {
				l = m + 1;
				continue;
			}
			if (labels[m].name.compareTo(label) > 0) {
				r = m - 1;
				continue;
			}
			return labels[m].num;

		}
		return labels[m].num;
	}

	void solve() throws IOException {
		Queue q = new Queue();
		String[] command = new String[MAXEXECUTION];

		int numCom = 0;
		while (true) {
			String s = nextToken();
			if (s == "-1")
				break;
			command[numCom] = s;
			if (s.charAt(0) == ':') {
				String label = s.substring(1);
				labels[numLabels] = new Label(label, numCom);
				numLabels++;
			}
			numCom++;
		}

		Arrays.sort(labels, 0, numLabels);
		int curCom = 0;
		while (curCom < numCom) {
			if (command[curCom].charAt(0) == '+') {
				q.add();
				curCom++;
				continue;
			}
			if (command[curCom].charAt(0) == '-') {
				q.sub();
				curCom++;
				continue;
			}
			if (command[curCom].charAt(0) == '*') {
				q.mul();
				curCom++;
				continue;
			}
			if (command[curCom].charAt(0) == '/') {
				q.div();
				curCom++;
				continue;
			}
			if (command[curCom].charAt(0) == '%') {
				q.mod();
				curCom++;
				continue;
			}
			if (command[curCom].charAt(0) == '>') {
				q.pushReg(command[curCom].charAt(1));
				curCom++;
				continue;
			}
			if (command[curCom].charAt(0) == '<') {
				q.putReg(command[curCom].charAt(1));
				curCom++;
				continue;
			}
			if (command[curCom].charAt(0) == 'P') {
				if (command[curCom].length() == 1) {
					q.print();
				} else {
					q.printReg(command[curCom].charAt(1));
				}
				curCom++;
				continue;
			}
			if (command[curCom].charAt(0) == 'C') {
				if (command[curCom].length() == 1) {
					q.printC();
				} else {
					q.printRegC(command[curCom].charAt(1));
				}
				curCom++;
				continue;
			}
			if (command[curCom].charAt(0) == ':') {
				curCom++;
				continue;
			}
			if (command[curCom].charAt(0) == 'J') {
				String label = command[curCom].substring(1);
				curCom = getLabel(label);
				continue;
			}
			if (command[curCom].charAt(0) == 'Z') {
				char reg = command[curCom].charAt(1);
				String label = command[curCom].substring(2);
				if (q.getReg(reg) == 0) {
					curCom = getLabel(label);
				} else {
					curCom++;
				}
				continue;
			}
			if (command[curCom].charAt(0) == 'E') {
				char reg1 = command[curCom].charAt(1);
				char reg2 = command[curCom].charAt(2);
				String label = command[curCom].substring(3);
				if (q.getReg(reg1) == q.getReg(reg2)) {
					curCom = getLabel(label);
				} else {
					curCom++;
				}
				continue;
			}
			if (command[curCom].charAt(0) == 'G') {
				char reg1 = command[curCom].charAt(1);
				char reg2 = command[curCom].charAt(2);
				String label = command[curCom].substring(3);
				if (q.getReg(reg1) > q.getReg(reg2)) {
					curCom = getLabel(label);
				} else {
					curCom++;
				}
				continue;
			}
			if (command[curCom].charAt(0) == 'Q') {
				break; // fuf
			}
			q.put(Integer.parseInt(command[curCom]));
			curCom++;
		}
	}

	void run() throws IOException {
		br = new BufferedReader(new FileReader("quack.in"));
		out = new PrintWriter("quack.out");
		// br = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		solve();
		br.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		// Locale.setDefault(Locale.US);
		new TaskH().run();
	}
}
