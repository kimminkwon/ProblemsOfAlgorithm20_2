package BaekJoon;

import java.io.*;
import java.util.*;

public class 트리인가_6416 {

    private static boolean isUnique;
    private static int k = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<int[]> input = new ArrayList<>();
        while(true) {
            String box = br.readLine();
            if(box.contains("-1")) break;
            else if(!box.equals("")) {
                StringTokenizer st = new StringTokenizer(box, "  ");
                while(st.hasMoreTokens()) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    if(a != 0 && b != 0) input.add(new int[]{a, b});
                    else if(a == 0 && b == 0) {
                        printResult(isTree(input));
                        input.clear();
                        k++;
                    }
                }
            }
        }
    }

    private static boolean isTree(List<int[]> input) {
        if(input.isEmpty()) return true;

        int maxNum = -1;
        Map<Integer, Integer> inDegree = new HashMap<>();
        Set<Integer> treeNum = new HashSet<>();
        Map<Integer, List<Integer>> tree = new HashMap<>();

        // indegree, outdegree 구축
        for(int[] connect : input) {
            if(!inDegree.containsKey(connect[0])) inDegree.put(connect[0], 0);
            if(!inDegree.containsKey(connect[1])) inDegree.put(connect[1], 0);

            maxNum = Math.max(Math.max(connect[0], maxNum), connect[1]);
            treeNum.add(connect[0]);
            treeNum.add(connect[1]);

            inDegree.put(connect[1], inDegree.get(connect[1]) + 1);
        }

        // 트리 구축
        for(int[] connect : input) {
            if(!tree.containsKey(connect[0])) tree.put(connect[0], new ArrayList<>());
            if(!tree.containsKey(connect[1])) tree.put(connect[1], new ArrayList<>());

            tree.get(connect[0]).add(connect[1]);
        }

        // 1. ROOT가 없거나 2개 이상인가?
        int rootIndex = isRoot(treeNum, inDegree);
        if(rootIndex == -1) return false;

        // 2. ROOT를 제외한 모든 Node의 Indegree가 1인가?
        if(!onlyOneIndegree(treeNum, inDegree, rootIndex)) return false;

        // 3. DFS로 탐색 시 사이클이 발생하지 않는가?
        isUnique = true;
        allNodeHaveUniquePath(tree, rootIndex, new boolean[maxNum + 1]);
        if(isUnique == false) return false;

        return true;
    }

    private static void allNodeHaveUniquePath(Map<Integer, List<Integer>> tree, int rootIndex, boolean[] checked) {
        if (checked[rootIndex]) {
            isUnique = false;
            return;
        }
        checked[rootIndex] = true;
        for (int index : tree.get(rootIndex))
            allNodeHaveUniquePath(tree, index, checked);
    }

    private static boolean onlyOneIndegree(Set<Integer> treeNum, Map<Integer, Integer> inDegree, int rootIndex) {
        Iterator<Integer> iter = treeNum.iterator();
        while(iter.hasNext()) {
            int index = iter.next();
            if(index != rootIndex && inDegree.get(index) > 1) return false;
        }
        return true;
    }

    private static int isRoot(Set<Integer> treeNum, Map<Integer, Integer> inDegree) {
        Iterator<Integer> iter = treeNum.iterator();
        int rootIndex = -1;
        while(iter.hasNext()) {
            int index = iter.next();
            if(inDegree.get(index) == 0) {
                if(rootIndex == -1) rootIndex = index;
                else return -1;
            }
        }
        return rootIndex;
    }

    private static void printResult(boolean isTree) {
        if(isTree) System.out.println("Case " + k + " is a tree.");
        else System.out.println("Case " + k + " is not a tree.");
    }
}
