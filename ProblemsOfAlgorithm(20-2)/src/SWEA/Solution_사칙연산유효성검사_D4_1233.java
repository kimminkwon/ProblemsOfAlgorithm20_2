import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_사칙연산유효성검사_D4_1233 {
	
	private static int N;
	private static String[] operTree;
	private static int isCorrect;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_사칙연산유효성검사.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        
        for(int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	operTree = new String[N + 1];
        	isCorrect = 1;
        	
        	for(int i = 0; i < N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
            	operTree[Integer.parseInt(st.nextToken())] = st.nextToken();
        	}
        	isCorrectOperationUsingInOrder(1);
        	System.out.println("#" + tc + " " + isCorrect);
        }
        br.close();
	}

	private static void isCorrectOperationUsingInOrder(int n) {
		int leftIndex = n * 2; 
		int rightIndex = n * 2 + 1;
		
		if((leftIndex > N && rightIndex > N) && isOper(operTree[n])) { // 1. leaf이다. 무조건 숫자!
			isCorrect = 0;
			return;
		} else if((leftIndex > N && rightIndex <= N) || (leftIndex <= N && rightIndex > N)) { // 2. 무조건 두개의 자식이 존재해야한다.
			isCorrect = 0;
			return;
		} else if(!isOper(operTree[n])) { // 3. leaf도 아니고, 두개의 자식도 다 있다면 반드시 연산자여야한다. 
			isCorrect = 0;
			return;
		}
		isCorrectOperationUsingInOrder(leftIndex);
		isCorrectOperationUsingInOrder(rightIndex);
	}
	
	private static boolean isOper(String s) {
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
	}
}
