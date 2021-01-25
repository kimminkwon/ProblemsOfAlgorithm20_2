package BaekJoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 스택수열_1874 {

    private static int n;
    private static List<Integer> nums;
    private static String results;

    public static void main(String[] args) throws IOException {
        makeInput();
        makePermUsingStack();
        printResult();
    }

    private static void printResult() {
        System.out.println(results);
    }

    private static void makePermUsingStack() {
        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder("");
        int count = 1;

        for(int i = 0 ; i < n; i++) {
            if(st.isEmpty()) {
                st.push(count++);
                sb.append("+\n");
            }

            if(nums.get(i) < st.peek()) break;
            while(st.peek() != nums.get(i)) {
                st.push(count++);
                sb.append("+\n");
            }

            if(st.peek() == nums.get(i)) {
                st.pop();
                sb.append("-\n");
            }
        }

        if(!st.isEmpty()) results = "NO";
        else results = sb.toString();
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new ArrayList<>();

        for(int i = 0; i < n; i++)
            nums.add(Integer.parseInt(br.readLine()));
    }
}
