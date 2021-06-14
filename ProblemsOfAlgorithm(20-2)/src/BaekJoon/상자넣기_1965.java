package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 상자넣기_1965 {

    private static int n;
    private static int[] boxes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        boxes = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            boxes[i] = Integer.parseInt(st.nextToken());

        System.out.println(findMaximumBox());
    }

    private static int findMaximumBox() {
        int[] boxDp = new int[n];
        Arrays.fill(boxDp, 1);
        int res = 1;
        for(int i = 1; i < n; i++) {
            // i번째 박스에 대해서 이전 값을 순회한다.
            int maxBoxValue = 0;
            for(int j = 0; j < i; j++) {
                if(boxes[i] > boxes[j]) { // i번째 박스의 앞에 박스 중 나보다 작은 무게의 박스가 있다.
                    maxBoxValue = Math.max(maxBoxValue, boxDp[j] + 1);
                }
            }
            res = Math.max(boxDp[i] = Math.max(maxBoxValue, boxDp[i]), res);
        }
        return res;
    }
}
