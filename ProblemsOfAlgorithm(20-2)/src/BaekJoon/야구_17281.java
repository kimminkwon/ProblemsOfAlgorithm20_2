package BaekJoon;

import java.io.*;
import java.util.*;

public class 야구_17281 {

    private static int N, result;
    private static int[][] inningScore;
    private static int[] perm = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inningScore = new int[N][9];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++)
                inningScore[i][j] = Integer.parseInt(st.nextToken());
        }
        permutation(0, 1, new int[9]);
        System.out.println(result);
    }

    private static void permutation(int cnt, int flag, int[] makePerm) {
        if(cnt == 3) {
            makePerm[cnt] = perm[0];
            permutation(cnt + 1, flag | 1, makePerm);
        } else if(cnt == 9) {
            getScore(makePerm);
            return;
        } else {
            for(int i = 1; i < 9; i++) {
                if((flag & 1 << i) != 0) continue;
                else {
                    makePerm[cnt] = perm[i];
                    permutation(cnt + 1, flag | 1 << i, makePerm);
                }
            }
        }
    }

    private static void getScore(int[] makePerm) {
        int score = 0; int pointer = 0;
        int[] ground = new int[3];
        for (int i = 0; i < N; i++) {
            ground[0] = 0; ground[1] = 0; ground[2] = 0;
            int out = 0; int currScore = 0;
            while(true) {
                int shoot = inningScore[i][makePerm[pointer++]];
                if(shoot == 0) out++;
                else if(shoot == 1) {
                    currScore = moveBase(ground, currScore, 1);
                } else if(shoot == 2) {
                    currScore = moveBase(ground, currScore, 1);
                    currScore = moveBase(ground, currScore, 0);
                } else if(shoot == 3) {
                    currScore = moveBase(ground, currScore, 1);
                    currScore = moveBase(ground, currScore, 0);
                    currScore = moveBase(ground, currScore, 0);
                } else if(shoot == 4) {
                    currScore = moveBase(ground, currScore, 1);
                    currScore = moveBase(ground, currScore, 0);
                    currScore = moveBase(ground, currScore, 0);
                    currScore = moveBase(ground, currScore, 0);
                }
                if(pointer >= 9) pointer = 0;
                if(out >= 3) break;
            }
            score += currScore;
        }
        result = Math.max(result, score);
    }

    private static int moveBase(int[] ground, int currScore, int num) {
        if (ground[2] == 1) currScore++;
        ground[2] = ground[1];
        ground[1] = ground[0];
        ground[0] = num;
        return currScore;
    }
}