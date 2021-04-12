package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_무선충전_모의SW역량테스트 {

	private static class BC {
		int x, y, C, P;
		public BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			C = c;
			P = p;
		}
	}

	private static int[] dx = {0, -1, 0, 1, 0};
	private static int[] dy = {0, 0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_무선충전.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int[] moveA = new int[M + 1], moveB = new int[M + 1];
			BC[] bcs = new BC[A];

			st = new StringTokenizer(br.readLine());
			moveA[0] = 0; moveB[0] = 0;
			for(int i = 0; i < M; i++) moveA[i + 1] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) moveB[i + 1] = Integer.parseInt(st.nextToken());

			for(int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()); int y = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken()); int P = Integer.parseInt(st.nextToken());
				bcs[i] = new BC(y, x, C, P);
			}
			sb.append("#").append(tc).append(" ").append(findMaximumCharge(M, A, moveA, moveB, bcs)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int findMaximumCharge(int M, int A, int[] moveA, int[] moveB, BC[] bcs) {
		int totalCharge = 0;
		int ax = 1, ay = 1; int bx = 10, by = 10;
		for(int m = 0; m < M + 1; m++) {
			int nax = ax + dx[moveA[m]], nay = ay + dy[moveA[m]];
			int nbx = bx + dx[moveB[m]], nby = by + dy[moveB[m]];

			boolean[] isChargeA = findAdjBC(nax, nay, A, bcs);
			boolean[] isChargeB = findAdjBC(nbx, nby, A, bcs);

			int maxCharge = 0;
			for(int a = 0; a < A; a++) {
				for(int b = 0; b < A; b++) {
					if(isChargeA[a] || isChargeB[b]) {
						if(isChargeA[a] && isChargeB[b]) {
							if(a == b) maxCharge = Math.max(maxCharge, bcs[a].P);
							else maxCharge = Math.max(maxCharge, bcs[a].P + bcs[b].P);
						} else {
							if(isChargeA[a]) maxCharge = Math.max(maxCharge, bcs[a].P);
							else maxCharge = Math.max(maxCharge, bcs[b].P);
						}
					}
				}
			}
			totalCharge += maxCharge;
			ax = nax; ay = nay; bx = nbx; by = nby;
		}
		return totalCharge;
	}

	private static boolean[] findAdjBC(int x, int y, int A, BC[] bcs) {
		boolean[] isCharge = new boolean[A];
		for(int i = 0; i < A; i++)
			if(getDist(x, y, bcs[i].x, bcs[i].y) <= bcs[i].C) isCharge[i] = true;
		return isCharge;
	}

	private static int getDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
