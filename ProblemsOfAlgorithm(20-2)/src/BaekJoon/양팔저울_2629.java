package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 양팔저울_2629 {
	
	private static int numOfCu, numOfGu; // 추와 구슬의 개수 
	private static int[] cus;// 추의 무게를 저장할 배열
	private static int[] gus;// 구슬의 무게를 저장할 배열
	private static boolean[] isPossableGus; // i-index의 구슬이 측정가능한 무게인지 저장하는 배열
	private static boolean[][] visited; // 무게가 15000까지 가능: 중복된 상태공간 트리인지 확인하기 위한 방문 배열
	
	public static void main(String[] args) throws Exception {
		// 0. 입력 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		numOfCu = Integer.parseInt(br.readLine());
		cus = new int[numOfCu];
		visited = new boolean[numOfCu + 1][15010];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < numOfCu; i++)
			cus[i] = Integer.parseInt(st.nextToken());
		
		numOfGu = Integer.parseInt(br.readLine());
		gus = new int[numOfGu];
		isPossableGus = new boolean[numOfGu];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < numOfGu; i++) {
			gus[i] = Integer.parseInt(st.nextToken());			
		}
		// 1. 모든 케이스를 백 트래킹을 통해 살핀다. ===> 본 알고리즘의 코어 메소드
		makeCaseOfPlacementForCu(0, 0, 0, 0);
		// 2. isPossableGus 배열을 통해 결과를 출력한다.
		printResult();
	}

	// 결과 출력용 메소드: isPossableGus가 T/F에 따라서 출력문을 결정한다.
	private static void printResult() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < numOfGu; i++) {
			if(isPossableGus[i]) sb.append("Y").append(" ");
			else sb.append("N").append(" ");
		}
		System.out.println(sb.toString());
	}

	// DFS를 통해 백트래킹하며 모든 케이스를 살피는 메소드
	// index: 현재까지 확인한 인덱스, weightLeft: 왼쪽 무게, weightRight: 오른쪽 무게
	private static void makeCaseOfPlacementForCu(int depth, int index, int weightLeft, int weightRight) {
		
		int possableWeight = Math.abs(weightLeft - weightRight); // 두 무게의 차이값 = 현재 측정할 수 있는 무게를 뜻한다.
		if(visited[depth][possableWeight]) return; // 해당 무게가 이미 확인한 경우라면 리턴해준다.
		// 이는 depth k에서 무게 w를 확인 시, depth m(for m > k)에서 무게 w를 만나면 적어도 depth k에서 depth m에서 탐색 가능한 경우는 모두 탐색하기 때문이다.
		
		visited[depth][possableWeight] = true;
		int findIndex = findValueInGus(possableWeight); // 해당 무게가 최대 7개의 구슬 중 존재하는가? 없다면 -1을 반환 있다면 인덱스를 반환
		if(findIndex >= 0) isPossableGus[findIndex] = true; // 해당 구슬이 있으므로 측정 가능함을 체크
		if(index == numOfCu) return; // 인덱스가 끝까지 왔다면 리턴해준다.

		
		// 총 3가지 경우의 수를 체크한다. 1) 왼쪽저울에 현재 추를 올린다. 2) 오른쪽 저울에 현재 추를 올린다. 3) 현재 추를 무시하고 올리지 않는다.
		makeCaseOfPlacementForCu(depth + 1, index + 1, weightLeft + cus[index], weightRight);
		makeCaseOfPlacementForCu(depth + 1, index + 1, weightLeft, weightRight + cus[index]);
		makeCaseOfPlacementForCu(depth + 1, index + 1, weightLeft, weightRight);		
	}
	
	// 측정 가능 무게를 받아서 해당 무게가 구슬 배열에 존재하는지 확인하는 메소드
	private static int findValueInGus(int possableWeight) {
		for(int i = 0; i < numOfGu; i++) 
			if(possableWeight == gus[i]) return i;
		return -1;
	}
	
}
