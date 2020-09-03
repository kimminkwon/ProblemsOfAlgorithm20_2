package BaekJoon;

import java.util.Scanner;

public class 직사각형에서탈출_1085 {
	
	static int x, y; // 한수 위치
	static int w, h; // 직사각형의 오른쪽 끝 점
	
	public static void main(String[] args) {
		makeInput();
		PrintResult(findMinimumLength());
	}

	private static void PrintResult(int minimumLength) {
		System.out.println(minimumLength);
	}

	private static int findMinimumLength() {
		// 사각형의 네 변과의 거리를 모두 구한 후, 가장 가까운 거리를 찾는다.
		int x_left = x; int x_right = w - x;
		int y_up = h - y; int y_down = y;

		return Math.min( Math.min(x_left, x_right), Math.min(y_up, y_down) );
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		x = input.nextInt(); y = input.nextInt();
		w = input.nextInt(); h = input.nextInt();
	}
}
