package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class 후위표기식_1918 {

    private static HashMap<Character, Integer> hs = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        String postExp = "";

        hs.put('*', 1); hs.put('/', 1);
        hs.put('+', 0); hs.put('-', 0);
        hs.put('(', -1); hs.put(')', -1);

        Stack<Character> st = new Stack<>();
        for(char c : exp.toCharArray()) {
            if(c == '(') st.push(c);
            else if(c == '*' || c == '/' || c == '+' || c == '-') {
                if(st.isEmpty()) st.push(c);
                else {
                    while (!st.isEmpty() && hs.get(st.peek()) >= hs.get(c)) {
                        postExp += String.valueOf(st.pop());
                    }
                    st.push(c);
                }
            }
            else if(c == ')') {
                while(!st.isEmpty() && st.peek() != '(') postExp += String.valueOf(st.pop());
                st.pop();
            }
            else postExp += String.valueOf(c);
        }
        while(!st.isEmpty()) postExp += String.valueOf(st.pop());
        System.out.println(postExp);
    }
}
