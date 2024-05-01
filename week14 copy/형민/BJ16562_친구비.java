import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k; //학생수, 친구관계수, 가지고 있는 돈
    static int[] friendCost; //친구비
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> al = new ArrayList<>();
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        friendCost = new int[n + 1];
        visited = new boolean[n + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            friendCost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= n; i++) {
            al.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            al.get(a).add(b);
            al.get(b).add(a);
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            if(!visited[i]) {
                min = friendCost[i];
                visited[i] = true;
                dfs(i);

                result += min;
            }
        }

        if(k < result ) System.out.println("Oh no");
        else System.out.println(result);
    }

    static void dfs(int x) {
        for(int i : al.get(x)) {
            if(visited[i]) continue;

            visited[i] = true;
            min = Math.min(min, friendCost[i]);
            dfs(i);
        }
    }
}
