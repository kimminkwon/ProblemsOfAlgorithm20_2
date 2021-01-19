package BaekJoon;

import java.util.Scanner;

public class 큰수AplusB_10757 {
    private static String strNum1, strNum2;
    private static int[] result;

    public static void main(String[] args) {
        makeInput();
        doPlus();
        printResult();
    }

    private static void printResult() {
        int len = result.length;
        if(result[result.length - 1] == 0) len--;

        for(int i = len - 1; i >= 0; i--) {
            System.out.print(result[i]);
        }
    }

    private static void doPlus() {
        int[] num1 = strNum1.length() > strNum2.length() ? makeIntArray(strNum1) : makeIntArray(strNum2);
        int[] num2 = strNum1.length() > strNum2.length() ? makeIntArray(strNum2) : makeIntArray(strNum1);

        result = new int[num1.length + 1];

        for(int i = 0; i < num2.length; i++)
            num1[i] += num2[i];

        for(int i = 0; i < num1.length; i++) {
            if(num1[i] >= 10) {
                if(i >= num1.length - 1) {
                    result[i+1]++;
                } else {
                    num1[i+1]++;
                }
            }
            result[i] = num1[i] % 10;
        }
    }

    // 계산의 편의를 위해 뒤집어 반환
    private static int[] makeIntArray(String strNum1) {
        int[] numArr = new int[strNum1.length()];

        for(int i = 0; i < strNum1.length(); i++) {
            numArr[strNum1.length() - i - 1] = strNum1.charAt(i) - '0';
        }
        return numArr;
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        strNum1 = input.next();
        strNum2 = input.next();
    }
}
