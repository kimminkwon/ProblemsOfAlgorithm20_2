package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 게리맨더링_17471 {

    private static int N, result = Integer.MAX_VALUE;
    private static int[] peopleNum;
    private static int[][] adjArr;
    private static boolean truePartition1, truePartition2;

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


        findMinimumDiffBetweenTwoCluster(0, new boolean[N], 0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void findMinimumDiffBetweenTwoCluster(int index, boolean[] select, int trueNum, int falseNum) {
        if(index == N) {
            System.out.println(Arrays.toString(select) + "의 CASE...");
            int start1 = findStart(select, true);
            if(start1 == -1) return;
            truePartition1 = isConnected(start1, new boolean[N], select, true);

            int start2 = findStart(select, false);
            if(start2 == -1) return;
            truePartition2 = isConnected(start2, new boolean[N], select, false);

            System.out.println("T1: " + truePartition1);
            System.out.println("T2: " + truePartition2);
            if(truePartition1 && truePartition2) {
                int diff = calculateDiff(select);
                result = Math.min(diff, result);
            }
            return;
        }
        select[index] = true;
        findMinimumDiffBetweenTwoCluster(index + 1, select, trueNum + 1, falseNum);
        select[index] = false;
        findMinimumDiffBetweenTwoCluster(index + 1, select, trueNum, falseNum + 1);
    }

    private static int calculateDiff(boolean[] select) {
        int num1 = 0, num2 = 0;
        for(int i = 0; i < N; i++) {
            if(select[i]) num1 += peopleNum[i];
            else num2 += peopleNum[i];
        }
        System.out.println("\t * NUM1: " + num1);
        System.out.println("\t * NUM2: " + num2);
        return Math.abs(num1 - num2);
    }

    private static int findStart(boolean[] select, boolean flag) {
        for(int i = 0; i < N; i++)
            if(select[i] == flag) return i;
        return -1;
    }

    private static boolean isConnected(int start, boolean[] visited, boolean[] select, boolean flag) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start); visited[start] = true;
        while(!q.isEmpty()) {
            int currIndex = q.poll();
            for(int i = 0; i < N; i++)
                if(adjArr[currIndex][i] == 1 && !visited[i] && select[i] == flag) {
                    q.offer(i);
                    visited[i] = true;
                }
        }

        return isAllVisited(flag, visited, select);
    }

    private static boolean isAllVisited(boolean flag, boolean[] visited, boolean[] select) {
        for(int i = 0; i < N; i++)
            if(select[i] == flag)
                if(!visited[i]) return false;
        return true;
    }
}
