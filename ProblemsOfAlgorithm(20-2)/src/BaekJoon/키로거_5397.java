package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 키로거_5397 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder("");
        for(int tc = 0; tc < T; tc++)
            sb.append(findPassword(br.readLine().toCharArray()) + "\n");
        System.out.println(sb.toString());
    }

    private static String findPassword(char[] keyLoggerArr) {
        LinkedList<Character> keyLogger = new LinkedList<>();
        int cursor = 0;
        for(char c : keyLoggerArr) {
            switch (c) {
                case '<':
                    if(cursor != 0) cursor--;
                    break;
                case '>':
                    if(cursor != keyLogger.size()) cursor++;
                    break;
                case '-':
                    if(cursor != 0 && !keyLogger.isEmpty()) keyLogger.remove(--cursor);
                    break;
                default:
                    keyLogger.add(cursor++, c);
                    break;
            }
        }
        StringBuilder sb = new StringBuilder("");
        keyLogger.stream().forEach(c -> sb.append(c));
        return sb.toString();
    }
}
