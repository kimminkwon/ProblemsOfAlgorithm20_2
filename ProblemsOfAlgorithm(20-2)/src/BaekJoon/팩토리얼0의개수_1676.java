package BaekJoon;

import java.util.Scanner;

public class 팩토리얼0의개수_1676 {

    private static int n;
    private static int result;
    
    public static void main(String[] args) {
        makeInput();
        findFirstZeroForDesc();
        printResult();
    }

    private static void printResult() {
        System.out.println(result);
    }

    private static void findFirstZeroForDesc() {
        int cntFive = 0;

        for(int i = 1; i <= n; i++) {
            if(i % 5 == 0)
                cntFive++;
            if(i % Math.pow(5, 2) == 0)
                cntFive++;
            if(i % Math.pow(5, 3) == 0)
                cntFive++;
        }

        result = cntFive;
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
    }
}
