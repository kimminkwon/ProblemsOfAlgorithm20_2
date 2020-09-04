package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 별찍기10_2447 {

	static int N;
	static char[][] printChar;
	
	public static void main(String[] args) {
		makeInput();
		makePrintChar(N, 0, 0);
		printResult();
	}
	
	private static void printResult() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {			
			for(int j = 0; j < N; j++) {
				sb.append(printChar[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb);	
	}

	private static void makePrintChar(int n, int x1, int y1) {
		if(n == 3) {
			printChar[x1][y1] = '*'; printChar[x1][y1+1] = '*'; printChar[x1][y1+2] = '*';
			printChar[x1+1][y1] = '*'; printChar[x1+1][y1+2] = '*';
			printChar[x1+2][y1] = '*'; printChar[x1+2][y1+1] = '*'; printChar[x1+2][y1+2] = '*';
		}
		else {
			int flag = n / 3;
			int point_x = x1 + flag; int point_y = y1 + flag;
			for(int x = x1; x < x1 + n; x = x + flag) // x좌표 변화
				for(int y = y1; y < y1 + n; y = y + flag) {
					if(x != point_x || y != point_y) {
						makePrintChar(flag, x, y);
					}
				}
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		printChar = new char[N][N];
		for(int i = 0; i < printChar.length; i++) {
			Arrays.fill(printChar[i], ' ');
		}
	}

}
