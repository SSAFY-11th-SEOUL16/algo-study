package week2.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1149_RGB거리 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[3][n];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cost[0][i] = Integer.parseInt(st.nextToken());
			cost[1][i] = Integer.parseInt(st.nextToken());
			cost[2][i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<n; i++) {
			cost[0][i] += Math.min(cost[1][i-1], cost[2][i-1]);
			cost[1][i] += Math.min(cost[0][i-1], cost[2][i-1]);
			cost[2][i] += Math.min(cost[0][i-1], cost[1][i-1]);
		}
		
		System.out.println(Math.min(cost[2][n-1], Math.min(cost[0][n-1], cost[1][n-1])));
	}
}
