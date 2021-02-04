import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class 괄호짝짓기_D4_1218 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_괄호짝짓기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for(int tc = 1; tc <= T; tc++) 
        	System.out.println("#" + tc + " " + isCorrectParenthesis(Integer.parseInt(br.readLine()), br.readLine()));
	}

	private static int isCorrectParenthesis(int N, String parenthesis) {
		Stack<Character> st = new Stack<>();
		for(char p : parenthesis.toCharArray()) { 
			switch(p) {
			case '(': case '[': case '{': case '<':
				st.push(p);
				break;
			case ')':
				if(!st.isEmpty() && st.peek() == '(') st.pop();
				else return 0;
				break;
			case ']':
				if(!st.isEmpty() && st.peek() == '[') st.pop();
				else return 0;
				break;
			case '}':
				if(!st.isEmpty() && st.peek() == '{') st.pop();
				else return 0;
				break;
			case '>':
				if(!st.isEmpty() && st.peek() == '<') st.pop();
				else return 0;
				break;
			}
		}
		return st.isEmpty() ? 1 : 0;
	}
}
