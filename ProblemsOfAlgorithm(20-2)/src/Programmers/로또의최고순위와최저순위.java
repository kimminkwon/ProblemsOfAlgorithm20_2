package Programmers;

import java.util.Arrays;

public class 로또의최고순위와최저순위 {

    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};

        makeMaxScore(lottos, win_nums);

        System.out.println(Arrays.toString(makeMaxScore(lottos, win_nums)));
    }

    private static int[] makeMaxScore(int[] lottos, int[] win_nums) {
        boolean[] winNums = new boolean[46], myNums = new boolean[46];
        for(int winNum : win_nums) winNums[winNum] = true;

        int passNum = 0; // 현재 내가 몇개나 맞았는가?
        for(int myNum : lottos) {
            if(winNums[myNum]) passNum++;
            myNums[myNum] = true;
        }
        // 현재 passNum의 개수가 내 최저점이다.

        int addPassNum = 0;
        for(int i = 0; i < 6; i++) {
            if(lottos[i] == 0) { // 0은 무조건 정답으로 만들 수 있다.
                addPassNum++;
            }
        }

        return new int[]{getScore(addPassNum + passNum), getScore(passNum)};
    }

    private static int getScore(int cnt) {
        if(cnt == 6) return 1;
        else if(cnt == 5) return 2;
        else if(cnt == 4) return 3;
        else if(cnt == 3) return 4;
        else if(cnt == 2) return 5;
        else return 6;
    }



}
