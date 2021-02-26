package BaekJoon;

import java.io.*;
import java.util.*;

public class 연료채우기_1826 {

    private static int N, fuel, goal, numOfGasStation;
    private static int[][] gas;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        gas = new int[N][2];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            gas[i][0] = Integer.parseInt(st.nextToken());
            gas[i][1] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        goal = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        findMinimumGasStation();
        System.out.println(numOfGasStation);
    }

    private static void findMinimumGasStation() {
        Arrays.sort(gas, (o1, o2) -> o1[0] - o2[0]);
        Deque<int[]> q = new ArrayDeque<>();
        Arrays.stream(gas).forEach(ints -> q.offer(ints));
        q.offer(new int[]{goal, 0});

        int currDist = fuel;
        PriorityQueue<int[]> pastStation = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        while(!q.isEmpty()) {
            // 0. 만약 도착했다면 반복을 멈춘다.
            if(currDist >= goal) break;
            // 1. 현재까지 지나온 주유소 저장
            while(!q.isEmpty() && currDist >= q.peek()[0])
                pastStation.add(q.pop());
            // 2. 지나온 주유소를 탐색하며 가장 좋은 케이스 찾음
            while(true) {
                if(pastStation.isEmpty()) { // 더 이상 갈 수 없다는 뜻
                    numOfGasStation = -1;
                    return;
                }
                currDist += pastStation.poll()[1];
                numOfGasStation++;
                if(currDist >= q.peek()[0]) break; // 다음 가야할 주유소까지 갈 수 있으면 루프 탈출
            }
        }
    }
}
