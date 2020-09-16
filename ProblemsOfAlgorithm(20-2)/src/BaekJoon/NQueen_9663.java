package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class NQueen_9663 {

	private static int N;
	private static long result;

	public static void main(String[] args) {
		makeInput();
		checkNumOfQueenArrangements();
		printResult();
	}

	
	private static void checkNumOfQueenArrangements() {
		boolean[][] checkerBoard = new boolean[N][N];
		checkNumOfQueenArrangement(0, checkerBoard);
	}


	private static void printResult() {
		System.out.println(result);
	}

	private static void checkNumOfQueenArrangement(int numOfQ, boolean[][] checkerBoard) {
		if(numOfQ == N) {
			result++;
			return;
		}
		
		for(int i = 0; i < N; i++) { // i-th 행에 뒀을 때 확인
			checkerBoard[i][numOfQ] = true;
			if(isAttackingQ(checkerBoard, i, numOfQ) == false) {
				checkNumOfQueenArrangement(numOfQ+1, checkerBoard);
			}
			checkerBoard[i][numOfQ] = false;
		}
	}

	private static boolean isAttackingQ(boolean[][] checkerBoard, int numOfQ, int QLocation) {
		// 세로, 가로 체크
		for(int i = 0; i < N; i++) {
			if(checkerBoard[numOfQ][i] == true && i != QLocation)
				return true;
			if(checkerBoard[i][QLocation] == true && i != numOfQ)
				return true;
		}
		
		// 대각선 4방향 체크
		int w = numOfQ - 1; int h = QLocation - 1;
		while(w >= 0 && h >= 0) {
			if(checkerBoard[w][h] == true)
				return true;
			w--; h--;
		}
		
		w = numOfQ + 1; h = QLocation + 1;
		while(w < N && h < N) {
			if(checkerBoard[w][h] == true)
				return true;
			w++; h++;
		}
		
		w = numOfQ - 1; h = QLocation + 1;
		while(w >= 0 && h < N) {
			if(checkerBoard[w][h] == true)
				return true;
			w--; h++;
		}		
		
		w = numOfQ + 1; h = QLocation - 1;
		while(w < N && h >= 0) {
			if(checkerBoard[w][h] == true)
				return true;
			w++; h--;
		}	
		
		return false;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
	}

}
