package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동물원_12907 {

    private static int N;
    private static int[] tailArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tailArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            tailArr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(tailArr);
        System.out.println(findCaseOfAnimal());
    }

    private static int findCaseOfAnimal() {
        int numOfCase = 1, flag = 0, index = 0;
        while(index < N) {
            if(tailArr[index] != flag) return 0;
            else if(index < N - 1 && tailArr[index] == tailArr[index + 1]) {
                numOfCase *= 2;
                flag++;
                index += 2;
            } else if(index < N - 1 && tailArr[index] + 1 == tailArr[index + 1]) {
                while(index < N - 1 && tailArr[index] + 1 == tailArr[index + 1]) {
                    index++;
                    flag++;
                }
                flag++;
                if(index == N - 1) index++;
                numOfCase *= 2;
            } else if(index == N - 1) {
                numOfCase *= 2;
                flag++;
                index++;
            } else return 0;
        }
        return numOfCase;
    }
}
