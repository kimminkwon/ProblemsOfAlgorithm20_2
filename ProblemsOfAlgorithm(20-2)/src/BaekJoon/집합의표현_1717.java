package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 집합의표현_1717 {
    private static class UnionFind {
        private int[] uf;

        public UnionFind(int n) {
            uf = new int[n + 1];
            Arrays.fill(uf, -1);
        }

        public int findSet(int i) {
            if(uf[i] < 0) return i;
            else return uf[i] = findSet(uf[i]);
        }

        public boolean isSame(int i, int j) {
            return findSet(i) == findSet(j);
        }

        public void unionSet(int i, int j) {
            if(!isSame(i, j)) {
                int iParent = findSet(i), jParent = findSet(j);
                int iParentValue = -1 * uf[iParent], jParentValue = -1 * uf[jParent];
                if(iParentValue > jParentValue) {
                    uf[iParent] = (iParentValue + jParentValue) * -1;
                    uf[jParent] = iParent;
                } else {
                    uf[jParent] = (iParentValue + jParentValue) * -1;
                    uf[iParent] = jParent;
                }
            }
        }
    }

    private static int n, m;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        UnionFind uf = new UnionFind(n);

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(oper == 0) uf.unionSet(a, b);
            else sb.append(uf.isSame(a, b) ? "YES" : "NO").append("\n");
        }

        System.out.print(sb.toString());
    }
}
