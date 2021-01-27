package BaekJoon;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class 괄호의값_2504 {
    private static String[] instance;
    private static int result;

    public static void main(String[] args) {
        makeInput();
        if(isCurr() == true) calculateInstance();
        printResult();
    }

    private static boolean isCurr() {
        Stack<String> stack = new Stack<>();
        for(String str : instance)
            switch (str) {
                case "(":
                    stack.push("(");
                    break;
                case "[":
                    stack.push("[");
                    break;
                case ")":
                    if(stack.isEmpty() || stack.peek() != "(") return false;
                    else stack.pop();
                    break;
                case "]":
                    if(stack.isEmpty() || stack.peek() != "[") return false;
                    else stack.pop();
                    break;
            }

        return stack.isEmpty() ? true : false;
    }

    private static void calculateInstance() {
        Stack<String> stack = new Stack<>();
        for(String str : instance) {
            int value = 0;
            switch (str) {
                case "(":
                    stack.push("(");
                    break;
                case "[":
                    stack.push("[");
                    break;
                case ")":
                    while(true) {
                        String s = stack.pop();
                        if(s == "(") {
                            stack.push(value == 0 ? "2" : String.valueOf(value * 2));
                            break;
                        } else value += Integer.parseInt(s);
                    }
                    break;
                case "]":
                    while(true) {
                        String s = stack.pop();
                        if(s == "[") {
                            stack.push(value == 0 ? "3" : String.valueOf(value * 3));
                            break;
                        } else value += Integer.parseInt(s);
                    }
                    break;
            }
        }

        result = 0;
        for(String s : stack) result += Integer.parseInt(s);
    }

    private static void printResult() { System.out.println(result); }

    public static void makeInput() {
        Scanner sc = new Scanner(System.in);
        instance = sc.next().replace(" ", "").split("");
        sc.close();
    }
}
