package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class N과M3_15651 {

	private static int N, M;
	private static String[] colors;
	
	public static void main(String[] args) {
		makeInput();
		makeCombsAllowDuplication();
	}

	private static void makeCombsAllowDuplication() {
		for(int i = 1; i <= N; i++) {
			Arrays.fill(colors, "white");
			makeComb(i, "");
		}
	}

	private static void makeComb(int index, String visited) {
		// colors[index] = "grey";
		visited = visited + String.valueOf(index);
		
		for(int i = 1; i <= N; i++) {
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
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < visited.length(); i++) {			
			sb.append(visited.charAt(i) + " ");
		}
		sb.append('\n');
		System.out.print(sb);	
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		M = input.nextInt();
		colors = new String[N+1];
	}

}
