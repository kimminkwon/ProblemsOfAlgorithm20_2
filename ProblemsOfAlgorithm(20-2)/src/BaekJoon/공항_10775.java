package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 공항_10775 {

    private static class UnionFind {
        int[] uf;

        public UnionFind(int n) {
            this.uf = new int[n + 1];
            for(int i = 0; i < n + 1; i++) uf[i] = i;
        }

        public int findSet(int i) {
            if(uf[i] == i) return i;
            return uf[i] = findSet(uf[i]);
        }

        public void unionSet(int i, int j) {
            int iParent = findSet(i), jParent = findSet(j);
            if(i < j) uf[j] = iParent;
            else uf[i] = jParent;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int[] planes = new int[P];
        
        for(int i = 0; i < P; i++) 
            planes[i] = Integer.parseInt(br.readLine());

        int count = 0;
        UnionFind uf = new UnionFind(G);
        for(int i = 0; i < P; i++) {
            int rootIndex = uf.findSet(planes[i]);
            if(rootIndex == 0) break;
            uf.unionSet(rootIndex - 1, rootIndex);
            count++;
        }
        System.out.println(count);
    }
}
