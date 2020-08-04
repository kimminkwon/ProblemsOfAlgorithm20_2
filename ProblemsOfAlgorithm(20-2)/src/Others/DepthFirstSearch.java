package Others;

import java.util.*;

class DFS {
	private AdjList al;
	private int[] color; // 0 : no visit, 1 : visit start, 2: visit end
	private int[] discoverTime;
	private int[] finishTime;
	private int time;
	
	public DFS(AdjList al) {
		this.al = al;
		color = new int[al.getList().size()];
		Arrays.fill(color, 0);
		discoverTime = new int[al.getList().size()];
		finishTime = new int[al.getList().size()];
		time = 0;
	}
	
	public void dfs(int current) {
		System.out.println(current + "-th node visit");
		color[current] = 1;
		discoverTime[current] = ++time;
		
		ArrayList<Integer> box = al.getNode(current);
		for(int i : box) {
			if(color[i] == 0) {
				dfs(i);
			}
		}
		color[current] = 2;
		finishTime[current] = ++time;
	}
}

public class DepthFirstSearch {

	public static void main(String[] args) {
		AdjList al = new AdjList(8);
		al.putDouble(1, 2); al.putDouble(1, 3);
		al.putDouble(2, 3); al.putDouble(2, 4); al.putDouble(2, 5);
		al.putDouble(3, 6); al.putDouble(3, 7);
		al.putDouble(4, 5);
		al.putDouble(6, 7);
		
		al.printAdjList();
		
		DFS d = new DFS(al);
		d.dfs(1);
	}

}
