package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 가르침_1062 {

    private static int N, K, possibleCharLength, result;
    private static String[] strs;
    private static Character[] possibleChar;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(K < 5) {
            System.out.println(0);
            return;
        } else K = K - 5;

        strs = new String[N];
        for(int i = 0; i < N; i++) {
            strs[i] = br.readLine();
            strs[i] = strs[i].substring(4, strs[i].length() - 4);
        }

        preprocessing();
        possibleCharLength = possibleChar.length;

        if(K >= possibleCharLength) {
            System.out.println(N);
            return;
        }
        makeCombination(new int[K], 0, 0);
        System.out.println(result);
    }

    private static void makeCombination(int[] comb, int index, int currCnt) {
        if(index == K) {
            int currRes = findCaseOfWords(comb);
            result = Math.max(currRes, result);
            return;
        }
        for(int i = currCnt; i < possibleCharLength; i++) {
            comb[index] = i;
            makeCombination(comb, index + 1, i + 1);
        }
    }

    private static int findCaseOfWords(int[] comb) {
        Set<Character> hs = new HashSet<>();
        hs.add('a'); hs.add('i'); hs.add('c'); hs.add('n'); hs.add('t');

        for(int i = 0; i < comb.length; i++) hs.add(possibleChar[comb[i]]);

        int cnt = 0;
        for(int i = 0; i < N; i++) {
            boolean isPossible = true;
            for(int j = 0; j < strs[i].length(); j++)
                if(!hs.contains(strs[i].charAt(j))) isPossible = false;
            if(isPossible) cnt++;
        }

        for(int i = 0; i < comb.length; i++)
            hs.remove(possibleChar[comb[i]]);
        hs.add('a'); hs.add('i'); hs.add('c'); hs.add('n'); hs.add('t');
        return cnt;
    }

    private static void preprocessing() {
        Set<Character> hs = new HashSet<>();
        hs.add('a'); hs.add('i'); hs.add('c'); hs.add('n'); hs.add('t');

        for(int i = 0; i < N; i++)
            for(int j = 0; j < strs[i].length(); j++)
                hs.add(strs[i].charAt(j));
        hs.remove('a'); hs.remove('i'); hs.remove('c'); hs.remove('n'); hs.remove('t');
        possibleChar = new Character[hs.size()];
        hs.toArray(possibleChar);
    }
}
