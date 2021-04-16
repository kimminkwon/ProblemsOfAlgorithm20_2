package Others;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Programmers_test_3 {

    private static List<List<Integer>> adjList = new ArrayList<>();

    public static void main(String[] args) {
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0, 1}, {3, 4}, {2, 3}, {0, 3}};

        for(int i = 0; i < a.length; i++) adjList.add(new ArrayList<>());
        for(int i = 0; i < edges.length; i++) {
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }

        Deque<Character> q = new ArrayDeque<>();
        System.out.println(q.peek() == ')');





    }
}
