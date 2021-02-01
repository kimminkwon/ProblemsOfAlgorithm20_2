package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Print {
    private int num; // 인덱스
    private int imp; // 우선순위

    public Print(int num, int imp) {
        this.num = num;
        this.imp = imp;
    }

    public int getNum() { return num; }

    public int getImp() { return imp; }
}

public class 프린터큐_1966 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfTest = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < numOfTest; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfDoc = Integer.parseInt(st.nextToken());
            int docNum = Integer.parseInt(st.nextToken());
            Queue<Print> printQ = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < numOfDoc; i++)
                printQ.offer(new Print(i, Integer.parseInt(st.nextToken())));

            findPrintSeq(docNum, printQ);
        }
    }

    private static void findPrintSeq(int docNum, Queue<Print> printQ) {
        // docNum: 찾고자하는 프린터 목록 번호, printQ: 프린터 대기 큐
        int cnt = 1;
        while(!printQ.isEmpty()) {
            int maxImp = findMaximumImp(printQ);
            if(maxImp > printQ.peek().getImp()) printQ.offer(printQ.poll());
            else {
                Print currP = printQ.poll();
                if(currP.getNum() == docNum) System.out.println(cnt);
                else cnt++;
            }
        }
    }

    private static int findMaximumImp(Queue<Print> printQ) {
        int max = 0;
        for(Print p : printQ)
            max = Math.max(p.getImp(), max);
        return max;
    }
}

// 0 1 2 3 ==> 인덱스
// 1 2 3 4 ==> 우선순위

// 높은거부터 출력한다!
// 1. 지금 있는 Q에서 가장 높은 우선순위가 몇인가?
// 2. while() ==> 그 높은 우선순위가 나올때까지:: 첫번째 원소를 poll해서 offer해주는 과정 반복
// 3. offer() 그 값에 index번호가 문제에서 주어진 번호랑 동일하다면? 출력!!!