package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 좋은수열_2661 {

    private static String[] perms;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        perms = new String[81]; // N은 최대 800개

        findMinimumGoodPerm(1, "");
        System.out.println(perms[N]);
    }

    private static void findMinimumGoodPerm(int depth, String currStr) {
        if(depth > 80) return;
        if(perms[80] != null) return;

        for(int i = 1; i < 4; i++) {
            String newStr = currStr + String.valueOf(i);
            if(isGoodPerm(newStr, depth)) {
                if(perms[depth] == null) perms[depth] = newStr;
                findMinimumGoodPerm(depth + 1, newStr);
            }
        }
    }

    private static boolean isGoodPerm(String perm, int length) {
        for(int size = 1; size <= length / 2; size++) { // 이 Size만큼을 잘라 체크한다.
            for(int i = 0; i <= length - (size * 2); i++) { // i번째 index부터 Size만큼 잘라서 확인한다.
                String subStr1 = perm.substring(i, i + size);
                String subStr2 = perm.substring(i + size, i + (size * 2));
                if(subStr1.equals(subStr2)) return false;
            }
        }
        return true;
    }
}
