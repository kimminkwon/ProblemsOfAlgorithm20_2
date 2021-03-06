package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 괄호_9012 {

    private static int t;
    private static char[][] parenthesis;
    private static List<String> results;

    public static void main(String[] args) throws IOException {
        makeInput();
        makeResults();
        printResults();
    }

    private static void printResults() {
        results.stream().forEach(s -> System.out.println(s));
    }

    private static void makeResults() {
        for(int i = 0; i < t; i++)
            results.add(isVPS(parenthesis[i]));
    }

    private static String isVPS(char[] parenthesis) {
        int flag = 0;
        for(char p : parenthesis) {
            if(p == '(') flag++;
            else flag--;

            if(flag < 0) return "NO";
        }

        return flag == 0 ? "YES" : "NO";
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        parenthesis = new char[t][];
        results = new ArrayList<>();

        for(int i = 0; i < t; i++)
            parenthesis[i] = br.readLine().toCharArray();
    }
}
