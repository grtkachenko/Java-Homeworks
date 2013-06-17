public class SingletonSum {
	public static void main(String[] args) {
		int ans = 0;
		new ArrayQueueSingleton();
		for (int i = 0; i < args.length; i++) {
			String[] tmpNum = args[i].split(" ");
			for (int j = 0; j < tmpNum.length; j++) {
				if (tmpNum[j].length() != 0) {
					try {
						ArrayQueueSingleton.push(Integer.parseInt(tmpNum[j]));
					}
					catch (NumberFormatException e) {
						continue;
					}
				}
			}		
		}
		while (!ArrayQueueSingleton.isEmpty()) {
			ans += (Integer) ArrayQueueSingleton.pop();
		}
		System.out.println(ans);
	}
}
