package Others;

import java.util.*;

// 그래프 알고리즘을 위한 인접 리스트 클래스 구현

public class AdjList {
	private ArrayList<ArrayList<Integer>> list;
	
	public AdjList(int listSize) {
		this.list = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < listSize; i++) {
			this.list.add(new ArrayList<Integer>());
		}
	}

	public ArrayList<ArrayList<Integer>> getList() {
		return this.list;
	}
	
	public ArrayList<Integer> getNode(int index) {
		return this.list.get(index);
	}
	
	public void putDouble(int start, int end) {
		list.get(start).add(end);
		list.get(end).add(start);
	}
	
	public void putSingle(int start, int end) {
		list.get(start).add(end);
	}
	
	public void printAdjList() {
		for (int i = 1; i < list.size(); i++) {
			System.out.print("AdjList of Node " + i);

			for (int j = 0; j < list.get(i).size(); j++) {
				System.out.print(" -> " + list.get(i).get(j));
			}
			System.out.println();
		}
	}
}
