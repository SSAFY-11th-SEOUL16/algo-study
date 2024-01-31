package week2.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12865_평범한배낭 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n+1][2];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());;
		}
		
		Arrays.sort(arr,(o1, o2) -> o1[0] - o2[0]);
		
		int[] value = new int[k+1];
		for(int[] num: arr) {
			for(int i=k; i>=num[0]; i--) {
				value[i] = Math.max(value[i-num[0]] + num[1], value[i]);
			}
		}
		System.out.println(value[k]);
	}
}
