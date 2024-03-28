import java.io.*;
import java.util.*;

public class BJ14267_회사문화1 {
    static int n, m; //직원 수, 칭찬 수
    static int good[]; //직원 별 칭찬 합계
    static ArrayList<ArrayList<Integer>> al;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        good = new int[n+1];

        al = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            al.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        
        //후임 연결
        for(int i=2;i<=n;i++) {
            int top = Integer.parseInt(st.nextToken());
            al.get(top).add(i);
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken()); //칭찬을 받은 직원 번호
            int cnt = Integer.parseInt(st.nextToken()); //칭찬의 수치

            good[cur] += cnt;
        }

        dfs(1);
        
        for (int i = 1; i <= n; i++) {
            System.out.print(good[i] + " ");
        }
    }

    static void dfs(int x) {
        for(Integer i : al.get(x)) {
            good[i] += good[x];
            dfs(i);
        }
    }
}

