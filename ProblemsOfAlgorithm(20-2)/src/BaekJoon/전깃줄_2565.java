package BaekJoon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class ConnectedState implements Comparable {
	private int start, end;

	public ConnectedState(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() { return start; }

	public int getEnd() { return end; }

	@Override
	public int compareTo(Object o) {
		ConnectedState otherC = (ConnectedState)o;
		if(this.start > otherC.getStart())
			return 1;
		else if(this.start < otherC.getStart())
			return -1;
		else 
			return 0;
	}

	@Override
	public String toString() {
		return "("+ this.start + " to " + this.end + ")";
	}
	
	
}

public class 전깃줄_2565 {

	private static int n; // 전기줄의 개수
	private static ConnectedState[] connect;
	private static int result;
	

	public static void main(String[] args) {
		makeInput();
		findMaximumConnection();
		printResult();
	}

	private static void printResult() {
		System.out.println(n - result);
	}

	private static void findMaximumConnection() {
		result = 0; 
		int[] dpConnection = new int[n+1];
		
		for(int i = 1; i < n+1; i++) {
			dpConnection[i] = 1;
			for(int j = 1; j < i; j++) {
				if(connect[j].getEnd() < connect[i].getEnd() && dpConnection[i] < dpConnection[j] + 1) {
					dpConnection[i] = dpConnection[j] + 1;
				}
			}
			result = result < dpConnection[i] ? dpConnection[i] : result;
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		connect = new ConnectedState[n+1];
		connect[0] = new ConnectedState(Integer.MIN_VALUE, Integer.MIN_VALUE);
		for(int i = 1; i < n+1; i++) {
			connect[i] = new ConnectedState(input.nextInt(), input.nextInt());
		}
		Arrays.sort(connect);
	}
}
