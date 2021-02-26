package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 성곽_2234 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }

        @Override
        public String toString() {
            return "("+ x +
                    ", " + y +
                    ')';
        }
    }

    private static int N, M, numOfRoom, maxSize, maxAdjSize;
    private static int[][] map;
    private static Map<Integer, Integer> roomSize = new HashMap<>();
    private static int[] dX = {0, -1, 0, 1}, dY = {-1, 0, 1, 0};

    private static void print(int[][] map) {
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] roomMap = findNumOfRoomAndSize(1, new int[M][N]);

        System.out.println(numOfRoom);
        System.out.println(maxSize);
        findMaxAdjRoom(roomMap);
        System.out.println(maxAdjSize);
    }

    private static void findMaxAdjRoom(int[][] roomMap) {
        boolean[] visitNum = new boolean[numOfRoom + 1];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(!visitNum[roomMap[i][j]]) {
                    doAdjBFS(new Coor(i, j), roomMap, roomMap[i][j]);
                    visitNum[roomMap[i][j]] = true;
                }
            }
        }
    }

    private static void doAdjBFS(Coor start, int[][] roomMap, int roomNum) {
        boolean[][] visited = new boolean[M][N];
        boolean[] adjNum = new boolean[numOfRoom + 1];
        Deque<Coor> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.x][start.y] = true;

        while(!q.isEmpty()) {
            Coor currC = q.poll();
            for(int d = 0; d < 4; d++) {
                int nX = currC.x + dX[d], nY = currC.y + dY[d];
                if(!isOut(nX, nY) && !visited[nX][nY]) {
                    if(roomMap[nX][nY] != roomNum) adjNum[roomMap[nX][nY]] = true;
                    else {
                        q.offer(new Coor(nX, nY));
                        visited[nX][nY] = true;
                    }
                }
            }
        }
        for(int i = 1; i <= numOfRoom; i++)
            if(adjNum[i]) maxAdjSize = Math.max(roomSize.get(roomNum) + roomSize.get(i), maxAdjSize);
    }

    private static int[][] findNumOfRoomAndSize(int cnt, int[][] roomMap) {
        loop: for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if (roomMap[i][j] == 0) {
                    roomSize.put(cnt, doBFS(new Coor(i, j), roomMap, cnt));
                    cnt++;
                    i = 0; j = 0;
                    continue;
                }
            }
        }
        numOfRoom = cnt - 1;
        return roomMap;
    }

    private static int doBFS(Coor start, int[][] roomMap, int cnt) {
        int size = 0;
        Deque<Coor> q = new ArrayDeque<>();
        q.offer(start);
        roomMap[start.x][start.y] = cnt;

        while(!q.isEmpty()) {
            Coor currC = q.poll();
            size++;
            for(int d = 0; d < 4; d++) {
                if((map[currC.x][currC.y] & 1 << d) != 0) continue;
                int nX = currC.x + dX[d], nY = currC.y + dY[d];
                if(!isOut(nX, nY) && roomMap[nX][nY] == 0) {
                    q.offer(new Coor(nX, nY));
                    roomMap[nX][nY] = cnt;
                }
            }
        }
        maxSize = Math.max(maxSize, size);
        return size;
    }


    private static boolean isOut(int x, int y) {
        return x >= M || x < 0 || y >= N || y < 0;
    }
}
