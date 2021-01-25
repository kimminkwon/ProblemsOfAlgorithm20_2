package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Zero_10773 {

    private static int k;
    private static List<Integer> nums;
    private static Stack<Integer> stack;
    private static int result;

    public static void main(String[] args) throws IOException {
        makeInput();
        stackingNumbers();
        printResult();
    }

    private static void printResult() { System.out.println(result); }

    private static void stackingNumbers() {
        nums.stream().forEach( i -> {
                    if(i == 0) stack.pop();
                    else stack.add(i);
                });
        result = stack.stream().reduce(0, Integer::sum);
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        nums = new ArrayList<>(); stack = new Stack<>();

        for(int i = 0; i < k; i++)
            nums.add(Integer.parseInt(br.readLine()));
    }
}
