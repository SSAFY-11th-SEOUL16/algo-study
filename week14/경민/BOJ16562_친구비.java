import java.util.*;
import java.io.*;

public class Main {

    /*
    친구의 친구는 친구

    유니온파인드 ???
     */
    static int n, m, k, result;
    static int[] fee, parent;

    public static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); // 쓸 수 있는 돈
        fee = new int[n + 1];
        parent = new int[n + 2];

        for (int i = 1; i <= n + 1; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            fee[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int pay = 0;
        int me = n + 1;
        for (int i = 1; i <= n; i++) {
            if (find(me) != find(i)) {
                // i가 친구가 아니라면 i 친구들 중 가장 최소 비용 친구 찾기
                int min = Integer.MAX_VALUE;

                for (int j = 1; j <= n; j++) {
                    if (find(j) == find(i)) min = Math.min(min, fee[j]);
                }
                union(me, i);
                pay += min;
            }

            if(pay > k) break;
        }

        System.out.println(pay > k ? "Oh no" : pay);

    }
}
