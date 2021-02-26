package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 히스토그램에서가장큰직사각형_6549 {

    private static int N;
    private static int[] histogram;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;
            histogram = new int[N];
            for(int i = 0; i < N; i++) histogram[i] = Integer.parseInt(st.nextToken());
            
            sb.append(maximumSquareInHistogram(0, N)).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static long maximumSquareInHistogram(int start, int end) {
        if(end - start <= 1) return histogram[start];

        int midIndex = (start + end) / 2;
        int left = midIndex, right = midIndex;
        long minHeight = histogram[midIndex];
        long maxArea = histogram[midIndex];

        long leftResult = maximumSquareInHistogram(start, midIndex);
        long rightResult = maximumSquareInHistogram(midIndex, end);

        while(left - 1 >= start || right + 1 < end) {
            long leftArea = Long.MIN_VALUE, rightArea = Long.MIN_VALUE;
            if(left - 1 >= start) {
                long currMinHeight = Math.min(minHeight, histogram[left - 1]);
                leftArea = (right - (left - 1) + 1) * currMinHeight;
            }
            if(right + 1 < end) {
                long currMinHeight = Math.min(minHeight, histogram[right + 1]);
                rightArea = ((right + 1) - left + 1) * currMinHeight;
            }
            if(leftArea > rightArea) {
                maxArea = Math.max(maxArea, leftArea);
                minHeight = Math.min(minHeight, histogram[--left]);
            } else {
                maxArea = Math.max(maxArea, rightArea);
                minHeight = Math.min(minHeight, histogram[++right]);
            }
        }
        return Math.max(maxArea, Math.max(leftResult, rightResult));
    }
}