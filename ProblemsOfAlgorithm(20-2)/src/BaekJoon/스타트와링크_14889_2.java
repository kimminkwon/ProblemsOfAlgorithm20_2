package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 스타트와링크_14889_2 {

    private static int N;
    private static int[][] skill;
    private static int diff = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        skill = new int[N + 1][N + 1];

        for(int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N + 1; j++)
                skill[i][j] = Integer.parseInt(st.nextToken());
        }

        dividedTeam(0, 0, new int[N/2]);
        System.out.println(diff);
    }

    private static void dividedTeam(int start, int count, int[] nums) {
        if(count == N/2) {
            int currDiff = calculateDiff(makeTeams(nums));
            diff = Math.min(diff, currDiff);
            return;
        }
        for(int i = start + 1; i < N; i++) {
            nums[count] = i;
            dividedTeam(i, count + 1, nums);
        }
    }

    private static int[][] makeTeams(int[] nums) {
        boolean[] check = new boolean[N + 1];
        for(int i = 0; i < nums.length; i++)
            check[nums[i]] = true;

        int cnt1 = 0, cnt2 = 0;
        int[][] teams = new int[2][N/2];
        for(int i = 1; i < N + 1; i++) {
            if(check[i]) teams[0][cnt1++] = i;
            else teams[1][cnt2++] = i;
        }
        return teams;
    }

    private static int calculateDiff(int[][] teams) {
        int value1 = 0, value2 = 0;
        for(int i = 0; i < N/2; i++) {
            for(int j = i; j < N/2; j++) {
                value1 += skill[teams[0][i]][teams[0][j]];
                value1 += skill[teams[0][j]][teams[0][i]];
                value2 += skill[teams[1][i]][teams[1][j]];
                value2 += skill[teams[1][j]][teams[1][i]];
            }
        }
        return Math.abs(value1 - value2);
    }
}
