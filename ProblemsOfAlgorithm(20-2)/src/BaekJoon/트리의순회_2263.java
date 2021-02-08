package BaekJoon;

import java.io.*;
import java.util.*;

public class 트리의순회_2263 {

    private static class Node { int num; Node left; Node right; }
    private static StringBuilder sb = new StringBuilder();
    private static List<Integer> inOrder, postOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        inOrder = new ArrayList<>(); postOrder = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) inOrder.add(Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) postOrder.add(Integer.parseInt(st.nextToken()));

        Node head = makeTree(new Node(), N, 0, N, 0, N);
        doPreOrder(head);
        System.out.println(sb.toString());
    }

    private static void doPreOrder(Node head) {
        sb.append(head.num + " ");
        if(head.left != null) doPreOrder(head.left);
        if(head.right != null) doPreOrder(head.right);
    }

    private static Node makeTree(Node node, int n, int inStart, int inEnd, int postStart, int postEnd) {
        int rootNum = postOrder.get(postEnd - 1);
        node.num = rootNum;

        if(n <= 1) return node;

        int rootIndex = inStart;
        for(int i = inStart; i < inEnd; i++) {
            if(inOrder.get(i) == rootNum) break;
            rootIndex++;
        }
        int leftLength = rootIndex - inStart;

        node.left = leftLength > 0 ?
                makeTree(new Node(), leftLength,
                inStart, rootIndex,
                postStart, postStart + leftLength)
                : null;
        
        node.right = n - leftLength - 1 > 0 ?
                makeTree(new Node(), n - leftLength - 1,
                rootIndex + 1, inEnd,
                postStart + leftLength, postEnd - 1)
                : null;

        return node;
    }
}
