package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Stack_10828 {

    private static String[] commands;
    private static Stack<Integer> stack;
    private static List<Integer> results;

    public static void main(String[] args) throws IOException {
        makeInput();
        doCommand();
        printResult();
    }

    private static void printResult() { results.stream().forEach(s -> System.out.println(s)); }

    private static void doCommand() {
        for(String s : commands) {
            if(s.contains("push")) stack.push(Integer.parseInt(s.split(" ")[1]));
            else if(s.contains("pop")) results.add(stack.isEmpty() ? -1 : stack.pop());
            else if(s.contains("size")) results.add(stack.size());
            else if(s.contains("empty")) results.add(stack.isEmpty() ? 1 : 0);
            else if(s.contains("top")) results.add(stack.isEmpty() ? -1 : stack.peek());
        }
    }

    private static void makeInput() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int numOfCommand = Integer.parseInt(input.readLine());
        stack = new Stack<>(); results = new ArrayList<>();
        commands = new String[numOfCommand];

        for(int i = 0; i < numOfCommand; i++) {
            commands[i] = input.readLine();
        }
    }
}
