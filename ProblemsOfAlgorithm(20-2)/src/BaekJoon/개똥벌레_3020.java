package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 개똥벌레_3020 {

    private static int N, H;
    private static int[] upDown, downUp, brokeNum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        upDown = new int[N/2];
        downUp = new int[N/2];
        brokeNum = new int[H + 1];

        int cnt1 = 0, cnt2 = 0;
        for(int i = 0; i < N; i++) {
            if(i % 2 == 0) downUp[cnt2++] = Integer.parseInt(br.readLine());
            else upDown[cnt1++] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(downUp);
        Arrays.sort(upDown);

        int minValue = Integer.MAX_VALUE;
        for(int h = 1; h <= H; h++) {
            int downUpNum = findNumOfBroke(0, N / 2 - 1, h, downUp);
            int upDownNum = findNumOfBroke(0, N / 2 - 1, H - h + 1, upDown);
            brokeNum[h] = downUpNum + upDownNum;
            minValue = Math.min(minValue, brokeNum[h]);
        }

        int minCount = 0;
        for(int i = 1; i <= H; i++)
            if(brokeNum[i] == minValue) minCount++;

        System.out.println(minValue + " " + minCount);
    }

    private static int findNumOfBroke(int left, int right, int height, int[] findArr) {
        int min = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(height <= findArr[mid]) {
                min = Math.min(min, mid);
                right = mid - 1;
            } else left = mid + 1;
        }
        if(min == Integer.MAX_VALUE) return 0;
        return N / 2 - min;
    }

}
