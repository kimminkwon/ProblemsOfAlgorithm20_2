package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ATM_11399 {

	private static int n;
	private static List<Integer> timeList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		makeInput();
		findMinimumTimesAndPrint();
	}

	private static void findMinimumTimesAndPrint() {
		System.out.println(timeList.stream().sorted().map(i -> i * n--).reduce(0, Integer::sum));
	}

	private static void makeInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		Arrays.stream(br.readLine().split(" ")).forEach(s -> timeList.add(Integer.parseInt(s)));
	}
}
