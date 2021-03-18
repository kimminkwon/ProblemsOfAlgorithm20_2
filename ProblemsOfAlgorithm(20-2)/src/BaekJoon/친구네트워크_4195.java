package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 친구네트워크_4195 {
    private static class UnionFind {
        int[] uf;

        public UnionFind(int n) {
            uf = new int[n * 2 + 1]; // 관계가 n개라면 사람은 최대 2n까지 있다.
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

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 0; tc < T; tc++) {
            int F = Integer.parseInt(br.readLine());
            UnionFind uf = new UnionFind(F);
            Map<String, Integer> hm = new HashMap<>();
            int indexForFriend = 0;

            for(int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken(), friend2 = st.nextToken(); // 두명의 친구 이름을 받는다.
                if(!hm.containsKey(friend1)) {
                    hm.put(friend1, indexForFriend); // Map에 저장 (이름, 인덱스 번호)
                    uf.uf[indexForFriend++] = -1; // 해당 인덱스 번호의 노드를 생성
                }
                if(!hm.containsKey(friend2)) {
                    hm.put(friend2, indexForFriend); // Map에 저장 (이름, 인덱스 번호)
                    uf.uf[indexForFriend++] = -1; // 해당 인덱스 번호의 노드를 생성
                }
                // 각 인덱스를 유니온한다. 유니온한 결과는 유니온 된 노드의 하위 노드 개수를 반환
                sb.append(uf.unionSet(hm.get(friend1), hm.get(friend2))).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}
