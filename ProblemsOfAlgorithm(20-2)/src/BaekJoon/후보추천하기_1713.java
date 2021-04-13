package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 후보추천하기_1713 {

    private static class Cand {
        int recommend, count, index;
        public Cand(int recommend, int count, int index) {
            this.recommend = recommend;
            this.count = count;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Cand{" +
                    "recommend=" + recommend +
                    ", count=" + count +
                    ", index=" + index +
                    '}';
        }
    }

    private static int N, R;
    private static Cand[] pictures;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());

        pictures = new Cand[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < R; i++) {
            int r = Integer.parseInt(st.nextToken());
            boolean isHave = false;
            for(int j = 0; j < N; j++) {
                if(pictures[j] != null && pictures[j].index == r) {
                    pictures[j].recommend++;
                    isHave = true;
                }
            }
            if(!isHave) {
                boolean isNull = false;
                for(int j = 0; j < N; j++) {
                    if(!isNull) {
                        if(pictures[j] == null) {
                            pictures[j] = new Cand(0, 0, r);
                            isNull = true;
                        } else {
                            pictures[j].count++;
                        }
                    }
                }
                if(!isNull) {
                    Arrays.sort(pictures, (o1, o2) -> o1.recommend == o2.recommend ? o2.count - o1.count : o1.recommend - o2.recommend);
                    pictures[0] = new Cand(0, 0, r);
                }
            }
        }

        List<Cand> candList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            if(pictures[i] != null) candList.add(pictures[i]);
        }
        Collections.sort(candList, (o1, o2) -> o1.index - o2.index);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < candList.size(); i++) {
            sb.append(candList.get(i).index).append(" ");
        }
        System.out.println(sb.toString());

    }
}

