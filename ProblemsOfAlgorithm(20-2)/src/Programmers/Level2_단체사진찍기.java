package Programmers;

import java.util.*;
import java.io.*;

public class Level2_단체사진찍기 {

    private static Map<String, Integer> hm;
    private static int NUM_OF_FRIENDS;
    private static int result;

    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};

        NUM_OF_FRIENDS = 8;
        result = 0;
        hm = new HashMap<>();
        String[] friendsName = {"A", "C", "F", "J", "M", "N", "R", "T"};
        for(int i = 0; i < NUM_OF_FRIENDS; i++) hm.put(friendsName[i], i);

        findPermOfFriends(data, 0, new boolean[NUM_OF_FRIENDS], new int[NUM_OF_FRIENDS]);
        System.out.println(result);
    }

    private static void findPermOfFriends(String[] data, int cnt, boolean[] visited, int[] perm) {
        if(cnt == 8) {
            if(isFitAllConditions(data, perm)) result++;
            return;
        }

        for(int i = 0; i < NUM_OF_FRIENDS; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            perm[cnt] = i;
            findPermOfFriends(data, cnt + 1, visited, perm);
            visited[i] = false;
        }
    }

    private static boolean isFitAllConditions(String[] data, int[] perm) {
        for(String condition : data) {
            int fIdx1 = hm.get(String.valueOf(condition.charAt(0))), fIdx2 = hm.get(String.valueOf(condition.charAt(2)));
            int fNum1 = 0, fNum2 = 0;
            for(int i = 0; i < NUM_OF_FRIENDS; i++) {
                if(perm[i] == fIdx1) fNum1 = i;
                else if(perm[i] == fIdx2) fNum2 = i;
            }
            if(!isFitCondition(fNum1, fNum2, condition.charAt(3), Character.getNumericValue(condition.charAt(4)))) return false;
        }
        return true;
    }

    private static boolean isFitCondition(int fNum1, int fNum2, char oper, int length) {
        int gap = Math.abs(fNum1 - fNum2) - 1;
        switch (oper) {
            case '=':
                if(gap == length) return true;
                break;
            case '<':
                if(gap < length) return true;
                break;
            case '>':
                if(gap > length) return true;
                break;
        }
        return false;
    }
}
