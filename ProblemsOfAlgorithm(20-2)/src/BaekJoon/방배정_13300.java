package BaekJoon;

import java.io.*;
import java.util.*;

public class 방배정_13300 {

	private static class Student {
		int year, gender;
		public Student(int gender, int year) { this.year = year; this.gender = gender; }
		@Override
		public String toString() {
			return "Student [year=" + year + ", gender=" + gender + "]";
		}
		
		
	}
	
	private static int N, K;
	private static Student[] students;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		students = new Student[N + 1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			students[i] = new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		students[N] = new Student(0, 7);
		
		System.out.println(findMinimumRooms());
	}

	private static int findMinimumRooms() {
		Arrays.sort(students, (o1, o2) -> o1.year == o2.year ? o1.gender - o2.gender : o1.year - o2.year);
		
		int result = 0;
		int currYear = 1; int maleNum = 0, femaleNum = 0;
		for(int i = 0; i <= N; i++) {
			if(students[i].year > currYear) {
				currYear++;
				result += (maleNum % K == 0 ? maleNum / K : maleNum / K + 1);
				result += (femaleNum % K == 0 ? femaleNum / K : femaleNum / K + 1);
				maleNum = 0; femaleNum = 0;
			}
			
			if(students[i].gender == 0) femaleNum++;
			else maleNum++;
		}
		
		return result;
	}
}
