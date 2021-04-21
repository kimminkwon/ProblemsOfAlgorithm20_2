package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 감시_15683 {

    private static int N, M, numOfCCTV, result = Integer.MAX_VALUE;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static List<int[]> cctvs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6) cctvs.add(new int[]{i, j});
            }
        }
        numOfCCTV = cctvs.size();
        findMinimumSafeUnsafeArea(0, copyMap(map));
        System.out.println(result);
    }

    private static void findMinimumSafeUnsafeArea(int index, int[][] currMap) {
        if(index == numOfCCTV) {
            result = Math.min(countSafeArea(currMap), result);
            return;
        }
        int x = cctvs.get(index)[0], y = cctvs.get(index)[1];
        switch (map[x][y]) {
            case 1:
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{0}));
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{1}));
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{2}));
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{3}));
                break;
            case 2:
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{0, 2}));
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{1, 3}));
                break;
            case 3:
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{0, 1}));
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{1, 2}));
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{2, 3}));
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{3, 0}));
                break;
            case 4:
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{0, 1, 2}));
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{1, 2, 3}));
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{2, 3, 0}));
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{3, 0, 1}));
                break;
            case 5:
                findMinimumSafeUnsafeArea(index + 1, makeSafeArea(currMap, x, y, new int[]{0, 1, 2, 3}));
                break;
        }
    }

    private static int countSafeArea(int[][] map) {
        int cnt = 0;
        for(int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if(map[i][j] == 0) cnt++;
        return cnt;
    }

    private static int[][] makeSafeArea(int[][] map, int i, int j, int[] dirs) {
        int[][] newMap = copyMap(map);
        for(int d : dirs) {
            int x = i, y = j;
            while(true) {
                int nx = x + dx[d], ny = y + dy[d];
                if(isOut(nx, ny) || newMap[nx][ny] == 6) break;
                if(newMap[nx][ny] == 0) newMap[nx][ny] = -1;
                x = nx; y = ny;
            }
        }
        return newMap;
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= M || x < 0 || y < 0;
    }

    private static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }
}
