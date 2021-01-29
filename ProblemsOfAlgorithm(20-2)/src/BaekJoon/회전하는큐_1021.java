package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class 회전하는큐_1021 {
    private static int N, M;
    private static int[] findNums;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        makeInput();
        cycleQueue();
    }

    private static void cycleQueue() {
        LinkedList<Integer> list = new LinkedList<>();
        IntStream.range(1, N+1).forEach(list::add);
        IntStream.range(0, M).forEach(i -> result += findNumInQueue(list, findNums[i]));
        System.out.println(result);
    }

    private static int findNumInQueue(LinkedList<Integer> list, int findNum) {
        if(findIndex(list, findNum) > list.size() / 2) return moveBackToFront(list, findNum);
        else return moveFrontToBack(list, findNum);
    }

    private static int moveFrontToBack(LinkedList<Integer> list, int findNum) {
        int cnt = 0;
        while(list.get(0) != findNum) {
            list.addLast(list.removeFirst());
            cnt++;
        }
        list.removeFirst();
        return cnt;
    }

    private static int moveBackToFront(LinkedList<Integer> list, int findNum) {
        int cnt = 0;
        while(list.get(0) != findNum) {
            list.addFirst(list.removeLast());
            cnt++;
        }
        list.removeFirst();
        return cnt;
    }

    private static int findIndex(LinkedList<Integer> list, int findNum) {
        for(int i = 0; i < list.size(); i++) if(list.get(i) == findNum) return i;
        return -1;
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        findNums = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) findNums[i] =Integer.parseInt(st.nextToken());
    }
}
