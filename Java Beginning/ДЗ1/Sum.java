public class Sum {
	public static void main(String[] args) {
		int ans = 0;
		for (int i = 0; i < args.length; i++) {
			String[] tmpNum = args[i].split(" ");
			for (int j = 0; j < tmpNum.length; j++) {
				if (tmpNum[j].length() != 0) {
					try {
						ans += Long.parseLong(tmpNum[j]);
					}
					catch (NumberFormatException e) {

					}
				}
			}		
		}
		System.out.println(ans);
	}
}