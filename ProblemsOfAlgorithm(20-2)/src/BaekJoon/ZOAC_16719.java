package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ZOAC_16719 {

    private static class Word {
        String s; int index;

        public Word(String s, int index) {
            this.s = s;
            this.index = index;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();

        System.out.print(makeOrderedStrings(chars));
    }

    private static String makeOrderedStrings(char[] chars) {
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[chars.length];

        for(int length = 0; length < chars.length; length++) {
            List<Word> words = new ArrayList<>();
            for(int i = 0; i < chars.length; i++) {
                if(!used[i]) {
                    used[i] = true;
                    Word currWord = new Word("", i);
                    for(int j = 0; j < chars.length; j++) {
                        if(used[j]) currWord.s += chars[j];
                    }
                    words.add(currWord);
                    used[i] = false;
                }
            }
            Collections.sort(words, (o1, o2) -> o1.s.compareTo(o2.s));
            used[words.get(0).index] = true;
            sb.append(words.get(0).s).append("\n");
        }
        return sb.toString();
    }
}
