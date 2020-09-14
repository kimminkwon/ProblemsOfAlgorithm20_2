package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 통계학_2108 {

	static int N;
	static int[] numbers;
	static double average;
	static int centerValue;
	static int maxFrequencyValue;
	static int range;
	
	public static void main(String[] args) {
		makeInput();
		sortedNumbers();
		makeValues();
		printResult();
	}

	private static void makeValues() {
		average = makeAverage();
		centerValue = findCenterValue();
		maxFrequencyValue = findMaxFrequencyValue();
		range = makeRange();
	}

	private static int makeRange() {
		return numbers[N-1] - numbers[0];
	}

	private static int findMaxFrequencyValue() {
		int[] hashNumbers = new int[8010];
		Arrays.fill(hashNumbers, 0);
		
		// Hash를 통해 빈도 저장
		for(int i = 0; i < N; i++) {
			int index = numbers[i] + 4000;
			hashNumbers[index]++;
		}
		
		// 최대 빈도 인덱스 찾기
		int flag = 0;
		int index = -1;
		for(int i = 0; i < hashNumbers.length; i++) {
			if(hashNumbers[i] > flag) {
				flag = hashNumbers[i];
				index = i;
			}
		}
		
		// 같은 빈도인 값이 있는지 확인
		ArrayList<Integer> box = new ArrayList<Integer>();
		for(int i = 0; i < hashNumbers.length; i++) {
			if(flag == hashNumbers[i]) {
				box.add(i);
			}
		}
		
		return box.size() > 1? box.get(1) - 4000 : index - 4000;
	}

	private static int findCenterValue() {
		return numbers[N/2];
	}

	private static double makeAverage() {
		int sum = 0;
		for(int i = 0; i < N; i++)
			sum = sum + numbers[i];
		
		return (double)sum / (double)N;
	}

	private static void printResult() {
		System.out.printf("%.0f\n", average);
		System.out.printf("%d\n", centerValue);
		System.out.printf("%d\n", maxFrequencyValue);
		System.out.printf("%d\n", range);
	}

	private static void sortedNumbers() {
		Arrays.sort(numbers);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		numbers = new int[N];
		
		for(int i = 0; i < N; i++)
			numbers[i] = input.nextInt();
	}

}
