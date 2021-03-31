package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 즐거운단어_2922 {

    private static char[] word;
    private static long result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine().toCharArray();
        boolean isL = false;
        for(int i = 0; i < word.length; i++) {
            if(word[i] == 'L') isL = true;
            if(word[i] == '_') continue;
            if(isMo(word[i])) word[i] = 'M';
            else word[i] = 'J';
        }

        findCaseForGoodWord(word, 0, 1, isL);
        System.out.println(result);
    }

    private static void findCaseForGoodWord(char[] word, int index, long numOfCase, boolean isL) {
        if(index == word.length) {
            if(isGoodWord(word) && isL) result += numOfCase;
            return;
        }
        if(word[index] == '_') {
            word[index] = 'J';
            findCaseForGoodWord(word, index + 1, numOfCase * 20, isL);
            findCaseForGoodWord(word, index + 1, numOfCase * 1, true);
            word[index] = 'M';
            findCaseForGoodWord(word, index + 1, numOfCase * 5, isL);
        } else findCaseForGoodWord(word, index + 1, numOfCase, isL);
    }

    private static boolean isGoodWord(char[] word) {
        int numOfJa = 0, numOfMo = 0;
        for(int i = 0; i < word.length; i++) {
            if(word[i] == 'M') {
                numOfJa = 0;
                numOfMo++;
            } else {
                numOfJa++;
                numOfMo = 0;
            }
            if(numOfJa >= 3 || numOfMo >= 3) return false;
        }
        return true;
    }

    private static boolean isMo(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
