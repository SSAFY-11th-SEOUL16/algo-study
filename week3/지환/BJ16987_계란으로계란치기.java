package week3.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16987_계란으로계란치기 {
	static int n;
	static int[] body;
	static int[] weight;
	static int max = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		body = new int[n];
		weight = new int[n];
		StringTokenizer st;
		for(int i=0; i<n; i++ ) {
			st = new StringTokenizer(br.readLine(), " ");
			body[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		System.out.println(max);
	}
	
	public static void dfs(int tmp, int cnt) {
		if(tmp == n) {
			max = Math.max(max, cnt);
			return;
		}
		//2가지 경우 분기. 잡으려는 계란이 깨졌거나 안깨졌거나
		//깨졌으면 바로 다음 계란 픽
		if(body[tmp] <= 0) {
			dfs(tmp+1, cnt);
			//안깨졌으면 모든 계란중 안깨진 계란 선택
		} else {
			boolean crash = false;
			for(int i=0; i<n; i++) {
				if(i==tmp) continue;
				if(body[i] > 0) {
					body[i] -= weight[tmp];
					body[tmp] -= weight[i];
					crash = true;
					//부딪힌게 있을 경우 카운팅 후 다음걸로 넘어간다
					dfs(tmp+1, cnt + ((body[i] <= 0)? 1 : 0) + ((body[tmp] <= 0) ? 1 :0));
					body[i] += weight[tmp];
					body[tmp] += weight[i];
				}
			}
			//부딪힌게 없을 경우 바로 넘어간다.
			if(!crash) {
				dfs(tmp+1, cnt);
			}
		}
	}
}
