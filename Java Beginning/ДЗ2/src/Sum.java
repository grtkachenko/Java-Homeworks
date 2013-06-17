import java.util.Random;
public class Sum {
	public static void main(String[] args) {
		int ans = 0;
		ArrayQueue q1 = new ArrayQueue();
		ArrayQueue q2 = new ArrayQueue();
		
		for (int i = 0; i < args.length; i++) {
			String[] tmpNum = args[i].split(" ");
			for (int j = 0; j < tmpNum.length; j++) {
				if (tmpNum[j].length() != 0) {
					try {
						Random help = new Random();
						
						if (help.nextBoolean()) {
							q1.push(Integer.parseInt(tmpNum[j]));
						} else {
							q2.push(Integer.parseInt(tmpNum[j]));
						}
					}
					catch (NumberFormatException e) {
						continue;
					}
				}
			}		
		}
		while (!q1.isEmpty()) {
			ans += (Integer) q1.pop();
		}
		System.out.println(ans);
		ans = 0;
		while (!q2.isEmpty()) {
			ans += (Integer) q2.pop();
		}
		System.out.println(ans);
		
	}
}
