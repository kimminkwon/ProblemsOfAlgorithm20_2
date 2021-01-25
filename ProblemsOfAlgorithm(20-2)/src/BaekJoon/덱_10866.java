package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 덱_10866 {

    private static String[] commands;
    private static Deque<Integer> deque;
    private static List<Integer> results;

    public static void main(String[] args) throws IOException {
        makeInput();
        doCommand();
        printResult();
    }

    private static void makeInput() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int numOfCommand = Integer.parseInt(input.readLine());
        deque = new ArrayDeque<>(); results = new ArrayList<>();
        commands = new String[numOfCommand];

        for(int i = 0; i < numOfCommand; i++) {
            commands[i] = input.readLine();
        }
    }

    private static void doCommand() {
        for(String s : commands) {
            if(s.contains("push_back")) deque.offerLast(Integer.parseInt(s.split(" ")[1]));
            else if(s.contains("push_front")) deque.offerFirst(Integer.parseInt(s.split(" ")[1]));
            else if(s.contains("pop_back")) results.add(deque.isEmpty() ? -1 : deque.pollLast());
            else if(s.contains("pop_front")) results.add(deque.isEmpty() ? -1 : deque.pollFirst());
            else if(s.contains("size")) results.add(deque.size());
            else if(s.contains("empty")) results.add(deque.isEmpty() ? 1 : 0);
            else if(s.contains("front")) results.add(deque.isEmpty() ? -1 : deque.peekFirst());
            else if(s.contains("back")) results.add(deque.isEmpty() ? -1 : deque.peekLast());
        }
    }

    private static void printResult() { results.stream().forEach(s -> System.out.println(s)); }
}
