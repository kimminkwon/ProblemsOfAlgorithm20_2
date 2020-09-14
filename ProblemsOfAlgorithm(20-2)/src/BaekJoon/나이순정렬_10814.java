package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

class OnlineJudgeMember implements Comparable {
	
	private int sq, age;
	private String name;
	
	public OnlineJudgeMember(int sq, int age, String name) { this.sq = sq; this.age = age; this.name = name; }

	public int getSq() { return sq; }
	public int getAge() { return age; }
	public String getName() { return name; }

	@Override
	public String toString() { return this.age + " " + this.name; }
	
	@Override
	public int compareTo(Object o) {
		OnlineJudgeMember otherM = (OnlineJudgeMember)o;
		if(this.age > otherM.getAge()) return 1;
		else if(this.age < otherM.getAge()) return -1;
		else {
			if(this.sq > otherM.getSq()) return 1;
			else if(this.sq < otherM.getSq()) return -1;
			else return 0;
		}
	}
}

public class 나이순정렬_10814 {

	static int N;
	static OnlineJudgeMember[] members;
	
	public static void main(String[] args) {
		makeInput();
		sortedMembers();
		printMembers();
	}

	private static void printMembers() {
		for(int i = 0; i < N; i++)
			System.out.println(members[i]);
	}

	private static void sortedMembers() {
		Arrays.sort(members);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		members = new OnlineJudgeMember[N];
		
		int sq = 0;
		for(int i = 0; i < N; i++) 
			members[i] = new OnlineJudgeMember(sq++, input.nextInt(), input.next());
	}

}
