package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AC_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfTest = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < numOfTest; tc++) {
            String func = br.readLine();
            int numOfList = Integer.parseInt(br.readLine());
            Deque<Integer> deque = new ArrayDeque<>();
            String str = br.readLine().replace("[", "").replace("]", "");
            if(str.length() <= 0) {
                if(func.contains("D")) System.out.println("error");
                else System.out.println("[]");
            } else {
                String[] strArr = str.split(",");
                for(int i = 0; i < numOfList; i++)
                    deque.add(Integer.parseInt(strArr[i]));

                System.out.println(AC(func, numOfList, deque));
            }
        }
    }

    private static String AC(String func, int numOfList, Deque<Integer> deque) {
        boolean isReverse = false;

        for(char f : func.toCharArray()) {
            if(f == 'R') isReverse = !isReverse;
            else if(f == 'D'){
                if(deque.isEmpty()) return "error";
                else {
                    if (isReverse) deque.pollLast();
                    else deque.pollFirst();
                }
            }
        }

        return dequeToString(deque, isReverse);
    }

    private static String dequeToString(Deque<Integer> deque, boolean isReverse) {
        StringBuilder sb = new StringBuilder("[");
        while(!deque.isEmpty())  {
            if(isReverse) sb.append(deque.pollLast());
            else sb.append(deque.pollFirst());
            if(deque.size() >= 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
