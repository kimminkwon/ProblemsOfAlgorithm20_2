package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 세친구_10096 {
    private static int n;
    private static char[] chars;

    public static void main(String[] args) throws IOException {
        makeInput();
        System.out.println(findDuplicationWord());
    }

    private static String findDuplicationWord() {
        if(n % 2 == 0) return "NOT POSSIBLE";
        int midIndex = n / 2;
        String res1 = duplicationTwoString(Arrays.copyOfRange(chars, 0, midIndex), Arrays.copyOfRange(chars, midIndex, n));
        String res2 = duplicationTwoString(Arrays.copyOfRange(chars, midIndex + 1, n), Arrays.copyOfRange(chars, 0, midIndex + 1));

        if(!res1.equals("NOT POSSIBLE") && res2.equals("NOT POSSIBLE")) return res1;
        else if(res1.equals("NOT POSSIBLE") && !res2.equals("NOT POSSIBLE")) return res2;
        else if(!res1.equals("NOT POSSIBLE") && !res2.equals("NOT POSSIBLE")) {
            return res1.equals(res2) ? res1 : "NOT UNIQUE";
        }
        else return "NOT POSSIBLE";
    }

    private static String duplicationTwoString(char[] splitCharsShort, char[] splitCharsLong) {
        int index1  = 0; int index2 = 0; int count = 0;
        while(index1 < splitCharsShort.length) {
            if(splitCharsShort[index1] != splitCharsLong[index2]) {
                if(count > 0) return "NOT POSSIBLE";
                count++;
                index1--;
            }
            index1++; index2++;
        }
        return String.valueOf(splitCharsShort);
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        chars = br.readLine().toCharArray();
    }
}
