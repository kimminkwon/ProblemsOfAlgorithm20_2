package BaekJoon;

import java.util.Scanner;

public class 돌게임3_9657 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(winnerOfStoneGameThree(input.nextInt()));
        input.close();
    }

    private static String winnerOfStoneGameThree(int n) {
        boolean[] skStart = new boolean[n+1];
        boolean[] ckStart = new boolean[n+1];

        for(int i = 1; i <= n; i++) {
            if(i < 5)
                if(i == 2) {
                    skStart[i] = false;
                    ckStart[i] = false;
                } else {
                    skStart[i] = true;
                    ckStart[i] = true;
                }
            else {
                skStart[i] = !(ckStart[i-1] && ckStart[i-3] && ckStart[i-4]);
                ckStart[i] = !(skStart[i-1] && skStart[i-3] && skStart[i-4]);
            }
        }
        return skStart[n] ? "SK" : "CY";
    }
}
