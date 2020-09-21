package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 연산자끼워넣기_14888 {

	private static int n;
	private static int[] numbers;
	private static char[] operatorsChar;
	private static int maxResult, minResult;
	
	public static void main(String[] args) {
		makeInput();
		boolean[] selected = new boolean[n-1];
		operatorsPerm(selected, "");
		printResult();
	}
	
	private static void operatorsPerm(boolean[] selected, String operatorArr) {
	
		if(operatorArr.length() == (n-1)) {
			operationNumbers(operatorArr);
			return;
		}
		
		for(int i = 0; i < n-1; i++) {
			if(selected[i] == false) {
				selected[i] = true;
				String operatorArrBox = operatorArr + operatorsChar[i];
				operatorsPerm(selected, operatorArrBox);
				selected[i] = false;
			}
		}
	}
	
	
	private static void operationNumbers(String operatorArr) {
		int num = operationNumber(operatorArr.charAt(0), numbers[0], numbers[1]);
		for(int i = 1; i < numbers.length - 1; i++) {
			num = operationNumber(operatorArr.charAt(i), num, numbers[i+1]);
		}
		updateResult(num);
	}
	
	private static void updateResult(int num) {
		if(num > maxResult) maxResult = num;
		if(num < minResult) minResult = num;
	}

	private static int operationNumber(char oper, int num1, int num2) {
		switch(oper) {
		case '+':
			return num1+num2;
		case '-':
			return num1-num2;
		case '*':
			return num1*num2;
		case '/':
			return num1/num2;
		default:
			return num1+num2;
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		numbers = new int[n];
		int[] operators = new int[4];
		operatorsChar = new char[n-1];
		maxResult = Integer.MIN_VALUE;
		minResult = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) 
			numbers[i] = input.nextInt();
	
		for(int i = 0; i < 4; i++) 
			operators[i] = input.nextInt();
		
		makeNumberCharArr(operators);
	}
	
	private static void makeNumberCharArr(int[] operators) {
		int cnt = 0;
		for(int i = 0; i < operators[0]; i++) {
			operatorsChar[cnt] = '+';
			cnt++;
		}
		for(int i = 0; i < operators[1]; i++) {
			operatorsChar[cnt] = '-';
			cnt++;
		}
		for(int i = 0; i < operators[2]; i++) {
			operatorsChar[cnt] = '*';
			cnt++;
		}
		for(int i = 0; i < operators[3]; i++) {
			operatorsChar[cnt] = '/';
			cnt++;
		}
	}

	private static void printResult() {
		System.out.println(maxResult);
		System.out.println(minResult);
	}


}
