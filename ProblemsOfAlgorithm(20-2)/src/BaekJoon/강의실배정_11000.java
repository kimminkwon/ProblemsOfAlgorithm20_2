package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class ClassTime implements Comparable<ClassTime> {
    private int startTime;
    private int endTime;

    public ClassTime(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() { return startTime; }

    public int getEndTime() { return endTime; }

    @Override
    public int compareTo(ClassTime o) {
        return this.startTime - o.startTime;
    }

    @Override
    public String toString() {
        return "ClassTime{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
public class 강의실배정_11000 {

    private static int numOfClass;
    private static List<ClassTime> classList;

    public static void main(String[] args) throws IOException {
        makeInput();
        System.out.println(findMinimumRoom());
    }

    private static int findMinimumRoom() {
        Collections.sort(classList);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        int maxRoom = 0;
        for(int i = 0; i < classList.size(); i++) {
            if(priorityQueue.isEmpty()) {
                priorityQueue.add(classList.get(i).getEndTime());
                maxRoom++;
            }
            else {
                if(priorityQueue.peek() <= classList.get(i).getStartTime()) {
                    priorityQueue.poll();
                    priorityQueue.add(classList.get(i).getEndTime());
                } else {
                    maxRoom++;
                    priorityQueue.add(classList.get(i).getEndTime());
                }
            }
        }

        return maxRoom;
    }


    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numOfClass = Integer.parseInt(br.readLine());
        classList = new ArrayList<>();
        for(int i = 0; i < numOfClass; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            classList.add(new ClassTime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }
}
