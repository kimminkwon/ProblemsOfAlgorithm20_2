package BaekJoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Print {
    private int num;
    private int imp;

    public Print(int num, int imp) {
        this.num = num;
        this.imp = imp;
    }

    public int getNum() { return num; }

    public int getImp() { return imp; }
}

public class 프린터큐_1966 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfTest = sc.nextInt();
        for(int tc = 0; tc < numOfTest; tc++) {
            int numOfDoc = sc.nextInt();
            int docNum = sc.nextInt();
            Print[] docArr = new Print[numOfDoc];
            Queue<Print> printQ = new LinkedList<>();
            for(int i = 0; i < numOfDoc; i++) printQ.offer(new Print(i, sc.nextInt()));

            findPrintSeq(numOfDoc, docNum, printQ);
        }

    }

    private static void findPrintSeq(int numOfDoc, int docNum, Queue<Print> printQ) {
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
        for(Print p : printQ) max = Math.max(p.getImp(), max);
        return max;
    }

}
