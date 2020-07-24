package Others;

import java.util.*;

class BubbleSort {
	private int[] list;

	public BubbleSort(int[] list) {
		this.list = list;
	}
	
	public int[] getSortedList() {
		bubbleSort();
		return list;
	}
	
	private void bubbleSort() {
		System.out.println("\n정렬 시작할 리스트: " + Arrays.toString(list) + " ================");
		for(int i = 0; i < list.length; i++) {
			for(int j = 0; j < list.length - (i + 1); j++) {
				if(list[j] > list[j + 1]) {
					System.out.println("list[" + j + "] = " + list[j] + "와 list[" + (j+1) + "] = " + list[j+1] + "를 Swap한다.");
					swap(j, j+1);
					System.out.println("현재 리스트: " + Arrays.toString(list));
				}
			}
		}
	}
	
	private void swap(int index1, int index2) {
		int box = list[index1];
		list[index1] = list[index2];
		list[index2] = box;
	}
}

public class SortAlgorithm_BubbleSort {
	public static void main(String[] args) {
		int size = 10;
		int[] testList = new int[size];
		ArrayList<Integer> al = new ArrayList<>();
		for(int i = 1; i <= testList.length; i++) {
			al.add(i);
		}
		Collections.shuffle(al);
		for(int i = 0; i < testList.length; i++) {
			testList[i] = al.get(i);
		}
		BubbleSort bubbleSort = new BubbleSort(testList);
		int[] answer = bubbleSort.getSortedList();
		System.out.println("\n========= 정렬 결과: " + Arrays.toString(answer) + " =========");
	}

}
