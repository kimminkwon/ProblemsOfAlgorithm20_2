package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 달력_20207 {

    private static class Job {
        int start, end;
        public Job(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
    private static int N;
    private static Job[] jobs;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        jobs = new Job[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            jobs[i] = new Job(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(calculatePaperArea());
    }

    private static int calculatePaperArea() {
        int[] numOfJob = new int[370];
        int start = 365, end = 0;
        for(Job j : jobs) {
            if(j.end > end) end = j.end;
            if(j.start < start) start = j.start;
            for(int i = j.start; i <= j.end; i++)
                numOfJob[i]++;
        }

        int area = 0;
        int currHeight = 0, currWidth = 0;
        for(int i = start; i <= end + 1; i++) {
            if(numOfJob[i] == 0) { // 넓이를 계산해야되는 경우
                area += (currHeight * currWidth);
                currWidth = 0; currHeight = 0;
            } else {
                currWidth++;
                currHeight = Math.max(currHeight, numOfJob[i]);
            }
        }
        return area;
    }
}
