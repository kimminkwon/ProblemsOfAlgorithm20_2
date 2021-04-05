package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 거짓말_1043 {

    private static class UnionFind {
        int[] uf;

        public UnionFind(int n) {
            uf = new int[n + 1];
            for(int i = 1; i < n + 1; i++)
                uf[i] = i;
        }

        public int findSet(int i) {
            if(uf[i] == i) return i;
            else return uf[i] = findSet(uf[i]);
        }

        public boolean isSame(int i, int j) {
            return findSet(i) == findSet(j);
        }

        public void unionSet(int i, int j) {
            if(!isSame(i, j)) {
                int iParent = findSet(i), jParent = findSet(j);
                if(knowTrue[iParent]) {
                    uf[jParent] = iParent;
                } else if(knowTrue[jParent]){
                    uf[iParent] = jParent;
                } else {
                    if(iParent >= jParent) {
                        uf[jParent] = iParent;
                    } else {
                        uf[iParent] = jParent;
                    }
                }
            }
        }
    }

    private static int N, M;
    private static boolean[] knowTrue;
    private static int[] numOfPartyPeople;
    private static ArrayList<Integer>[] party;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        knowTrue = new boolean[N + 1];
        party = new ArrayList[M];
        numOfPartyPeople = new int[M];
        st = new StringTokenizer(br.readLine());

        int numOfKnow = Integer.parseInt(st.nextToken());
        for(int i = 0; i < numOfKnow; i++)
            knowTrue[Integer.parseInt(st.nextToken())] = true;

        UnionFind uf = new UnionFind(N);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            numOfPartyPeople[i] = Integer.parseInt(st.nextToken());
            party[i] = new ArrayList<>();
            for(int j = 0; j < numOfPartyPeople[i]; j++)
                party[i].add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < M; i++) {
            if(numOfPartyPeople[i] != 0) {
                int flagPeople = party[i].get(0);
                for(int j = 0; j < numOfPartyPeople[i]; j++) {
                    uf.unionSet(flagPeople, party[i].get(j));
                }
            }
        }

        int result = 0;
        for(int i = 0; i < M; i++) {
            boolean isLie = true;
            if(numOfPartyPeople[i] != 0) {
                for(int j = 0; j < numOfPartyPeople[i]; j++) {
                    if(knowTrue[uf.findSet(party[i].get(j))]) isLie = false;
                }
            }
            if(isLie) result++;
        }
        System.out.println(result);

    }
}
