package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 숫자할리갈리게임_20923 {

    private static int N, M;
    private static String result;
    private static Deque<Integer> doQ = new ArrayDeque<>(), suQ = new ArrayDeque<>();
    private static Deque<Integer> doGroundQ = new ArrayDeque<>(), suGroundQ = new ArrayDeque<>();

    public static void main(String[]args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            doQ.offerFirst(Integer.parseInt(st.nextToken()));
            suQ.offerFirst(Integer.parseInt(st.nextToken()));
        }

        doGame();
        System.out.println(result);
    }

    private static void doGame() {
        int cnt = 0;
        while(true) {
            // 1. 도도가 패를 내려놓고
            doGroundQ.offerFirst(doQ.pollFirst());
            // 2. 도도의 패가 비었다면 수연의 승리!
            if(doQ.size() == 0) {
                result = "su";
                return;
            }
            // 3. 현재 종을 칠 수 있는 사람이 종 치기!
            if((doGroundQ.size() != 0 && doGroundQ.peekFirst() == 5) || (suGroundQ.size() != 0 && suGroundQ.peekFirst() == 5)) doWin();
            if((doGroundQ.size() != 0 && suGroundQ.size() != 0 && suGroundQ.peekFirst() + doGroundQ.peekFirst() == 5)) suWin();
            // 4. 한 턴이 흘렀으니 턴 개수가 맞는지 확인!
            if(++cnt == M) break;
            
            // 5. 수연이가 패를 내려놓고
            suGroundQ.offerFirst(suQ.pollFirst());
            // 6. 수연의 패가 비었다면 도도의 승리
            if(suQ.size() == 0) {
                result = "do";
                return;
            }
            // 7. 현재 종을 칠 수 있는 사람이 종 치기!
            if((doGroundQ.size() != 0 && doGroundQ.peekFirst() == 5) || (suGroundQ.size() != 0 && suGroundQ.peekFirst() == 5)) doWin();
            if((doGroundQ.size() != 0 && suGroundQ.size() != 0 && suGroundQ.peekFirst() + doGroundQ.peekFirst() == 5)) suWin();
            // 8. 한 턴이 흘렀으니 턴 개수가 맞는지 확인!
            if(++cnt == M) break;
        }
        whoWin();
    }

    private static void whoWin() {
        if(doQ.size() > suQ.size()) result = "do";
        else if(doQ.size() < suQ.size()) result = "su";
        else result = "dosu";
    }

    private static void doWin() {
        while(!suGroundQ.isEmpty()) doQ.offerLast(suGroundQ.pollLast());
        while(!doGroundQ.isEmpty()) doQ.offerLast(doGroundQ.pollLast());
    }

    private static void suWin() {
        while(!doGroundQ.isEmpty()) suQ.offerLast(doGroundQ.pollLast());
        while(!suGroundQ.isEmpty()) suQ.offerLast(suGroundQ.pollLast());
    }
}
