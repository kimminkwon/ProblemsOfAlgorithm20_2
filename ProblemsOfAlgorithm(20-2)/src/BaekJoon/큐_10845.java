package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class 큐_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder("");
        Deque<Integer> queue = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            String s = doCommand(queue, br.readLine());
            if(!s.equals("push")) sb.append(s + "\n");
        }

        System.out.println(sb.toString());
    }

    private static String doCommand(Deque<Integer> queue, String command) {
        String resultOfCommand = "push";

        if(command.contains("push")) queue.addLast(Integer.parseInt(command.split(" ")[1]));
        else if(command.contains("pop")) resultOfCommand = queue.isEmpty() ? "-1" : String.valueOf(queue.pollFirst());
        else if(command.contains("size")) resultOfCommand = String.valueOf(queue.size());
        else if(command.contains("empty")) resultOfCommand = queue.isEmpty() ? "1" : "0";
        else if(command.contains("front")) resultOfCommand = queue.isEmpty() ? "-1" : String.valueOf(queue.peekFirst());
        else if(command.contains("back")) resultOfCommand = queue.isEmpty() ? "-1" : String.valueOf(queue.peekLast());

        return resultOfCommand;
    }
}
