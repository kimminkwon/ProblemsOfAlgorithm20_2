package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ATM_11399 {

	private static int n;
	private static int[] times;
	private static List<Integer> timeList;
	private static int result;

	public static void main(String[] args) {
		makeInput();
		sortedTimes();
		findMinumumTimes();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void findMinumumTimes() {
		IntStream.range(0, n).forEach( i -> result = result + (timeList.get(i) * (n - i)) );
	}

	private static void sortedTimes() {
		Collections.sort(timeList);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		timeList = new ArrayList<Integer>();
		IntStream.range(0, n).forEach(i -> timeList.add(input.nextInt()));
		
	}

}
