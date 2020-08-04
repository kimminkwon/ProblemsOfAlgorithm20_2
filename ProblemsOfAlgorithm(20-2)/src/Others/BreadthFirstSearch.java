package Others;

import java.util.*;

class BFS {
	private AdjList al;
	
	public BFS(AdjList al) {
		this.al = al;
	}
	
	public void bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] selected = new boolean[al.getList().size()];
		
		int cnt = 0;
		queue.offer(start);
		selected[start] = true;
		
		
		while(!queue.isEmpty()) {
			// 1. 큐에서 하나의 값을 꺼내고 방문처리
			int current = queue.poll();
			cnt++;
			System.out.println(cnt + "-th visited vertex : " + current);
			
			// 2. 해당 값과 인접한 모든 정점을 큐에 삽입
			ArrayList<Integer> box = al.getNode(current);
			for(Integer i : box) {
				if(selected[i] == false) {
					queue.offer(i);
					selected[i] = true;
				}
			}			
		}
	}
}

public class BreadthFirstSearch {
	public static void main(String[] args) {
		// 인접리스트 생성
		AdjList al = new AdjList(8);
		al.putDouble(1, 2); al.putDouble(1, 3);
		al.putDouble(2, 3); al.putDouble(2, 4); al.putDouble(2, 5);
		al.putDouble(3, 6); al.putDouble(3, 7);
		al.putDouble(4, 5);
		al.putDouble(6, 7);
		
		al.printAdjList();
		
		BFS b = new BFS(al);
		b.bfs(1);
	}

}
