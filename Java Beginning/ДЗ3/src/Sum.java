import java.util.Random;
public class Sum {
	public static void main(String[] args) {
		Queue q;
		for (int i = 0; i < args.length; i++) {
			Random help = new Random();
			int ans = 0;
			if (help.nextBoolean()) {
				q = new ArrayQueue();
			} else {
				q = new LinkedQueue();
			}
			String[] tmpNum = args[i].split(" ");
			for (int j = 0; j < tmpNum.length; j++) {
				if (tmpNum[j].length() != 0) {
					try {
						q.push(Integer.parseInt(tmpNum[j]));
					} catch (NumberFormatException e) {
						continue;
					}
				}
			}
			while (!q.isEmpty()) {
				ans += (Integer) q.pop();
			}
			System.out.println(ans);
		}
	}
}
