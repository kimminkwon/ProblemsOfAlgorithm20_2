package BaekJoon;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class 요세푸스문제0_11866 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        List<Integer> link = new LinkedList<>();
        for(int i = 1; i <= n; i++) link.add(i);

        StringBuilder sb = new StringBuilder("");
        int cnt = 0;
        int flag = 0;
        while(cnt < n) {
            flag = moveFlag(n, k, flag, cnt);
            sb.append(link.remove(flag) + " ");
            cnt++;
        }

        printResult(sb);
    }

    private static void printResult(StringBuilder sb) {
        String[] strArr = sb.toString().split(" ");
        System.out.print("<");
        for(int i = 0; i < strArr.length; i++) {
            if(i == strArr.length - 1) System.out.print(strArr[i] + ">");
            else System.out.print(strArr[i] + ", ");
        }
    }

    private static int moveFlag(int n, int k, int flag, int cnt) {
        if(flag + (k - 1) >= n - cnt) return (flag + (k - 1)) % (n - cnt);
        else return flag + k - 1;
    }
}
