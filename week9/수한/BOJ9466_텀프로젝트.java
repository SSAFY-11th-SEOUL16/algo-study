
package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_9466_텀프로젝트{
	
	static class Info{
		int cnt;
		int stNum;
		
		public Info(int cnt, int stNum) {
			this.cnt = cnt;
			this.stNum = stNum;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		int[] select = new int[100_001];
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			tokens = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				select[i] = Integer.parseInt(tokens.nextToken());
			}
			
			int numInclude = 0;
			Info[] info = new Info[n+1];
			for (int i = 1; i <= n; i++) {
				if(info[i] == null) {
					int s = i;
					info[s] = new Info(1,i);
					int ns = select[s];
					while(info[ns] == null) {
						info[ns] = new Info(info[s].cnt+1,i);
						s = ns;
						ns = select[s];
					}
					if(info[ns].stNum == info[s].stNum) {
						numInclude += (info[s].cnt - info[ns].cnt + 1);
					}
				}
			}
			sb.append(n-numInclude).append("\n");
		}
		
		System.out.println(sb);
	}
}
