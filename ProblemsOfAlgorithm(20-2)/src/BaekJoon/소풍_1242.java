package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소풍_1242 {

    private static int N, K, M;
    private static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        doKINGame();
        System.out.println(result);
    }

    private static void doKINGame() {
        int n = N, k = K, myPlace = M;
        for(int i = 1; i <= N; i++) {
            int outNum = k % n == 0 ? n : k % n;
            if(n == 1 || outNum == myPlace) {
                result = i;
                return;
            }
            n--;
            myPlace = myPlace > outNum ? myPlace - outNum : n - Math.abs(myPlace - outNum + 1);
        }
    }
}
