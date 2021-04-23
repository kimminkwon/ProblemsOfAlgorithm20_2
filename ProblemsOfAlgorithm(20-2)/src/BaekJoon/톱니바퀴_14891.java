package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 톱니바퀴_14891 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<List<Integer>> magnet = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
              String box = br.readLine();
              magnet.add(new LinkedList<>());
              for(int j = 0; j < 8; j++)
                  magnet.get(i).add(Character.getNumericValue(box.charAt(j)));
        }
        int K = Integer.parseInt(br.readLine());
        int[][] cycleInfo = new int[K][2];
        for(int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cycleInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
            cycleInfo[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(cycleMagnet(magnet, K, cycleInfo));
        br.close();
    }

    private static int cycleMagnet(List<List<Integer>> magnet, int K, int[][] cycleInfo) {
        for(int[] currCycle : cycleInfo) {
            int[] cycleDir = getCycleDir(currCycle[0], currCycle[1], magnet);
            for(int i = 0; i < 4; i++)
                doCycle(cycleDir[i], magnet.get(i));
        }
        return getScore(magnet);
    }

    private static int[] getCycleDir(int cycleNum, int dir, List<List<Integer>> magnet) {
        int[] cycleDir = new int[4];
        cycleDir[cycleNum] = dir;
        // 1. cycleNum 기준 왼쪽에서 회전될 애들을 확인한다.
        // 이번 기준(num)의 6번과 왼쪽(num - 1)의 2번이 다른 극인지 확인
        int currDir = dir;
        for(int num = cycleNum; num > 0; num--) {
            if(magnet.get(num).get(6) != magnet.get(num - 1).get(2)) {
                currDir = currDir == 1 ? -1 : 1;
                cycleDir[num - 1] = currDir;
            } else break;
        }
        // 2. cycleNum 기준 오른쪽에서 회전될 애들을 확인한다.
        // 이번 기준(num)의 2번과 왼쪽(num + 1)의 6번이 다른 극인지 확인
        currDir = dir;
        for(int num = cycleNum; num < 4 - 1; num++) {
            if(magnet.get(num).get(2) != magnet.get(num + 1).get(6)) {
                currDir = currDir == 1 ? -1 : 1;
                cycleDir[num + 1] = currDir;
            } else break;
        }
        return cycleDir;
    }

    private static void doCycle(int dir, List<Integer> magnet) {
        if(dir == 1) {
            int lastNum = magnet.get(7);
            magnet.remove(7);
            magnet.add(0, lastNum);
        } else if(dir == -1) {
            int firstNum = magnet.get(0);
            magnet.remove(0);
            magnet.add(magnet.size(), firstNum);
        }
    }

    private static int getScore(List<List<Integer>> magnet) {
        int score = 0;
        for(int i = 0; i < 4; i++) {
            int num = magnet.get(i).get(0);
            if(num == 1) score += Math.pow(2, i);
        }
        return score;
    }
}
