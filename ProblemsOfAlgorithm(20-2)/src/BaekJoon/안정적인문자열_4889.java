package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 안정적인문자열_4889 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");

        int n = 1;
        while(true) {
            String s = br.readLine();
            if(s.contains("-")) break;
            sb.append(n++ + ". " + findMinimumModify(s) + "\n");
        }
        System.out.print(sb.toString());
    }

    private static int findMinimumModify(String s) {
        if(s.length() == 0) return 0;

        Stack<Character> st = new Stack<>();
        int cnt = 0;
        char[] chars = s.toCharArray();

        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == '{') st.push(chars[i]);
            else {
                if(st.isEmpty()) {
                    cnt++;
                    st.push('{');
                } else st.pop();
            }
        }
        cnt += (st.size() / 2);
        return cnt;
    }
}
