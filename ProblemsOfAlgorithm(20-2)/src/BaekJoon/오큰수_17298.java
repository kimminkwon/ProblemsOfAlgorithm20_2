package BaekJoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class 오큰수_17298 {
    private static Stack<Integer> stack;
    private static int n;
    private static List<Integer> numList;
    private static int[] results;

    public static void main(String[] args) throws IOException {
        makeInput();
        searchNGE();
        printResult();
    }

    private static void printResult() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < n; i++)
            bw.write(results[i] + " ");
        bw.write("\n");
        bw.flush();
    }

    private static void searchNGE() {
        stack.push(0);
        for(int i = 1; i < n; i++) {
            if(stack.isEmpty()) stack.push(i);

            while(!stack.isEmpty() && numList.get(stack.peek()) < numList.get(i))
                results[stack.pop()] = numList.get(i);

            stack.push(i);
        }
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        stack = new Stack<>(); results = new int[n]; numList = new ArrayList<>();
        Arrays.fill(results, -1);
        Arrays.stream(br.readLine().split(" ")).forEach(s -> numList.add(Integer.parseInt(s)));
    }

}
