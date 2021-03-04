package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class 다리만들기2_17472 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M, numOfSum, result;
    private static int[][] mapBox;
    private static int[][] map;
    private static int[][] adjArr;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        mapBox = new int[N][M]; map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                mapBox[i][j] = Integer.parseInt(st.nextToken());
        }
        makeMap();
        adjArr = new int[numOfSum + 1][numOfSum + 1];
        for(int i = 0; i < numOfSum + 1; i++) Arrays.fill(adjArr[i], Integer.MAX_VALUE);
        makeAdjArr();
        makeBridge();
        System.out.println(result);
    }

    private static void makeBridge() {
        if(numOfSum == 0) {
            result = -1;
            return;
        }
        boolean[] visited = new boolean[numOfSum + 1];
        int[] minDist = new int[numOfSum + 1];
        int min = Integer.MAX_VALUE;
        int selected = 1; visited[1] = true;

        for(int cnt = 0; cnt < numOfSum - 1; cnt++) {
            for(int i = 1; i < numOfSum + 1; i++) {
                if(adjArr[selected][i] != Integer.MAX_VALUE) {
                    if(minDist[i] == 0) minDist[i] = adjArr[selected][i];
                    minDist[i] = Math.min(minDist[i], adjArr[selected][i]);
                }
            }

            // 2. 선택
            min = Integer.MAX_VALUE;
            for(int i = 1; i < numOfSum + 1; i++) {
                if(!visited[i] && minDist[i] != 0) {
                    if(min > minDist[i]) {
                        min = minDist[i];
                        selected = i;
                    }
                }
            }
            if(min == Integer.MAX_VALUE) break;

            // 3. 포함시키기
            visited[selected] = true;
            result += min;
        }

        for(int i = 1; i < numOfSum + 1; i++)
            if(visited[i] == false) result = -1;
    }

    private static void makeAdjArr() {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 0) findMinimumPath(i, j, map[i][j]);
            }
        }
    }

    private static void findMinimumPath(int i, int j, int sumNum) {
        for(int d = 0; d < 4; d++) {
            int x = i; int y = j;
            int length = 0;
            while(true) {
                int nx = x + dx[d], ny = y + dy[d];
                if(isOut(nx, ny) || map[nx][ny] == sumNum) break;
                else if(map[nx][ny] != 0) {
                    if(length > 1) {
                        adjArr[map[nx][ny]][sumNum] = Math.min(adjArr[map[nx][ny]][sumNum], length);
                        adjArr[sumNum][map[nx][ny]] = Math.min(adjArr[sumNum][map[nx][ny]], length);
                    }
                    break;
                }
                length++;
                x = nx; y = ny;
            }
        }
    }

    private static void makeMap() {
        int cnt = 1;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                if(mapBox[i][j] == 1 && map[i][j] == 0)
                    fillMap(new Coor(i, j), cnt++);
        numOfSum = cnt - 1;
    }

    private static void fillMap(Coor start, int cnt) {
        boolean[][] visited = new boolean[N][M];
        Deque<Coor> q = new ArrayDeque<>();
        q.offerLast(start); visited[start.x][start.y] = true; map[start.x][start.y] = cnt;

        while(!q.isEmpty()) {
            Coor currC = q.poll();
            for(int d = 0; d < 4; d++) {
                int nx = currC.x + dx[d], ny = currC.y + dy[d];
                if(!isOut(nx, ny) && !visited[nx][ny] && mapBox[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    map[nx][ny] = cnt;
                    q.offer(new Coor(nx, ny));
                }
            }
        }
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= M || x < 0 || y < 0;
    }
}
