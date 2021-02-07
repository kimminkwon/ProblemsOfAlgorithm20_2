package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 계산기2_D4_1223 {
    
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_계산기2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println("#" + tc + " " + doCalculate(N, br.readLine()));
		}
		br.close();
	}

	private static int doCalculate(int N, String beforeExpression) {
		Queue<String> afterExpression = inFixToPostFix(N, beforeExpression);
		return doCalculateUsingPostFix(afterExpression);
	}

	private static int doCalculateUsingPostFix(Queue<String> afterExpression) {
		Stack<Integer> st = new Stack<>();
		while(!afterExpression.isEmpty()) {
			String c = afterExpression.poll();
			if(c.equals("*") ||c.equals("+")) {
				int num2 = st.pop();
				int num1 = st.pop();
				st.push(c.equals("*") ? (num1 * num2) : (num1 + num2));
			} else st.push(Integer.parseInt(c));
		}
		return st.pop();
	}

	private static Queue<String> inFixToPostFix(int N, String beforeExpression) {
		Stack<String> operator = new Stack<>();
		Queue<String> afterExpression = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			String c = String.valueOf(beforeExpression.charAt(i));
			if(c.equals("*")) {
				if(operator.isEmpty() || operator.peek().equals("+")) operator.push(c);
				else {
					afterExpression.offer(operator.pop());
					operator.push(c);
				}
			} else if(c.equals("+")) {
				if(operator.isEmpty()) operator.push(c);
				else {
					afterExpression.offer(operator.pop());
					operator.push(c);
				}
			} else afterExpression.offer(c);
		}
		while(!operator.isEmpty()) afterExpression.offer(operator.pop());
		
		return afterExpression;
	}
}
