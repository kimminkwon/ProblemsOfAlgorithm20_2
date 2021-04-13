package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치_2110 {

    private static int N, C, result = Integer.MIN_VALUE;
    private static int[] puppys;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        puppys = new int[N];
        for(int i = 0; i < N; i++)
            puppys[i] = Integer.parseInt(br.readLine());

        findMaximumRangeForSpiders();
        System.out.println(result);
    }

    private static void findMaximumRangeForSpiders() {
        // 1. 위치별로 정렬
        Arrays.sort(puppys);

        // 2. 최소 간격과 최대 간격을 설정 ==> 우리가 원하는 정답 x는 minDist <= x <= maxDist에 범위 안에 있다.
        int minDist = 1, maxDist = puppys[N - 1] - puppys[0];

        // 3. 최소 간격과 최대 간격의 중앙 값 midDist를 기준으로 몇개까지 먹이를 줄 수 있는지 확인한다.
        // 3-1. 준 먹이의 개수가 C보다 작다면 C개의 먹이를 주지 못했으므로 불가능한 케이스이다(즉, 기준이 된 거리 값이 너무 길다.).
        // 따라서 탐색하는 거리 값을 줄이기 위해 maxDist를 midDist - 1로 두고 다시 탐색한다.
        // 3-2. 준 먹이의 개수가 C보다 크거나 같다면 C개 이상의 먹이를 줬으므로 가능한 케이스이다.
        // 따라서 탐색하는 거리 값을 늘리기 위해 결과를 업데이트 + minDist를 midDist + 1로 두고 다시 탐색한다.
        while(minDist <= maxDist) {
            int midDist = (minDist + maxDist) / 2;
            int numOfPrey = getNumOfPrey(midDist);
            if(numOfPrey < C) { // 먹이를 다 주지 못했다. ==> 거리를 더 좁혀봐야한다. + 거리가 아무리 멀어도 문제의 조건을 만족하지 못한다.
                maxDist = midDist - 1;
            } else { // 먹이를 다 줬다. ==> 거리를 더 늘려봐야한다. + 거리 값 업데이트
                result = Math.max(result, midDist);
                minDist = midDist + 1;
            }
        }
    }

    private static int getNumOfPrey(long flagDist) {
        int preyCnt = 1;
        long currLoc = puppys[0];
        for(int i = 1; i < N; i++)
            if(puppys[i] - currLoc >= flagDist) {
                preyCnt++;
                currLoc = puppys[i];
            }
        return preyCnt;
    }
}
