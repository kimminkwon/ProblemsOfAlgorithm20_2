package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class 스타트와링크_14889 {

	private static int n, result;
	private static int[][] skills;
	private static Stack<Integer> startTeam;
	private static Stack<Integer> linkTeam;
	
	
	public static void main(String[] args) {
		makeInput();
		makeTeams();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void makeTeams() {
		for(int i = 0; i < n; i++) {
			startTeam.clear(); linkTeam.clear();
			makeTeam(i, 0, 0);
		}
	}

	private static void makeTeam(int num, int startTeamNum, int linkTeamNum) {
		if(num == n + 1) {
			if(startTeamNum == n/2 && linkTeamNum == n/2) {
				makeBalance();
			}
			return;
		}
		
		startTeam.push(num);
		makeTeam(num+1, startTeamNum+1, linkTeamNum);
		startTeam.pop();
		
		linkTeam.push(num);
		makeTeam(num+1, startTeamNum, linkTeamNum+1);
		linkTeam.pop();
	}
	
	private static void makeBalance() {

		int startTeamValue = 0; int linkTeamValue = 0;
		for(int i = 0; i < n/2; i++) {
			for(int j = i; j < n/2; j++) {
				int start_one = startTeam.get(i) - 1; int start_two = startTeam.get(j) - 1;
				int link_one = linkTeam.get(i) - 1; int link_two = linkTeam.get(j) - 1;
				startTeamValue = startTeamValue + skills[start_one][start_two] + skills[start_two][start_one];
				linkTeamValue = linkTeamValue + skills[link_one][link_two] + skills[link_two][link_one];
			}
		}
		
		isMinumum(Math.abs(startTeamValue - linkTeamValue));
	}

	private static void isMinumum(int value) {
		result = result < value? result : value;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		skills = new int[n][n]; result = Integer.MAX_VALUE;
		startTeam = new Stack<>(); linkTeam = new Stack<>();
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				skills[i][j] = input.nextInt();
	}

}
