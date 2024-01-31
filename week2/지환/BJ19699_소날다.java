package week2.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ19699_소날다 {
	static Set<Integer> set = new HashSet<>();
	static int n;
	static int m;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0, 0);
		if(set.size() == 0) {
			System.out.println(-1);
			return;
		}
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i: list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public static void dfs(int idx, int cnt, int sum) {
		if(cnt==m) {
			if(isPrime(sum)) {
				set.add(sum);
				return;
			}
		}
		for(int i=idx; i<n; i++) {
			dfs(i+1, cnt+1, sum+arr[i]);
		}
	}
	
	public static boolean isPrime(int n) {
		for(int i=2; i<=(int) Math.sqrt(n); i++) {
			if(n%i == 0) {
				return false;
			}
		}
		return true;
	}
}
