import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static ArrayList<Integer>[] treeData;
	static int [][] dp;
	static boolean [] check;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		//데이터 초기화
		n = Integer.parseInt(br.readLine());
		treeData = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			treeData[i] = new ArrayList<>();
		}
		for(int i = 0; i < n-1; i++) {
			str = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(str.nextToken());
			int tmp2 = Integer.parseInt(str.nextToken());
			
			treeData[tmp].add(tmp2);
			treeData[tmp2].add(tmp);
		}
		
		dp = new int[n+1][2];	//현재 노드를 루트로 하는 트리에서 현재 노드가 얼리어답터일 때와 아닐 때 필요한 얼리어답터의 수
		check = new boolean[n+1];	//얼리어답터로 강제 변환 여부 확인
		
		cal(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	static void cal(int node) {
		check[node] = true;
		dp[node][0] = 1;
		
		for(int tmp : treeData[node]) {
			if(check[tmp]) {continue;}
			cal(tmp);
			dp[node][1] += dp[tmp][0];    //node번째 노드를 루트로 하는 트리에서 node번째 노드가 얼리어답터가 아닐 때 필요한 최소 얼리어답터의 수
			dp[node][0] += Math.min(dp[tmp][0], dp[tmp][1]);  //node번째 노드를 루트로 하는 트리에서 node번째 노드가 얼리어답터일 때 필요한 최소 얼리어답터의 수
		}
	}
}
