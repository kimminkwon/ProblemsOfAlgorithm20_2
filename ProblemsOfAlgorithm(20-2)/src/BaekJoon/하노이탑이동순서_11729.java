package BaekJoon;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class 하노이탑이동순서_11729 {

	static int numOfHanoi;
	static ArrayList<int[]> moveSequence;
	static ArrayList<Stack<Integer>> hanoi;
	
	public static void main(String[] args) {
		makeInput();
		printHanoi(numOfHanoi, 1, 3, 2);
		printResult();
	}
	
	private static void printResult() {
		StringBuilder sb = new StringBuilder();
		sb.append(moveSequence.size());
		sb.append('\n');
		for(int i = 0; i < moveSequence.size(); i++) {			
			sb.append(String.format("%d %d\n", moveSequence.get(i)[0], moveSequence.get(i)[1]));
		}
		System.out.print(sb);
	}

	private static void printHanoi(int n, int from, int post, int sub) {
		if(n == 1) { // 1개밖에 없을 경우 바로 from으로 옮긴다.
			move(from, post);
		}
		else {
			printHanoi((n-1), from, sub, post); // n-1개의 원반을 sub로 이동시켜야 한다.
			move(from, post); // from에 남은 n번째 원반을 post으로 이동시킨다.
			printHanoi((n-1), sub, post, from); // sub에 옮긴 n-1개의 원반을 post로 이동시켜야 한다.
		}
	}

	private static void move(int from, int post) {
		hanoi.get(post).push(hanoi.get(from).pop());
		int[] moving = {from, post};
		moveSequence.add(moving);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		numOfHanoi = input.nextInt();
		moveSequence = new ArrayList<int[]>();

		hanoi = new ArrayList<Stack<Integer>>();
		hanoi.add(new Stack<Integer>());
		hanoi.add(new Stack<Integer>());
		hanoi.add(new Stack<Integer>());
		hanoi.add(new Stack<Integer>());
		
		for(int i = numOfHanoi; i >= 1; i--) {
			hanoi.get(1).push(i);
		}
		
		
	}

}
