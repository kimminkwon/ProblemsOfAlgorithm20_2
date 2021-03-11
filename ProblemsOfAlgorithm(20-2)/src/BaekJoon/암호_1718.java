package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 암호_1718 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        String keyBox = br.readLine();
        String key = "";
        while(key.length() < str.length())
            key += keyBox;

        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' ') {
                sb.append(" ");
                continue;
            }
            int num = str.charAt(i) - key.charAt(i);
            int charNum = num <= 0 ? (int)('z') + num : num + 97 - 1;
            sb.append((char) (charNum));
        }
        System.out.println(sb.toString());
    }
}
