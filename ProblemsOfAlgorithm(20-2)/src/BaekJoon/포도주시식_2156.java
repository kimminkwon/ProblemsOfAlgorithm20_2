package BaekJoon;

import java.util.Scanner;

public class 포도주시식_2156 {

	private static long result;
	private static int n;
	private static long[] podojus;
	
	public static void main(String[] args) {
		makeInput();
		findMaximumPodojus();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}
	
	private static void findMaximumPodojus() {
		long[] podojusDP = new long[n+1];
		for(int i = 0; i < n+1; i++) {
			if(i == 0 || i == 1)
				podojusDP[i] = podojus[i];
			else if(i == 2)
				podojusDP[i] = podojus[i-1] + podojus[i];
			else {
				// Case1) k번째를 마신다.
				// Case1-1) k-1번을 마셨다. 이 때 k-2번은 마시면 안된다. 따라서, k-3까지의 최대값 + k-1 + k
				long case1_1 = podojusDP[i-3] + podojus[i-1] + podojus[i];
				// Case1-2) k-1번을 마시지 않았다. 이 때 k-2번을 마셔도 된다. 따라서, k-2번까지의 최대값 + k
				long case1_2 = podojusDP[i-2] + podojus[i];
				// Case2) k번째를 마시지 않는다.
				// Case2-1) k-1번을 마셨다. 따라서 k-1번까지의 최대값과 동일
				long case2_1 = podojusDP[i-1];
				// Case2-2) k-1번을 마시지 않았다.따라서, k-2번까지의 최대값과 동일
				long case2_2 = podojusDP[i-2];
				
				podojusDP[i] = findMax(case1_1, case1_2, case2_1, case2_2);
			}
		}
		result = podojusDP[n];
	}

	private static long findMax(long case1_1, long case1_2, long case2_1, long case2_2) {
		return Math.max(Math.max(case1_1, case1_2), Math.max(case2_1, case2_2));
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		podojus = new long[n+1];
		podojus[0] = 0;
		for(int i = 1; i < n+1; i++) {
			podojus[i] = input.nextLong();
		}
	}

}
