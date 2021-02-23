package BaekJoon;

import java.io.*;
import java.util.*;

public class 컨베이어벨트위의로봇_20055 {

    private static int N, K, numOfZero;
    private static Set<HashSet> hs = new HashSet<>();
    private static LinkedList<Integer> robotLoc = new LinkedList<>(), belt = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2 * N; i++)
            belt.add(Integer.parseInt(st.nextToken()));

        System.out.println(findEndLevelInBelt());
    }

    private static int findEndLevelInBelt() {
        int level = 1;
        while (true) {
            moveBelt();
            if(numOfZero >= K) break;
            
            moveRobot();
            if(numOfZero >= K) break;

            setRobot();
            if(numOfZero >= K) break;
            level++;
        }
        return level;
    }

    private static void moveBelt() {
        belt.addFirst(belt.removeLast());

        for(int i = 0; i < robotLoc.size(); i++)
            if(robotLoc.get(i) + 1 == N) robotLoc.remove(i);

        for(int i = 0; i < robotLoc.size(); i++)
            robotLoc.set(i, robotLoc.get(i) + 1);
    }

    private static void setRobot() {
        if(belt.get(0) >= 1) {
            belt.set(0, belt.get(0) - 1);
            if(belt.get(0) == 0) numOfZero++;
            robotLoc.addLast(0);
        }
    }

    private static void moveRobot() {
        for(int i = 0; i < robotLoc.size(); i++)
            if(robotLoc.get(i) + 1 == N) robotLoc.remove(i);

        for(int i = 0; i < robotLoc.size(); i++) {
            if(belt.get(robotLoc.get(i) + 1) >= 1 && !robotLoc.contains(robotLoc.get(i) + 1)) {
                robotLoc.set(i, robotLoc.get(i) + 1);
                belt.set(robotLoc.get(i), belt.get(robotLoc.get(i)) - 1);
                if(belt.get(robotLoc.get(i)) == 0) numOfZero++;
            }
        }
    }
}
