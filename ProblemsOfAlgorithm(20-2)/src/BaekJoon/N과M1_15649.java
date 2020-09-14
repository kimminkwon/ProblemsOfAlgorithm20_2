package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class N과M1_15649 {

	static int N, M; 
	static String[] colors;
	
	public static void main(String[] args) {
		makeInput();
		makePerms();
	}

	private static void makePerms() {
		for(int i = 1; i <= N; i++) {
			Arrays.fill(colors, "white");
			makePerm(i, 0, "");
		}
	}

	private static void makePerm(int index, int length, String visited) {
		colors[index] = "gray";
		length++;
		visited = visited + String.valueOf(index);
		// System.out.println(index + " 에서 출발! 현재 길이: " + length);
		for(int i = 1; i <= N; i++) {
			if(length == M) {
				// System.out.println("길이 초과!");
				printResult(visited);
				break;
			}
			if(colors[i] == "white") {
				// System.out.println(i + "를 방문");
				makePerm(i, length, visited);
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
		Arrays.fill(colors, "white");
	}

}
