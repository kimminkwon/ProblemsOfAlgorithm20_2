package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_삼국지_모의A형테스트 {

	private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_삼국지.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] area = new int[N][N], person = new int[N][N], addPerson = new int[N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					area[i][j] = Integer.parseInt(st.nextToken());
			}

			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					person[i][j] = Integer.parseInt(st.nextToken());
			}

			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					addPerson[i][j] = Integer.parseInt(st.nextToken());
			}

			sb.append("#").append(tc).append(" ").append(doThreeNationGame(N, area, person, addPerson)).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static int doThreeNationGame(int N, int[][] area, int[][] person, int[][] addPerson) {
		int areaNum = 1;
		if(isEnd(N, area)) return countVictoryArea(N, area, person);
		while(true) {
			if(isPossibleGame(areaNum, N, area)) { // areaNum: 차례, 그 차례의 지역이 있다면?
				doAttackOtherArea(areaNum, N, area, person);
				doSupportForMyArea(areaNum, N, area, person);
				doAddPerson(N, person, addPerson);
				if(isEnd(N, area)) break;
			}
			areaNum = nextArea(areaNum);
		}
		return countVictoryArea(N, area, person);
	}

	private static int countVictoryArea(int N, int[][] area, int[][] person) {
		int count = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(area[i][j] > 0) count += person[i][j];
		return count;
	}

	private static void doSupportForMyArea(int areaNum, int N, int[][] area, int[][] person) {
		// 1. 지원을 시작, 변화한 값을 따로 담는다.
		int[][] supportValue = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(area[i][j] == areaNum) {
					if(isAllMyArea(areaNum, i, j, N, area)) { // 인접한 지역에 다른 나라의 지역이 없으므로 전부 지원한다.
						support(i, j, N, area, person, supportValue, 0);
						support(i, j, N, area, person, supportValue, 1);
						support(i, j, N, area, person, supportValue, 2);
						support(i, j, N, area, person, supportValue, 3);
					} else { // 인접한 지역에 적국이 있을 경우
						for(int d = 0; d < 4; d++) { // 4방을 탐색한다.
							int nx = i + dx[d], ny = j + dy[d];
							if(isOut(nx, ny, N) || area[nx][ny] != areaNum) continue; // 아웃이거나, 다른 나라면 취소
							if(person[i][j] <= person[nx][ny] * 5) continue; // 인접한 아군 나라의 5배보다 내 지역이 병력이 작거나 같다면 지원하지 않는다.
							support(i, j, N, area, person, supportValue, d);
						}
					}
				}
			}
		}
		// 2. 변화한 값을 병력 배열에 반영
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				person[i][j] = person[i][j] + supportValue[i][j];
	}

	private static void support(int x, int y, int N, int[][] area, int[][] person, int[][] supportValue, int dir) {
		int supportPerson = person[x][y] / 5;
		int nx = x + dx[dir], ny = y + dy[dir];
		if(isOut(nx, ny, N) || area[nx][ny] == 0) return;
		supportValue[x][y] = supportValue[x][y] - supportPerson;
		supportValue[nx][ny] = supportValue[nx][ny] + supportPerson;
	}

	private static boolean isAllMyArea(int areaNum, int x, int y, int N, int[][] area) {
		for(int d = 0; d < 4; d++) { // 4방을 탐색한다.
			int nx = x + dx[d], ny = y + dy[d];
			if(isOut(nx, ny, N)) continue;
			if(area[nx][ny] > 0 && area[nx][ny] != areaNum) return false;
		}
		return true;
	}

	private static void doAttackOtherArea(int areaNum, int N, int[][] area, int[][] person) {
		// 1. 공격을 시작, 변화한 값을 따로 담는다.
		int[][] attackValue = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(area[i][j] != areaNum && area[i][j] > 0) { // 내가 공격할 수 있는 지역인가? && 산악지역이 아닌가? ==> 공격할 대상
					int totalAdjAreaPerson = getTotalAdjAreaPerson(i, j, N, areaNum, area, person);
					if(totalAdjAreaPerson <= person[i][j] * 5) continue; // 5배보다 작거나 같다면 공격을 하지 않는다.
					attack(areaNum, i, j, N, area, person, attackValue); // 5배를 초과한다면 공격을 한다.
				}
			}
		}

		// 2. 변화한 값을 병력 배열에 반영
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				person[i][j] = person[i][j] + attackValue[i][j];

		// 3. 지역이 바뀔 곳이 있는지 확인한다.
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(person[i][j] < 0 && area[i][j] != areaNum && area[i][j] != 0) { // 0보다 작은 결과가 나왔다면 바꿔준다.
					person[i][j] = person[i][j] * (-1);
					area[i][j] = areaNum;
				}
	}

	private static void attack(int areaNum, int x, int y, int N, int[][] area, int[][] person, int[][] attackValue) {
		for(int d = 0; d < 4; d++) { // 4방을 탐색한다.
			int nx = x + dx[d], ny = y + dy[d];
			if(isOut(nx, ny, N)) continue;
			if(area[nx][ny] == areaNum) { // 공격을 하는 지역
				int goPerson = person[nx][ny] / 4; // 공격할 사람 수
				attackValue[nx][ny] = attackValue[nx][ny] - goPerson; // 공격한 사람만큼 빠질 것을 체크
				attackValue[x][y] = attackValue[x][y] - goPerson; // 공격을 당했으니 그만큼 값을 뺀다.
			}
		}
	}

	private static int getTotalAdjAreaPerson(int x, int y, int N, int areaNum, int[][] area, int[][] person) {
		int adjPerson = 0;
		for(int d = 0; d < 4; d++) { // 4방을 탐색한다.
			int nx = x + dx[d], ny = y + dy[d];
			if(isOut(nx, ny, N)) continue;
			if(area[nx][ny] == areaNum) adjPerson += person[nx][ny]; // 만약 4방 중 내 나라가 있다면 그 병력 수를 더한다.
		}
		return adjPerson;
	}

	private static void doAddPerson(int N, int[][] person, int[][] addPerson) {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				person[i][j] += addPerson[i][j];
	}

	private static boolean isEnd(int N, int[][] area) {
		boolean[] existArea = new boolean[4];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				existArea[area[i][j]] = true;

		int cnt = 0;
		for(int i = 1; i < 4; i++)
			if(existArea[i]) cnt++;

		return cnt == 1;
	}

	private static boolean isPossibleGame(int areaNum, int N, int[][] area) {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(area[i][j] == areaNum) return true;
		return false;
	}

	private static int nextArea(int areaNum) {
		switch (areaNum) {
			case 1: return 2;
			case 2: return 3;
			case 3: return 1;
		}
		return -1;
	}

	private static boolean isOut(int x, int y, int N) {
		return x >= N || y >= N || x < 0 || y < 0;
	}

	private static void print(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.printf("%6d ", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}

