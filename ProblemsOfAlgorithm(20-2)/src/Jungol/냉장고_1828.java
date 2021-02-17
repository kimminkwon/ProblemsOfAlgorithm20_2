package Jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 냉장고_1828 {

    private static class Chem {
        int start, end;
        public Chem(int start, int end) { this.start = start; this.end = end; }
    }

    private static Chem[] ref;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ref = new Chem[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ref[i] = new Chem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(ref, (o1, o2) -> { return o1.end - o2.end == 0 ? o1.start - o2.start : o2.end - o1.end; });
        System.out.println(findMinimumRefrigerator());
    }

    private static int findMinimumRefrigerator() {
        int numOfRef = 1;
        int currStart = ref[0].start;

        for(int i = 1; i < N; i++) {
            if(ref[i].end >= currStart) {
                if(currStart <= ref[i].start) currStart = ref[i].start;
            } else {
                numOfRef++;
                currStart = ref[i].start;
            }
        }
        return numOfRef;
    }
}
