package BaekJoon;

import java.util.Scanner;

public class 체스판다시칠하기_1018 {

	static int N, M;
	static char[][] board;
	static char[][] trueBoardOne;
	static char[][] trueBoardTwo;
	static int result;
	
	public static void main(String[] args) {
		makeInput();
		makeTrueBoards();
		findMinimumDraw();
		printResult();
	}


	private static void printResult() {
		System.out.println(result);
	}


	private static void makeTrueBoards() {
		trueBoardOne = new char[8][8];
		trueBoardTwo = new char[8][8];
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(i % 2 == 0) {
					if(j % 2 == 0)  {
						trueBoardOne[i][j] = 'W';
						trueBoardTwo[i][j] = 'B';
					}
					else {
						trueBoardOne[i][j] = 'B';
						trueBoardTwo[i][j] = 'W';
					}
						
				}
				else {
					if(j % 2 == 0) {
						trueBoardOne[i][j] = 'B';
						trueBoardTwo[i][j] = 'W';
					}
					else {
						trueBoardOne[i][j] = 'W';
						trueBoardTwo[i][j] = 'B';
					}
				}		
			}
		}
	}


	private static void findMinimumDraw() {
		result = Integer.MAX_VALUE;

		for(int i = 0; i <= N-8; i++) {
			for(int j = 0; j <= M-8; j++) {
				char[][] divideBoard = divideToPoint(i, j);
				int numOfDraw = findNumOfDraw(divideBoard);
				if(result > numOfDraw)
					result = numOfDraw;
			}
		}
	}


	private static int findNumOfDraw(char[][] divideBoard) {
		int oneCnt = 0;
		int twoCnt = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(trueBoardOne[i][j] != divideBoard[i][j])
					oneCnt++;			
				if(trueBoardTwo[i][j] != divideBoard[i][j])
					twoCnt++;
			}
		}
		return Math.min(oneCnt, twoCnt);
	}


	private static char[][] divideToPoint(int x, int y) {
		char[][] cArr = new char[8][8];
		for(int i = x; i < x+8; i++) {
			for(int j = y; j < y+8; j++) {
				cArr[i-x][j-y] = board[i][j];
			}
		}
		return cArr;
	}


	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt(); // 세로 
		M = input.nextInt(); // 가로
		board = new char[N][M];
		for(int i = 0; i < N; i++) {
			String s = input.next();
			for(int j = 0; j < M; j++)
				board[i][j] = s.charAt(j);
		}
	}
}
