package SWEA;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution_무인도탈출_연습문제_4335 {

	private static int result = 0;
	private static int[][][] memo = new int[20][3][1 << 20];
	private static final int MAXLENGTH = 100000000;

	private static class Box {
		int[][] faces; // 직육면체이므로 3개의 면을 저장
		public Box(int verti, int hori, int height) {
			faces = new int[3][3]; // 3개의 면이 각각 2개의 변을 통한 면과 높이를 가짐
			faces[0][0] = verti; faces[0][1] = hori; faces[0][2] = height;
			faces[1][0] = verti; faces[1][1] = height; faces[1][2] = hori;
			faces[2][0] = hori; faces[2][1] = height; faces[2][2] = verti;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_무인도탈출.txt"));
		Scanner sc = new Scanner(System.in); // BufferedReader를 허용하지 않는다.
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();; // N = 상자의 개수
			Box[] boxes = new Box[N];
			for(int i = 0; i < N; i++)
				boxes[i] = new Box(sc.nextInt(), sc.nextInt(), sc.nextInt());

			result = 0;
			for(int i = 0; i < N; i++) for(int j = 0; j < 3; j++) for(int k = 0; k < 1<<N; k++) // 필요한 부분만 초기화, 선언을 여기에서 하게되면 런타임 오류 발생!
				memo[i][j][k] = 0;
			// HashMap을 허용하지 않으므로 selected는 비트마스크로
			// 처음 선택은 주어진 길이보다 큰 길이를 통해 사용한다.
			result = findMaximumHeightOfBoxes(N, boxes, 0, MAXLENGTH, MAXLENGTH, -1, -1);
			System.out.println("#" + tc + " " + result);
		}
		sc.close();
	}

	private static int findMaximumHeightOfBoxes(int N, Box[] boxes, int isSelected, int preLength1, int preLength2, int preIndex, int preFace) {
		if(preIndex >= 0 && memo[preIndex][preFace][isSelected] > 0) return memo[preIndex][preFace][isSelected];
		int currMaxLength = 0;
		for(int i = 0; i < N; i++) {
			if((isSelected & (1 << i)) > 0) continue; // 이미 선택한 박스라면 넘긴다.
			for(int j = 0; j < 3; j++) { // 선택되지 않은 박스라면 현재 박스의 3개의 면을 각각 이전 것과 비교하여 쌓을 수 있는지 확인해본다.
				if(isStack(preLength1, preLength2, boxes[i].faces[j][0], boxes[i].faces[j][1])) { // i번째 박스의 j번째 면이 쌓을 수 있다면?
					int currLength = findMaximumHeightOfBoxes(N, boxes, (isSelected | (1 << i)), boxes[i].faces[j][0], boxes[i].faces[j][1], i, j);
					currMaxLength = currMaxLength >= currLength ? currMaxLength : currLength;
				}
			}
		}
		if(preIndex >= 0) memo[preIndex][preFace][isSelected] = currMaxLength + boxes[preIndex].faces[preFace][2];
		return preIndex >= 0 ? currMaxLength + boxes[preIndex].faces[preFace][2] : currMaxLength;
	}

	private static boolean isStack(int bottomLength1, int bottomLength2, int topLength1, int topLength2) {
		// bottom 위에 top을 쌓을 수 있는가?
		int bottomMaxLength = bottomLength1 >= bottomLength2 ? bottomLength1 : bottomLength2;
		int bottomMinLength = bottomLength1 >= bottomLength2 ? bottomLength2 : bottomLength1;
		int topMaxLength = topLength1 >= topLength2 ? topLength1 : topLength2;
		int topMinLength = topLength1 >= topLength2 ? topLength2 : topLength1;
		if(topMaxLength > bottomMaxLength) return false;
		else if(topMaxLength <= bottomMaxLength && topMinLength > bottomMinLength) return false;
		return true;
	}

}