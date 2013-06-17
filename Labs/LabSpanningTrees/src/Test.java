import java.util.ArrayList;
import java.util.HashMap;

public class Test {

	class Graph {
		int n;
		int[] u, v, w;

		public Graph(int n) {
			u = new int[10000];
			v = new int[10000];
			w = new int[10000];
			this.n = n;
		}

	}

	void run() {

		int mb = 1024 * 1024;

		// Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();

		HashMap<Integer, Integer> a = new HashMap<Integer, Integer>();
		for (int i = 0; i < 1000; i++) {
			a.clear();
			for (int j = 0; j < 10000; j++) {
				a.put(j, j);
			}
		}

		// Graph[] a = new Graph[1000];
		// for (int i = 0; i < 1000; i++) {
		// a[i] = new Graph(1000);
		//
		// }
		// for (int i = 0; i < 10000000; i++) {
		// }

		System.out.println("##### Heap utilization statistics [MB] #####");

		// Print used memory
		System.out.println("Used Memory:"
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

		// Print free memory
		System.out.println("Free Memory:" + runtime.freeMemory() / mb);

		// Print total available memory
		System.out.println("Total Memory:" + runtime.totalMemory() / mb);

		// Print Maximum available memory
		System.out.println("Max Memory:" + runtime.maxMemory() / mb);
	}

	public static void main(String[] args) {
		new Test().run();
	}
}
