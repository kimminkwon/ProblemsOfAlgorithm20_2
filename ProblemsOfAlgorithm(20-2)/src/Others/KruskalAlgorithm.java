package Others;

import java.util.*;

class Kruskal {
	private ArrayList<ConnectInfo> edgeList;
	private UnionFind unionFind;
	
	public Kruskal() {
		this.edgeList = new ArrayList<>();
	}
	
	public void addEdge(ConnectInfo connectInfo) {
		this.edgeList.add(connectInfo);
	}
	
	public int kruskal() {
		int res = 0;
		this.unionFind = new UnionFind(edgeList.size());
		Collections.sort(edgeList);
		
		for(int i = 0; i < edgeList.size(); i++) {
			if(unionFind.isSame(edgeList.get(i).getLeftNode(), edgeList.get(i).getRightNode()) == false ) {
				
				System.out.println("연결할 Edge: " + edgeList.get(i));
				
				unionFind.unionSet(edgeList.get(i).getLeftNode(), edgeList.get(i).getRightNode());
				res = res + edgeList.get(i).getWeight();
			}
		}
		
		return res;
	}
}

class ConnectInfo implements Comparable {
	private int leftNode;
	private int rightNode;
	private int weight;
	
	public ConnectInfo(int leftNode, int rightNode, int weight) {
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		this.weight = weight;
	}

	public int getLeftNode() {
		return leftNode;
	}

	public int getRightNode() {
		return rightNode;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Object o) {
		ConnectInfo otherConnectInfo = (ConnectInfo)o;
		if(this.weight > otherConnectInfo.getWeight())
			return 1;
		else if(this.weight < otherConnectInfo.getWeight())
			return -1;
		else 
			return 0;
	}

	@Override
	public String toString() {
		return "[" + this.leftNode + "와 " + this.rightNode + "를 잇는 Edge, 가중치: " + this.weight + "]";
	}
	
	
}

public class KruskalAlgorithm {
	public static void main(String[] args) {
		Kruskal krus = new Kruskal();
		krus.addEdge(new ConnectInfo(1, 7, 12));
		krus.addEdge(new ConnectInfo(1, 4, 28));
		krus.addEdge(new ConnectInfo(1, 2, 67));
		krus.addEdge(new ConnectInfo(1, 5, 17));
		krus.addEdge(new ConnectInfo(2, 4, 24));
		krus.addEdge(new ConnectInfo(2, 5, 62));
		krus.addEdge(new ConnectInfo(3, 5, 20));
		krus.addEdge(new ConnectInfo(3, 6, 37));
		krus.addEdge(new ConnectInfo(4, 7, 13));
		krus.addEdge(new ConnectInfo(5, 6, 45));
		krus.addEdge(new ConnectInfo(5, 7, 73));
		
		System.out.println("MST value: " + krus.kruskal());
		
	}
}
