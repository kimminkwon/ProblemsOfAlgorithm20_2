package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 무한문자열_12871 {

    private static String s, t;
    private static int sLength, tLength;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        sLength = s.length();
        t = br.readLine();
        tLength = t.length();

        int flag1 = findLoopString(s, sLength);
        int flag2 = findLoopString(t, tLength);
        String loop1 = flag1 == -1 ? s : s.substring(0, flag1);
        String loop2 = flag2 == -1 ? t : t.substring(0, flag2);

        System.out.println(loop1.equals(loop2) ? 1 : 0);
    }

    private static int findLoopString(String s, int length) {
        int flag = 1;
        boolean isLoop = true;
        while(flag <= length / 2) {
            if(length % flag == 0) {
                isLoop = true;
                for(int i = 0; i < length - flag; i += flag) {
                    for(int j = i; j < i + flag; j++)
                        if(s.charAt(j) != s.charAt(j + flag)) {
                            isLoop = false;
                        }
                }
                if(isLoop) break;
            }
            flag++;
        }
        return isLoop ? flag : -1;
    }
}
