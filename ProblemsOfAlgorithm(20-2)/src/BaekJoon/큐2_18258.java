package BaekJoon;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

class UserQueue {
    private List<Integer> q;

    public UserQueue() { q = new LinkedList<>(); }

    public int doJob(String command) {
        if(command.contains("push")) return push(Integer.parseInt(command.replace("push ", "")));
        else if(command.contains("pop")) return pop();
        else if(command.contains("size")) return q.size();
        else if(command.contains("empty")) return q.isEmpty() ? 1 : 0;
        else if(command.contains("front")) return q.isEmpty() ? -1 : q.get(0);
        else if(command.contains("back")) return q.isEmpty() ? -1 : q.get(q.size() - 1);

        return -9999;
    }

    private int pop() {
        return q.isEmpty() ? -1 : q.remove(0);
    }

    private int push(int num) {
        q.add(num);
        return -9999;
    }
}
public class 큐2_18258 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        UserQueue uq = new UserQueue();
        for(int i = 0; i < n; i++) {
            int res = uq.doJob(br.readLine());
            if(res != -9999) bw.write(res + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static void makeInput() {

    }


}
