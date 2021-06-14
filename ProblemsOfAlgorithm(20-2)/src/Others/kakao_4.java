package Others;

import java.util.*;

public class kakao_4 {

    public static void main(String[] args) {
        int n = 8;
        int k = 2;

        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};

        boolean[] isDelete = new boolean[n];
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) list.add(i);

        int currPoint = k;
        Stack<Integer> indexHistory = new Stack<>();
        Stack<Integer> valueHistory = new Stack<>();
        for(String c : cmd) {
            String[] command = c.split(" ");
            switch (command[0]) {
                case "C":
                    indexHistory.push(currPoint);
                    valueHistory.push(list.get(currPoint));
                    isDelete[list.get(currPoint)] = true;
                    list.remove(currPoint);
                    if(currPoint >= list.size()) currPoint = list.size() - 1;
                    if(currPoint < 0) currPoint = 0;
                    System.out.println("C: " + Arrays.toString(command));
                    System.out.println("currPoint: " + currPoint);
                    System.out.println("remove: " + Arrays.toString(isDelete));
                    System.out.println("result List: " + list);
                    System.out.println();
                    break;
                case "D":
                    currPoint = currPoint + Integer.parseInt(command[1]);
                    System.out.println("D: " + Arrays.toString(command));
                    System.out.println("currPoint: " + currPoint);
                    System.out.println("remove: " + Arrays.toString(isDelete));
                    System.out.println("result: " + list);
                    System.out.println();
                    break;
                case "U":
                    currPoint = currPoint - Integer.parseInt(command[1]);
                    System.out.println("U: " + Arrays.toString(command));
                    System.out.println("currPoint: " + currPoint);
                    System.out.println("remove: " + Arrays.toString(isDelete));
                    System.out.println("result: " + list);
                    System.out.println();
                    break;
                case "Z":
                    int removeIndex = indexHistory.pop();
                    int removeValue = valueHistory.pop();
                    isDelete[removeValue] = false;
                    list.add(removeIndex, removeValue);
                    if(removeIndex <= currPoint) currPoint++;
                    System.out.println("Z: " + Arrays.toString(command));
                    System.out.println("removeIndex: " + removeIndex);
                    System.out.println("removeValue: " + removeValue);
                    System.out.println("currPoint: " + currPoint);
                    System.out.println("remove: " + Arrays.toString(isDelete));
                    System.out.println("result: " + list);
                    System.out.println();
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(isDelete[i]) sb.append("X");
            else sb.append("O");
        }
        System.out.println();

    }

}
