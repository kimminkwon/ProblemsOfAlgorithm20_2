package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 애너그램_6443 {

    private static StringBuilder sb;
    private static int[] alphabet;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            char[] word = br.readLine().toCharArray();
            Arrays.sort(word);
            alphabet = new int[26];
            for(char c : word) alphabet[c - 'a']++;
            combinationOfWord(word.length, 0, new char[word.length]);
        }
        System.out.print(sb.toString());
    }

    private static void combinationOfWord(int length, int selectNum, char[] newWord) {
        if(selectNum >= length) {
            sb.append(String.valueOf(newWord)).append("\n");
            return;
        }
        for(int i = 0; i < 26; i++) {
            if(alphabet[i] > 0) {
                alphabet[i]--;
                newWord[selectNum] = (char)('a' + i);
                combinationOfWord(length, selectNum + 1, newWord);
                alphabet[i]++;
            }
        }
    }
}
