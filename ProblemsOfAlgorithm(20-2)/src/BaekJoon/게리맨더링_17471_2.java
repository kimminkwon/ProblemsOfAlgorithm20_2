package BaekJoon;

import java.io.*;
import java.util.*;

public class 게리맨더링_17471_2 {

    private static int N, result = Integer.MAX_VALUE;
    private static int[] peopleNum;
    private static int[][] adjArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        peopleNum = new int[N];
        adjArr = new int[N][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            peopleNum[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int numOfAdj = Integer.parseInt(st.nextToken());
            for(int j = 0; j < numOfAdj; j++) {
                int adjIndex = Integer.parseInt(st.nextToken()) - 1;
                adjArr[i][adjIndex] = 1;
                adjArr[adjIndex][i] = 1;
            }
        }

        divideTwoArea(0, new boolean[N]);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    // 1. 2개의 구역을 나눠준다.
    private static void divideTwoArea(int index, boolean[] isOneArea) {
        if(index == N) {
            // 2. 특정 구역만 전부 TRUE인지 확인한다. ==> isOneArea가 모두 같은 값인가?
            if(isCorrectDivide(isOneArea)) return;
            // 3. 각 구역이 서로 연결되어 있는가? ==> BFS를 통해 확인한다.
            boolean isConnectAreaOne = isConnectedArea(isOneArea, true);
            boolean isConnectAreaTwo = isConnectedArea(isOneArea, false);
            if(!isConnectAreaOne || !isConnectAreaTwo) return;

            result = Math.min(result, calculateDiff(isOneArea));
            return;
        }
        isOneArea[index] = true;
        divideTwoArea(index + 1, isOneArea);
        isOneArea[index] = false;
        divideTwoArea(index + 1, isOneArea);
    }

    // inOneArea가 flag(T/F)인 애들만 순회한다.
    private static boolean isConnectedArea(boolean[] isOneArea, boolean flag) {
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        // 1. 시작 값을 넣어준다.
        for(int i = 0; i < N; i++) {
            if(isOneArea[i] == flag) {
                q.offer(i);
                visited[i] = true;
                break;
            }
        }
        // 2. 시작점을 기준으로 BFS 시작
        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int i = 0; i < N; i++) {
                if(visited[i] || adjArr[curr][i] != 1 || isOneArea[i] != flag) continue;
                q.offer(i);
                visited[i] = true;
            }
        }
        return isAllVisited(flag, visited, isOneArea);
    }

    private static int calculateDiff(boolean[] isOneArea) {
        int num1 = 0, num2 = 0;
        for(int i = 0; i < N; i++) {
            if(isOneArea[i]) num1 += peopleNum[i];
            else num2 += peopleNum[i];
        }
        return Math.abs(num1 - num2);
    }

    private static boolean isAllVisited(boolean flag, boolean[] visited, boolean[] isOneArea) {
        for(int i = 0; i < N; i++)
            if(isOneArea[i] == flag)
                if(!visited[i]) return false;
        return true;
    }

    private static boolean isCorrectDivide(boolean[] isOneArea) {
        for(int i = 1; i < N; i++)
            if(isOneArea[i - 1] != isOneArea[i]) return false;
        return true;
    }
}
