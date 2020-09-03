package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 직각삼각형_4153 {

	static int numOfTestCase;
	static ArrayList<int[]> triangles;
	static ArrayList<String> results;
			
	public static void main(String[] args) {
		makeInput();
		sorting();
		checkIsRightAngles();
		printResult();
	}
	
	private static void printResult() {
		for(String s : results) {
			System.out.println(s);
		}
	}

	private static void checkIsRightAngles() {
		for(int i = 0; i < triangles.size(); i++) {
			results.add(isRightAngle(triangles.get(i)));
		}
	}

	private static String isRightAngle(int[] triangle) {
		double partOne = (Math.pow(triangle[0], 2) + Math.pow(triangle[1], 2));
		double partTwo = (Math.pow(triangle[2], 2));
		if(partOne == partTwo)
			return "right";
		else
			return "wrong";
	}

	private static void sorting() {
		for(int i = 0; i < triangles.size(); i++) {
			Arrays.sort(triangles.get(i));
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		triangles = new ArrayList<int[]>();
		results = new ArrayList<String>();
		while(true) {
			int a, b, c;
			a = input.nextInt(); b = input.nextInt(); c = input.nextInt(); 
			if(a == 0 && b == 0 && c == 0)
				break;
			int[] triangle = {a, b, c};
			triangles.add(triangle);
		}
	}

}
