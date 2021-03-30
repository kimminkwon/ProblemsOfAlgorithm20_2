package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 회문_17609 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            String str = br.readLine();
            int length = str.length();
            sb.append(isPalindrome(0, length - 1, str, false)).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static int isPalindrome(int left, int right, String str, boolean isDelete) {
        while(left <= right) {
            if(str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                int res = 2;
                if(str.charAt(left + 1) == str.charAt(right) && !isDelete)
                    res = Math.min(res, isPalindrome(left + 1, right, str, true));
                if(str.charAt(left) == str.charAt(right - 1) && !isDelete)
                    res = Math.min(res, isPalindrome(left, right - 1, str, true));
                if(str.charAt(left + 1) != str.charAt(right) && str.charAt(left) != str.charAt(right - 1))
                    return 2;
                return res;
            }
        }
        return isDelete ? 1 : 0;
    }

}
