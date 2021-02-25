package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

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

        int dist = fuel;

        while(true) {
            System.out.println("현재까지 온 거리: " + dist);
            if(dist >= goal) break;
            else if(q.isEmpty()) {
                numOfGasStation = -1;
                break;
            }
            int maxFuel = 0;
            while(!q.isEmpty() && q.peek()[0] < dist) {
                maxFuel += q.pop()[1];
                if(dist + maxFuel >= q.peek()[0]) break;
            }
            dist += maxFuel;
            numOfGasStation++;
        }
    }
}
