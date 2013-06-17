import java.io.*;
import java.util.*;

public class ADTSum {
	public static void main(String[] args) {
		int ans = 0;
		ArrayQueueADT q1 = new ArrayQueueADT();
		ArrayQueueADT q2 = new ArrayQueueADT();
		
		for (int i = 0; i < args.length; i++) {
			String[] tmpNum = args[i].split(" ");
			for (int j = 0; j < tmpNum.length; j++) {
				if (tmpNum[j].length() != 0) {
					try {
						Random help = new Random();
						
						if (help.nextBoolean()) {
							ArrayQueueADT.push(q1, Integer.parseInt(tmpNum[j]));
						} else {
							ArrayQueueADT.push(q2, Integer.parseInt(tmpNum[j]));
						}
					}
					catch (NumberFormatException e) {
						continue;
					}
				}
			}		
		}
		while (!ArrayQueueADT.isEmpty(q1)) {
			ans += (Integer) ArrayQueueADT.pop(q1);
		}
		System.out.println(ans);
		ans = 0;
		while (!ArrayQueueADT.isEmpty(q2)) {
			ans += (Integer) ArrayQueueADT.pop(q2);
		}
		System.out.println(ans);
		
	}
}
