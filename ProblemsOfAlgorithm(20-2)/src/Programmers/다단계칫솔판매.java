package Programmers;

import java.util.*;

public class 다단계칫솔판매 {

    private static class Node {
        String name;
        List<Integer> profit;
        List<Node> childs;

        public Node(String name, List<Integer> profit) {
            this.name = name;
            this.profit = profit;
            this.childs = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", profit=" + profit +
                    ", childs=" + childs +
                    '}';
        }
    }

    private static Map<String, Node> enrollMap;
    private static Map<String, List<Integer>> amounts;
    private static Map<String, Integer> profits;

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        // 1. 각자 판매량을 정확히 갱신한다.
        amounts = new HashMap<>();
        profits = new HashMap<>();
        for(int i = 0; i < seller.length; i++) {
            if(amounts.containsKey(seller[i])) {
                amounts.get(seller[i]).add(amount[i]);
            } else {
                amounts.put(seller[i], new ArrayList<>());
                amounts.get(seller[i]).add(amount[i]);
            }
        }

        for(String key : amounts.keySet()) {
            System.out.println(amounts.get(key));
        }

        // 2-1. 일단 모든 이름으로 노드를 생성한다.
        enrollMap = new HashMap<>();
        enrollMap.put("center", new Node("center", new ArrayList<>()));
        for(String name : enroll) {
            if(amounts.containsKey(name)) {
                enrollMap.put(name, new Node(name, amounts.get(name)));
            } else enrollMap.put(name, new Node(name, new ArrayList<>()));
        }

        // 2-2. 부모-자식 관계를 추가한다.
        for(int i = 0; i < referral.length; i++) {
            if(referral[i].equals("-")) {
                enrollMap.get("center").childs.add(enrollMap.get(enroll[i]));
            } else enrollMap.get(referral[i]).childs.add(enrollMap.get(enroll[i]));
        }

        for(String key : enrollMap.keySet()) {
            System.out.println(enrollMap.get(key));
            System.out.println();
            System.out.println();
        }
        // 3. DFS를 통해 손익 계산
        calculateProfit(enrollMap.get("center"));

        int[] answer = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) {
            answer[i] = profits.get(enroll[i]);
        }
        System.out.println(Arrays.toString(answer));
    }

    private static List<Integer> calculateProfit(Node currNode) {
        // 1. 나의 수익 계산
        List<Integer> fees = new ArrayList<>();
        int totalProfit = 0;
        for(int profit : currNode.profit) {
            int currProfit = profit * 100;
            int fee = (int) (currProfit * 0.1);
            currProfit = currProfit - fee;
            totalProfit += currProfit;
            fees.add(fee);
        }

        // 2. DFS 실행
        for(Node child : currNode.childs) {
            List<Integer> childFee = calculateProfit(child);
            for(int childProfit : childFee) {
                totalProfit = totalProfit + (childProfit - (int)(childProfit * 0.1));
                if((int)(childProfit * 0.1) < 1) continue;
                fees.add((int)(childProfit * 0.1));
            }
        }

        // 3. 수수료 계산
        System.out.println(currNode.name + "이 반환할 수수료: " + fees);
        System.out.println(currNode.name + "의 최종 수익: " + totalProfit);
        System.out.println();

        profits.put(currNode.name, totalProfit);
        return fees;
    }
}
