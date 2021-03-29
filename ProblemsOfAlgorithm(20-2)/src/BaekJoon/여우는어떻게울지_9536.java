package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 여우는어떻게울지_9536 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            String[] sounds = br.readLine().split(" ");
            List<String> otherSound = new ArrayList<>();
            while(true) {
                String box = br.readLine();
                if(box.contains("fox say?")) break;
                StringTokenizer st = new StringTokenizer(box);
                st.nextToken(); st.nextToken();
                otherSound.add(st.nextToken());
            }
            sb.append(findFoxSay(sounds, otherSound)).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static String findFoxSay(String[] sounds, List<String> otherSound) {
        boolean[] isFoxSay = new boolean[sounds.length];

        for(String other : otherSound) {
            for(int i = 0; i < sounds.length; i++)
                if(other.equals(sounds[i])) isFoxSay[i] = true;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < sounds.length; i++)
            if(!isFoxSay[i]) sb.append(sounds[i]).append(" ");
        return sb.toString();
    }
}
