package BaekJoon;

import java.io.*;
import java.util.*;

public class 가장가까운두점_2261 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }
    }

    private static int n;
    private static Coor[] coors;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        coors = new Coor[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coors[i] = new Coor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(coors, (o1, o2) -> o1.x - o2.x);
        System.out.println(getMinimumDist(0, n));
    }

    private static int getMinimumDist(int start, int end) {
        if(end - start == 1)
            return Integer.MAX_VALUE;
        else if(end - start == 2)
            return getDist(coors[start], coors[start + 1]);
        else if(end - start == 3)
            return Math.min(getDist(coors[start], coors[start + 2]), Math.min(getDist(coors[start], coors[start + 1]), getDist(coors[start + 1], coors[start + 2])));

        int mid = (end + start) / 2;
        int preDepthMinValue = Math.min(getMinimumDist(start, mid), getMinimumDist(mid + 1, end));

        int midValue = preDepthMinValue;
        List<Coor> midList = new ArrayList<>();
        for(int i = start; i < end; i++) {
            int dist = (int) Math.pow(coors[mid].x - coors[i].x, 2);
            if(dist < preDepthMinValue) midList.add(coors[i]);
        }
        Collections.sort(midList, (o1, o2) -> o1.y - o2.y);
        for(int i = 0; i < midList.size(); i++) {
            for(int j = i + 1; j < midList.size(); j++) {
                int dist = (int) Math.pow(midList.get(i).y - midList.get(j).y, 2);
                if(dist < midValue) midValue = Math.min(midValue, getDist(midList.get(i), midList.get(j)));
                else break;
            }
        }
        return Math.min(midValue, preDepthMinValue);
    }

    private static int getDist(Coor c1, Coor c2) {
        return (int) (Math.pow(Math.abs(c1.x - c2.x), 2) + Math.pow(Math.abs(c1.y - c2.y), 2));
    }
}