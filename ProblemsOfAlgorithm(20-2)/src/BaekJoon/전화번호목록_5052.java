package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 전화번호목록_5052 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] tels = new String[N];
            for(int i = 0; i < N; i++) tels[i] = br.readLine();
            sb.append(isStraightNumber(N, tels) ? "YES" : "NO").append("\n");
        }
        System.out.print(sb.toString());
    }

    private static boolean isStraightNumber(int N, String[] tels) {
        Map<String, Integer> hm = new HashMap<>();
        for(int l = 1; l <= 10; l++) {
            // 1. l개 길이를 갖는 맵을 생성한다. (접두어=key, 등장횟수=value)
            for(int i = 0; i < N; i++) {
                if(tels[i].length() >= l) {
                    String putStr = tels[i].substring(0, l);
                    if(!hm.containsKey(putStr)) hm.put(putStr, 1);
                    else hm.put(putStr, hm.get(putStr) + 1);
                }
            }
            // 2. 정확히 길이가 l인 애들을 Map에서 확인했을 때, 이 값이 2 이상이라면 접두어가 겹치는게 있다!
            for(int i = 0; i < N; i++) {
                if(tels[i].length() == l) {
                    if(hm.get(tels[i]) >= 2) return false;
                }
            }
        }
        return true;
    }
}

