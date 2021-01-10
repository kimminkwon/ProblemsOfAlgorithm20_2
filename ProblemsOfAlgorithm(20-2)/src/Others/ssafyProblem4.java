package Others;
/*
4. m개의 원소를 가진 집합에서 n개의 원소를 가진 집합으로 가는 전사함수를 모두 출력하는 프로그램을 작성하라.
출력을 어떻게 하는 것이 적절할지 생각해보아야한다.
*/

import java.util.Arrays;
import java.util.Scanner;

public class ssafyProblem4 {

    private static int m, n;

    public static void main(String[] args) {
        makeInput();
        int[] surjState = new int[m];
        Arrays.fill(surjState, -1);
        makeSurjection(surjState, 0);
    }

    private static void makeSurjection(int[] surjState, int flag) {
        if(flag == m) {
            if(isSurjection(surjState)) {
                printState(surjState);
            }
            return;
        }

        for(int i = 0; i < n; i++) {
            surjState[flag] = i;
            makeSurjection(surjState.clone(), flag + 1);
            surjState[flag] = -1;
        }
    }

    private static boolean isSurjection(int[] surjState) {
        boolean[] check = new boolean[n];
        for(int i : surjState) {
            check[i] = true;
        }
        return isAllTrue(check);
    }

    private static boolean isAllTrue(boolean[] check) {
        for(boolean b : check) {
            if(b == false) return false;
        }
        return true;
    }

    private static void printState(int[] surjState) {
        String printStr = "";
        for(int i = 0; i < surjState.length; i++) {
            printStr = printStr + "(" + i + " to " + surjState[i] + ") ";
        }
        System.out.println(printStr);
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        m = input.nextInt();
        n = input.nextInt();
    }
}
