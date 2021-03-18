package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 여행가자_1976 {

    private static class UnionFind {
        int[] uf;

        public UnionFind(int m) {
            uf = new int[m + 1];
            Arrays.fill(uf, -1);
        }

        public int findSet(int i) {
            if(uf[i] < 0) return i;
            else return uf[i] = findSet(uf[i]);
        }

        public boolean isSame(int i, int j) {
            return findSet(i) == findSet(j);
        }

        public int unionSet(int i, int j) {
            if(!isSame(i, j)) {
                int iParent = findSet(i), jParent = findSet(j);
                int iParentValue = -1 * uf[iParent], jParentValue = -1 * uf[jParent];
                if(iParentValue > jParentValue) {
                    uf[iParent] = (iParentValue + jParentValue) * -1;
                    uf[jParent] = iParent;
                    return uf[iParent] * -1;
                } else {
                    uf[jParent] = (iParentValue + jParentValue) * -1;
                    uf[iParent] = jParent;
                    return uf[jParent] * -1;
                }
            }
            return uf[findSet(i)] * -1;
        }
    }

    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        UnionFind uf = new UnionFind(N);

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                if(Integer.parseInt(st.nextToken()) == 1) {
                    uf.unionSet(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        String res = "YES";
        int root = -1;
        for(int i = 0; i < M; i++) {
            int currRoot = uf.findSet(Integer.parseInt(st.nextToken()));
            if(root == -1) root = currRoot;
            else if(currRoot != root) res = "NO";
        }

        System.out.println(res);
    }
}
