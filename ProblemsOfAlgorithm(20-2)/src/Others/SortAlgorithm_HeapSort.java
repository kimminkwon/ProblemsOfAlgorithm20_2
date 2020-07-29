package Others;

import java.util.*;

class HeapSort {
	private int[] list;
	private int[] heap;

	public HeapSort(int[] list) {
		// Heap 구현의 유용함을 위해 Index를 1부터 시작
		this.list = new int[list.length + 1];
		this.list[0] = Integer.MAX_VALUE;
		for (int i = 0; i < list.length; i++) {
			this.list[i+1] = list[i];
		}
		heap = new int[this.list.length];
	}

	public int[] getSortedList() {
		heapSort();
		return list;
	}

	private void heapSort() {
		buildHeap();
		for(int size = (list.length-1); size > 0; size--) {
			swap(1, size);
			heapify(1, size);
		}
	}
	private void buildHeap() {
		/*
		 * 노드 i의 부모 노드 인덱스: i/2 (for i>1) 
		 * 노드 i의 왼쪽 자식 노드 인덱스: i * 2
		 * 노드 i의 오른쪽 자식 노드 인덱스: i * 2 + 1
		 */
		for (int i = (list.length-1) / 2; i > 0 ; i--) { // 자식노드의 인덱스
			heapify(i, list.length);
		}
	}

	private void heapify(int start, int end) {
		int leftChild = start * 2;
		int rightChild = start * 2 + 1;
		int maxIndex = start;
		if (leftChild < end)
			if (list[leftChild] > list[maxIndex])
				maxIndex = leftChild;
		if (rightChild < end)
			if (list[rightChild] > list[maxIndex])
				maxIndex = rightChild;
		// max에는 start, leftChild, rightChild 중 더 큰 index가 저장되어있다.
		if (maxIndex != start) {
			// max가 바뀌었다 == left 혹은 right Child가 더 컸다.
			swap(start, maxIndex); // start와 max의 위치를 바꿔준다.
			heapify(maxIndex, end);
		}
	}

	private void swap(int index1, int index2) {
		int box = list[index1];
		list[index1] = list[index2];
		list[index2] = box;
	}

}

public class SortAlgorithm_HeapSort {
	public static void main(String[] args) {
		 int size = 20; 
		 int[] testList = new int[size]; 
		 ArrayList<Integer> al = new ArrayList<>(); 
		 for (int i = 1; i <= testList.length; i++) { al.add(i); }
		 Collections.shuffle(al); 
		 for (int i = 0; i < testList.length; i++) { testList[i] = al.get(i); }

		HeapSort heapSort = new HeapSort(testList);
		int[] buffer = heapSort.getSortedList();
		int[] answer = new int[testList.length];
		
		for(int i = 0; i < answer.length; i++) {
			answer[i] = buffer[i+1];
		}
		
		System.out.println("\n========= 정렬 결과: " + Arrays.toString(answer) + " =========");
	}

}
