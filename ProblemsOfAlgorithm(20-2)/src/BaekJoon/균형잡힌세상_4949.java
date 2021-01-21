package BaekJoon;

import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 균형잡힌세상_4949 {
    private static List<String> results, strings;

    public static void main(String[] args) throws IOException {
        makeInput();

        for(String str : strings)
            results.add(isBalanceString(str) ? "yes" : "no");

        printResult(results);
    }

    private static void makeInput() throws IOException {
        strings = new ArrayList<>();
        results = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String s = br.readLine();
            if(s.equals(".")) break;
            strings.add(s);
        }
        br.close();
    }

    private static void printResult(List<String> results) {
        results.stream().forEach(s -> System.out.println(s));
    }

    private static boolean isBalanceString(String str) {
        char[] charArr = str.toCharArray();
        Stack<Character> buffer = new Stack<>();

        for(char c : charArr) {
            if(c == '(' || c == '[') {
                buffer.push(c);
            } else if (c == ')') {
                if(buffer.isEmpty()) return false;
                else if(buffer.peek() == '[') return false;
                else if(buffer.peek() == '(') buffer.pop();
                else buffer.push(c);
            } else if (c == ']') {
                if(buffer.isEmpty()) return false;
                else if(buffer.peek() == '(') return false;
                else if(buffer.peek() == '[') buffer.pop();
                else buffer.push(c);
            }
        }

        return buffer.isEmpty() ? true : false;
    }
}
