package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 계단오르기_2579_2 {

    private static int numOfStair;
    private static int[] stair;
    private static int[] stairDp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numOfStair = Integer.parseInt(br.readLine());
        stair = new int[numOfStair + 1];
        stairDp = new int[numOfStair + 1];
        stair[0] = 0; stairDp[0] = 0;

        for(int i = 1; i <= numOfStair; i++)
            stair[i] = Integer.parseInt(br.readLine());

        findMaximumScoreInStair();
        System.out.println(Math.abs(stairDp[numOfStair]));
    }

    private static void findMaximumScoreInStair() {
        if(numOfStair >= 1) stairDp[1] = stair[1];
        if(numOfStair >= 2) stairDp[2] = (stairDp[1] + stair[2]) * -1;

        for(int i = 3; i <= numOfStair; i++) {
            int beforeOne = 0; int beforeTwo = 0; int beforeThree = 0;

            if(stairDp[i - 1] > 0) beforeOne = stairDp[i - 1] + stair[i];

            beforeTwo = Math.abs(stairDp[i - 2]) + stair[i];
            beforeThree = Math.abs(stairDp[i - 3]) + stair[i - 1] + stair[i];

            if(beforeTwo > beforeOne && beforeTwo > beforeThree) stairDp[i] = beforeTwo;
            else if(beforeThree > beforeOne && beforeThree > beforeTwo) stairDp[i] = beforeThree * -1;
            else stairDp[i] = beforeOne * -1;
        }
    }
}
