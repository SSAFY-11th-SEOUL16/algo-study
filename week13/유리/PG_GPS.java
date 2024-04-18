import java.util.ArrayList;

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int [][] dp = new int [k][n+1];
		ArrayList<Integer> [] data = new ArrayList[n+1];
		for(int i = 1; i <= n; i++) {
			data[i] = new ArrayList<>();
			data[i].add(i);
		}
		
		for(int i = 0; i < m; i++) {
			int tmp = edge_list[i][0];
			int tmp2 = edge_list[i][1];
			data[tmp].add(tmp2);
			data[tmp2].add(tmp);
		}
		
		for(int i = 0; i< k; i++) {
			for(int x = 1; x <= n; x++) {
				dp[i][x] = 1001;
			}
		}
		dp[0][gps_log[0]] = 0;
		
		for(int i = 0; i <k-1; i++) {
			for(int x=1;x<=n;x++) {	//시작지점
				//System.out.println(i+"/"+x);
				if(dp[i][x] == 1001) continue;	//도달 불가
				//System.out.println("도달 가능");
				for(int y =0; y<data[x].size();y++) {
					int next = data[x].get(y);
					int nv = 0;
					if(gps_log[i+1]!=next) {
						nv = 1;
					}
					dp[i+1][next] = Math.min(dp[i+1][next], dp[i][x]+nv);
				}
			}
		}
		
		int answer = dp[k-1][gps_log[k-1]];
		if(answer == 1001) {
			answer = -1;
		}
        return answer;
    }
}
