package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 바이러스_2606 {
    private static int numOfComputer, numOfPair;
    private static int[][] pairList;
    private static int[][] adjArr;


    public static void main(String[] args) throws IOException {
        makeInput();
        makeAdjArr();
        System.out.println(doBfsForVirus());
    }

    private static int doBfsForVirus() {
        if(numOfComputer <= 1) return 0;

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[numOfComputer + 1];

        q.offer(1);
        while(!q.isEmpty()) {
            int comNum = q.poll();
            visited[comNum] = true;
            for(int i = 1; i <= numOfComputer; i++)
                if(adjArr[comNum][i] == 1 && visited[i] == false) q.offer(i);
        }
        int cnt = 0;
        for(int i = 2; i < visited.length; i++)
            if(visited[i]) cnt++;
        return cnt;
    }

    private static void makeAdjArr() {
        adjArr = new int[numOfComputer + 1][numOfComputer + 1];

        for(int[] pair : pairList) {
            adjArr[pair[0]][pair[1]] = 1;
            adjArr[pair[1]][pair[0]] = 1;
        }
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numOfComputer = Integer.parseInt(br.readLine());
        numOfPair = Integer.parseInt(br.readLine());
        pairList = new int[numOfPair][2];
        for(int i = 0; i < numOfPair; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pairList[i][0] = Integer.parseInt(st.nextToken());
            pairList[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
