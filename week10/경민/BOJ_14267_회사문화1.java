import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] praise, dp;   
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();   

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        praise = new int[n + 1];
        dp = new int[n + 1];

        for (int i = 0; i <= n; i++)
            tree.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        st.nextToken();

        for (int i = 2; i <= n; i++) 
            tree.get(Integer.parseInt(st.nextToken())).add(i);
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            praise[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }
    
        dfs(1, 0);
        
        for (int i = 1; i <= n; i++)
            sb.append(dp[i]).append(" ");

        System.out.println(sb);
    }
    
    static void dfs(int index, int sum) {
        sum += praise[index];     
        dp[index] = sum;      
   
        for (int i : tree.get(index))
            dfs(i, sum);
    }
}