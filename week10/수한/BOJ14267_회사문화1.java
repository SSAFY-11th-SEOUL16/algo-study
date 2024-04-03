package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_14267{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		tokens = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(tokens.nextToken()); // 회사 직원 수
		int m = Integer.parseInt(tokens.nextToken()); // 최초 칭찬 횟수
		
		// i번 직원에게 w만큼 칭찬
		List<Integer>[] childs = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			childs[i] = new ArrayList<>();
		}
		
		tokens = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(tokens.nextToken());
			if(num == -1) continue;
			childs[num].add(i);
		}
		
		int[] result = new int[n+1];
		
		for (int i = 0; i < m; i++) {
			tokens = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(tokens.nextToken());
			int w = Integer.parseInt(tokens.nextToken());
			result[num] += w;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for (int child : childs[v]) {
				result[child] += result[v];
				q.add(child);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb);
	}
}
