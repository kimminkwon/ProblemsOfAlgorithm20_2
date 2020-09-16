package BaekJoon;

import java.util.ArrayList;
import java.util.Scanner;

public class 스도쿠_2580 {

	private static int[][] finalSdokuBoard;
	private static ArrayList<String> zeroIndexs;
	
	public static void main(String[] args) {
		fillSdokuBoard(makeInput(), 0);
		printResult();
	}
	
	
	private static void fillSdokuBoard(int[][] sdokuBoard, int index) {
		if(isEnd(sdokuBoard) == true) {
			saveResult(sdokuBoard);
			//System.out.println("완성!!!!!!!");
			return;
		}
		
		String s = zeroIndexs.get(index);
		
		int x = Integer.parseInt(String.valueOf(s.charAt(0)));
		int y = Integer.parseInt(String.valueOf(s.charAt(1)));
		//System.out.println("해당 인덱스를 기준으로 체크 시작 = (" + x + ", " + y + ")");
		for(int i = 1; i < 10; i++) {
			sdokuBoard[x][y] = i;
			//System.out.println("	(" + x + ", " + y + ")이 " + i + "일 때");
			//printResult_other(sdokuBoard);
			if(isCorrectBoard(sdokuBoard, x, y) == true) {
				if(isEnd(sdokuBoard) == true) {
					saveResult(sdokuBoard);
					//System.out.println("완성!!!!!!!");
					return;
				}
				else fillSdokuBoard(sdokuBoard, index + 1);
			}
				
			sdokuBoard[x][y] = 0;
		}
		
	}


	private static void saveResult(int[][] sdokuBoard) {
		finalSdokuBoard = new int[9][9];
		for(int i = 0; i < 9; i++) 
			for(int j = 0; j < 9; j++)
				finalSdokuBoard[i][j] = sdokuBoard[i][j];
	}


	private static boolean isCorrectBoard(int[][] sdokuBoard, int x, int y) {
		int checkNum = sdokuBoard[x][y];
		// 1. 가로 체크
		for(int i = 0; i < 9; i++) {
			if(sdokuBoard[x][i] == checkNum && i != y)
				return false;
		}
		//System.out.println("가로 문제 없음");
		// 2. 세로 체크
		for(int i = 0; i < 9; i++) {
			if(sdokuBoard[i][y] == checkNum && i != x)
				return false;
		}
		//System.out.println("세로 문제 없음");
		// 3. 같은 3*3 박스 체크
		int w, h;
		if(x < 3)
			w = 0;
		else if (x < 6)
			w = 3;
		else 
			w = 6;
		
		if(y < 3)
			h = 0;
		else if (y < 6)
			h = 3;
		else 
			h = 6;
		

		//System.out.println("(" + w + ", " + h + ")로 부터 체크 시작");
		for(int i = w; i < w + 3; i++)
			for(int j = h; j < h + 3; j++)
				if(sdokuBoard[i][j] == checkNum)
					if(i != x && j != y)
						return false;
		
		//System.out.println("박스도 문제 없음");
		// 다 문제 없다면 정상 숫자
		return true;
	}


	private static boolean isEnd(int[][] sdokuBoard) {
		for(int i = 0; i < 9; i++) 
			for(int j = 0; j < 9; j++)
				if(sdokuBoard[i][j] == 0)
					return false;
		return true;
	}
	
	private static void printResult() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(finalSdokuBoard[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void printResult_other(int[][] sdokuBoard) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(sdokuBoard[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int[][] makeInput() {
		Scanner input = new Scanner(System.in);
		int[][] sdokuBoard = new int[9][9];
		
		zeroIndexs = new ArrayList<String>();
		
		for(int i = 0; i < 9; i++) 
			for(int j = 0; j < 9; j++)
				sdokuBoard[i][j] = input.nextInt();
		makeZeroIndexs(sdokuBoard);
		
		return sdokuBoard;
		
	}


	private static void makeZeroIndexs(int[][] sdokuBoard) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(sdokuBoard[i][j] == 0) {
					zeroIndexs.add(String.valueOf(i) + String.valueOf(j));
				}
			}
		}
		// System.out.println(zeroIndexs);
	}

}
