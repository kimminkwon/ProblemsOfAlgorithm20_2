package BaekJoon;

import java.util.Scanner;

public class 택시기하학_3053 {
	
	static int R;
	static double euclideanValue;
	static double manhattanValue;
	
	public static void main(String[] args) {
		makeInput();
		makeEuclideanAndManhattanValues();
		printResult();
	}

	private static void printResult() {
		System.out.printf("%.6f\n", euclideanValue);
		System.out.printf("%.6f\n", manhattanValue);
	}

	private static void makeEuclideanAndManhattanValues() {
		manhattanValue = calculateManhattan();
		euclideanValue = calculateEuclidean();
	}

	private static double calculateManhattan() {
		return (Math.pow(R, 2) * 2);
	}

	private static double calculateEuclidean() {
		return (Math.pow(R, 2) * Math.PI);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		R = input.nextInt();
	}

}
