package BaekJoon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Coordinate2 implements Comparable {
	private int x, y;

	public Coordinate2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() { return x; }

	public int getY() { return y; }

	@Override
	public int compareTo(Object o) {
		Coordinate2 otherC = (Coordinate2)o;
		if(this.y > otherC.getY())
			return 1;
		else if(this.y < otherC.getY())
			return -1;
		else {
			if(this.x > otherC.getX())
				return 1;
			else if(this.x < otherC.getX())
				return -1;
			else
				return 0;
		}
	}

	@Override
	public String toString() {
		return this.x + " " + this.y;
	}
	
	
}

public class 좌표정렬하기2_11651 {

	static int N;
	static Coordinate2[] coors;
	
	public static void main(String[] args) {
		makeInput();
		sortedCoors();
		printCoorsList();
	}

	private static void printCoorsList() {
		for(int i = 0; i < N; i++) 
			System.out.println(coors[i]);
	}

	private static void sortedCoors() {
		Arrays.sort(coors);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		coors = new Coordinate2[N];
		
		for(int i = 0; i < N; i++) {
			int x = input.nextInt(); int y = input.nextInt();
			coors[i] = new Coordinate2(x, y);
		}
	}

}
