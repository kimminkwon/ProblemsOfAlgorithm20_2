package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리_1068 {

    private static Node root;
    private static int result;

    private static class Node {
        int num, parentNum;
        List<Node> childList = new ArrayList<>();

        public Node(int num, int parentNum) { this.num = num; this.parentNum = parentNum; }
        public void setNum(int num) { this.num = num; }
        public void addChild(Node child) { this.childList.add(child); }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Node[] nodeArr = new Node[N];
        for(int i = 0; i < N; i++)
            nodeArr[i] = new Node(i, Integer.parseInt(st.nextToken()));

        int removeNum = Integer.parseInt(br.readLine());
        nodeArr[removeNum].num = Integer.MIN_VALUE;

        makeTree(N, nodeArr, removeNum);
        doTraversal(root);
        System.out.println(result);
    }

    private static void makeTree(int n, Node[] nodeArr, int removeNum) {
        for(int i = 0; i < n; i++){
            if(nodeArr[i].parentNum == -1) {
                root = nodeArr[i];
                break;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(nodeArr[i].num == nodeArr[j].parentNum)
                    nodeArr[i].addChild(nodeArr[j]);
            }
        }
    }

    private static void doTraversal(Node n) {
        if(n.num == Integer.MIN_VALUE) return;
        if(n.childList.isEmpty()) {
            result++;
            return;
        }
        for(Node child : n.childList)
            doTraversal(child);
    }
}
