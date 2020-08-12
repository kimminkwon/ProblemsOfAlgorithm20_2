package Others;

import java.util.*;

class UnionFind {
	private int[] unionFindList;
	
	public UnionFind(int size) {
		this.unionFindList = new int[size];
		Arrays.fill(unionFindList, -1);
	}
	
	public int findSet(int i) {
		// 해당 Vertex의 부모노드가 없는 경우: Root노드는 원소의 개수를 기억한다.
		if(unionFindList[i] < 0) 
			return i;
		else 
			return unionFindList[i] = findSet(unionFindList[i]);
	}
	
	public boolean isSame(int i, int j) {
		return findSet(i) == findSet(j);		 
	}
	
	public void unionSet(int i, int j) {
		int iRoot = findSet(i);
		int jRoot = findSet(j);
		// System.out.println("합칠 대상: " + i + ", " + j + "이고, 각 Root는 " + iRoot + ", " + jRoot);
		
		if(iRoot != jRoot) {
			int numOfi = unionFindList[iRoot] * -1;
			int numOfj = unionFindList[jRoot] * -1;
			
			if(numOfi >= numOfj) {
				unionFindList[iRoot] = (numOfi + numOfj) * -1;
				unionFindList[jRoot] = iRoot;
			}
			else {
				unionFindList[jRoot] = (numOfi + numOfj) * -1;
				unionFindList[iRoot] = jRoot;
			}
		}
	}

	@Override
	public String toString() {
		return "UnionFind [unionFindList=" + Arrays.toString(unionFindList) + "]\n";
	}
}

public class UnionFindDefine {
	public static void main(String[] args) {
		UnionFind unionFind = new UnionFind(10);
		unionFind.unionSet(0, 1);
		System.out.println(unionFind.toString());
		unionFind.unionSet(1, 2);
		System.out.println(unionFind.toString());		
		unionFind.unionSet(2, 3);
		System.out.println(unionFind.toString());
		
		unionFind.unionSet(5, 6);
		System.out.println(unionFind.toString());
		unionFind.unionSet(4, 5);
		System.out.println(unionFind.toString());
		
		unionFind.unionSet(3, 5);
		System.out.println(unionFind.toString());

		System.out.println("4의 위치는 " + unionFind.findSet(4)); // 경로 압축이 같이 일어날 것
		System.out.println("6의 위치는 " + unionFind.findSet(6));
		System.out.println(unionFind.toString());

	}
}
