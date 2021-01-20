package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class UserStack {
    private int numOfCommand;
    private String[] commands;
    private List<Integer> stack;

    public UserStack(int numOfCommand, String[] commands) {
        this.numOfCommand = numOfCommand;
        this.commands = commands;
        stack = new ArrayList<>();
    }

    public List<Integer> getResult() {
        List<Integer> results = new ArrayList<>();

        for(String s : commands) {
            if(s.contains("push")) push(Integer.parseInt(s.split(" ")[1]));
            else if(s.contains("pop")) results.add(pop());
            else if(s.contains("size")) results.add(size());
            else if(s.contains("empty")) results.add(empty());
            else if(s.contains("top")) results.add(top());
        }

        return results;
    }

    private void push(int num) { stack.add(num); }

    private int pop() { return stack.size() == 0 ? -1 : stack.remove(stack.size() - 1); }

    private int size() { return stack.size(); }

    private int empty() { return stack.size() == 0 ? 1 : 0; }

    private int top() { return stack.size() == 0 ? -1 : stack.get(stack.size() - 1); }
}

public class 스택_10828 {
    private static UserStack stack;

    public static void main(String[] args) throws IOException {
        makeInput();
        printResult(stack.getResult());
    }

    private static void printResult(List<Integer> result) { result.stream().forEach(s -> System.out.println(s)); }

    private static void makeInput() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int numOfCommand = Integer.parseInt(input.readLine());
        String[] commands = new String[numOfCommand];

        for(int i = 0; i < numOfCommand; i++) {
            commands[i] = input.readLine();
        }
        stack = new UserStack(numOfCommand, commands);
    }
}
