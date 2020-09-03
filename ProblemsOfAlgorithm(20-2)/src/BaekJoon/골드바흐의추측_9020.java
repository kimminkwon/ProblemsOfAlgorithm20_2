package BaekJoon;

import java.util.*;

public class 골드바흐의추측_9020 {
	
	static int T; // # of TestCase
	static int[] n; 
	static int[] primeNumbersArr; // 1~10000까지의 PrimeNumber 1229개
	static HashSet<Integer> primeNumbersHash; 
	
	public static void main(String[] args) {
		makeInput();
		makePrimeNumberArr();
		findAndPrintGoldbachNumbers();
	}
	
	private static void findAndPrintGoldbachNumbers() {
		for(int i = 0; i < T; i++) {
			int[] result = findGoldbachNumber(n[i]);
			System.out.printf("%d %d\n", result[0], result[1]);
		}
	}

	private static int[] findGoldbachNumber(int number) {
		int[] goldbachNum = new int[2];
		int flag = number / 2;
		int getNum = Arrays.binarySearch(primeNumbersArr, flag);
		
		if(getNum >= 0) {
			// 짝수이므로 반값과 정확히 동일한 값이 존재하면 끝
			goldbachNum[0] = primeNumbersArr[getNum];
			goldbachNum[1] = primeNumbersArr[getNum];
			return goldbachNum;
		}
		
		while(true) {
			int flagNum = primeNumbersArr[(getNum * -1) - 1];
			
			if(primeNumbersHash.contains(number - flagNum)) {
				goldbachNum[0] = Math.min(flagNum, (number - flagNum));
				goldbachNum[1] = Math.max(flagNum, (number - flagNum));
				return goldbachNum;
			}
			else {
				getNum--;
			}
		}		
	}
	
	private static void makePrimeNumberArr() {
		int cnt = 0;
		for(int i = 0; i < 10000; i++) {
			if(isPrimeNumber(i) == true) {
				primeNumbersArr[cnt] = i;
				primeNumbersHash.add(i);
				cnt++;
			}
		}
	}
	
	private static boolean isPrimeNumber(int number) {
		if(number <= 1)
			return false;
		for(int i = 2; i <= Math.sqrt(number); i++) {
			if(number % i == 0)
				return false;
		}
		return true;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		T = input.nextInt();
		n = new int[T];
		for(int i = 0; i < T; i++) {
			n[i] = input.nextInt();
		}
		primeNumbersArr = new int[1230];
		primeNumbersHash = new HashSet<Integer>();
	}

}
