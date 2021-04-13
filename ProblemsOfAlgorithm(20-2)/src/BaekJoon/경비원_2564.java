package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 경비원_2564 {

    private static class Coor {
        int loc, x, y;
        public Coor(int loc, int x, int y) {
            this.loc = loc;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[(" + x + ", " + y + "), loc=" + loc + "]";
        }
    }
    private static int w, h, numOfStore; // 가로, 세로, 상점의 개수
    private static Coor[] stores;
    private static Coor dong;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        numOfStore = Integer.parseInt(br.readLine());
        stores = new Coor[numOfStore];

        for(int i = 0; i < numOfStore; i++) {
            st = new StringTokenizer(br.readLine());
            stores[i] = makeCoor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        dong = makeCoor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        System.out.println(findMinimumPathEachStores());
    }

    private static int findMinimumPathEachStores() {
        int sumOfPath = 0;
        for(int i = 0; i < numOfStore; i++) {
            if(dong.loc <= stores[i].loc) sumOfPath += findMinimumPath(dong, stores[i]);
            else sumOfPath += findMinimumPath(stores[i], dong);
        }
        return sumOfPath;
    }

    private static int findMinimumPath(Coor c1, Coor c2) {
        if(c1.loc == c2.loc) {
            return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
        } else if(c1.loc == 1 && c2.loc == 3) {
            return c1.x + c2.y;
        } else if(c1.loc == 1 && c2.loc == 4) {
            return (w - c1.x) + c2.y;
        } else if(c1.loc == 2 && c2.loc == 3){
            return c1.x + (h - c2.y);
        } else if(c1.loc == 2 && c2.loc == 4){
            return (w - c1.x) + (h - c2.y);
        } else if(c1.loc == 1 && c2.loc == 2) {
            int pathOne = c1.x + c2.x + h;
            int pathTwo = (w - c1.x) + (w - c2.x) + h;
            return Math.min(pathOne, pathTwo);
        } else if(c1.loc == 3 && c2.loc == 4) {
            int pathOne = c1.y + c2.y + w;
            int pathTwo = (h - c1.y) + (h - c2.y) + w;
            return Math.min(pathOne, pathTwo);
        }
        return 0;
    }

    private static Coor makeCoor(int loc, int range) {
        switch (loc) {
            case 1:
                return new Coor(loc, range, 0);
            case 2:
                return new Coor(loc, range, h);
            case 3:
                return new Coor(loc, 0, range);
            case 4:
                return new Coor(loc, w, range);
        }
        return null;
    }
}
