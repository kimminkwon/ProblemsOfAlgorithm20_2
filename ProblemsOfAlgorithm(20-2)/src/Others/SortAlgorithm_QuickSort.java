package Others;

import java.util.*;

class QuickSort {
	private int[] list;
	
	public QuickSort(int[] list) {
		this.list = list;
	}
	
	public int[] getSortedList() {
		qSort(0, list.length - 1);
		return list;
	}
	
	private void qSort(int start, int end) {
		if(start >= end) {
			return;
		}
		int pivot = start;
		int maxIndex = start + 1;
		int minIndex = end;
		System.out.println("\n정렬 시작할 리스트: " + Arrays.toString(list) + ", Start =  " + start + ", End = " + end + " ================");
		System.out.println("현재 pivot = list[" + pivot + "] = " + list[pivot]);
		while(true) {
			while(maxIndex < end) {
				if(list[maxIndex] > list[pivot]) break;	
				maxIndex++;
			}
			while(minIndex > start) {
				if(list[minIndex] < list[pivot]) break;	
				minIndex--;
			}	
			System.out.println("maxIndex = list[" + maxIndex + "] = " + list[maxIndex] + ", minIndex = list[" + minIndex + "] = " + list[minIndex]);
			
			if(maxIndex < minIndex) {
				swap(maxIndex, minIndex);
				System.out.println("현재 리스트: " + Arrays.toString(list));
			}
			else {
				swap(minIndex, pivot);
				System.out.println("현재 리스트: " + Arrays.toString(list));
				break;
			}
		}
		qSort(start, minIndex - 1);
		qSort(minIndex + 1, end);
	}
	
	private void swap(int index1, int index2) {
		int box = list[index1];
		list[index1] = list[index2];
		list[index2] = box;
	}
	
}

public class SortAlgorithm_QuickSort {
	public static void main(String[] args) {
		int size = 30;
		int[] testList = new int[size];
		ArrayList<Integer> al = new ArrayList<>();
		for(int i = 1; i <= testList.length; i++) {
			al.add(i);
		}
		Collections.shuffle(al);
		for(int i = 0; i < testList.length; i++) {
			testList[i] = al.get(i);
		}
		QuickSort qSort = new QuickSort(testList);
		int[] answer = qSort.getSortedList();
		System.out.println("\n========= 정렬 결과: " + Arrays.toString(answer) + " =========");
	}

}
