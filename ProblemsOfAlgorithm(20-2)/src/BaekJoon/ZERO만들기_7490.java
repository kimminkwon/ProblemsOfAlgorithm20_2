package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ZERO만들기_7490 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(findMakeZeroOperations(N)).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static String findMakeZeroOperations(int N) {
        List<String> zeroOper = new ArrayList<>();
        Deque<String> q = new ArrayDeque<>();
        findMakeZeroOperation(q, N, 1, zeroOper);
        Collections.sort(zeroOper);
        StringBuilder sb = new StringBuilder();
        for(String zero : zeroOper)
            sb.append(zero).append("\n");
        return sb.toString();
    }


    private static void findMakeZeroOperation(Deque<String> oper, int N, int currNum, List<String> zeroOper) {
        oper.offer(String.valueOf(currNum));
        if(currNum == N) {
            if(calculateString(oper) == 0)
                zeroOper.add(getStringToOperation(oper));
            oper.pollLast();
            return;
        }
        oper.offer("+");
        findMakeZeroOperation(oper, N, currNum + 1, zeroOper);

        oper.pollLast();
        oper.offer("-");
        findMakeZeroOperation(oper, N, currNum + 1, zeroOper);

        oper.pollLast();
        oper.offer(" ");
        findMakeZeroOperation(oper, N, currNum + 1, zeroOper);

        oper.pollLast();
        oper.pollLast();
    }

    private static String getStringToOperation(Deque<String> oper) {
        String box = "";
        Iterator<String> itr = oper.iterator();
        while(itr.hasNext())
            box += itr.next();
        return box;
    }

    private static int calculateString(Deque<String> operQ) {
        Iterator<String> itr = operQ.iterator();

        Deque<Integer> numList = new ArrayDeque<>();
        Deque<String> operList = new ArrayDeque<>();
        String box = "";
        while(itr.hasNext()) {
            String currStr = itr.next();
            if(currStr.equals("+") || currStr.equals("-")) {
                operList.offer(currStr);
                if(box.length() > 0) {
                    numList.offer(Integer.parseInt(box));
                    box = "";
                }
            } else if(!currStr.equals(" ")) box += currStr;
        }
        numList.offer(Integer.parseInt(box));
        if(operList.size() == 0) return numList.pollLast();

        while(!operList.isEmpty()) {
            int num1 = numList.pollFirst();
            int num2 = numList.pollFirst();
            String oper = operList.pollFirst();
            if(oper.equals("+")) numList.offerFirst(num1 + num2);
            else if(oper.equals("-")) numList.offerFirst(num1 - num2);
        }
        return numList.pollLast();

    }
}
