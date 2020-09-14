package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class N과M2_15650 {

	private static int N, M;
	private static String[] colors;
	
	public static void main(String[] args) {
		makeInput();
		makeCombs();
	}

	private static void makeCombs() {
		for(int i = 1; i <= N; i++) {
			Arrays.fill(colors, "white");
			makeComb(i, "");
		}
	}

	private static void makeComb(int index, String visited) {
		colors[index] = "grey";
		visited = visited + String.valueOf(index);
		
		for(int i = index; i <= N; i++) {
			if(visited.length() == M) {
				printResult(visited);
				break;
			}
			
			if(colors[i] == "white") {
				makeComb(i, visited);
				colors[i] = "white";
			}
		}
		
		colors[index] = "black";
	}
	
	private static void printResult(String visited) {
		for(int i = 0; i < visited.length(); i++) {
			System.out.print(visited.charAt(i) + " ");
		}
		System.out.println();
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		M = input.nextInt();
		colors = new String[N+1];
	}

}
