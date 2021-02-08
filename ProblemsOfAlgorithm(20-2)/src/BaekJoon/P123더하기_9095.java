package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P123더하기_9095 {
    private static int N, numOfCase;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++){
            N = Integer.parseInt(br.readLine());
            numOfCase = 0;
            findNumOfCaseFrom123(0);
            System.out.println(numOfCase);
        }
    }

    private static void findNumOfCaseFrom123(int currNum) {
        if(currNum > N) return;
        if(currNum == N) {
            numOfCase++;
            return;
        }
        findNumOfCaseFrom123(currNum + 1);
        findNumOfCaseFrom123(currNum + 2);
        findNumOfCaseFrom123(currNum + 3);
    }
}
