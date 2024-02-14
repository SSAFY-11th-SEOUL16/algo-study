package week4.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ2792_보석상자 {
	static int n;
	static int m;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		int max = 0;
		for(int i=0; i<m; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(arr[i], max);
		}
		System.out.println(binarySearch(max));
	}
	
	public static int binarySearch(int max) {
		int left = 1, right = max;
		while(left <= right) {
			int mid = (left+right)/2;
			if(calculate(mid) <= n) {
				right = mid -1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
	
	public static int calculate(int now) {
		int cnt = 0;
		for(int i=0; i<m; i++) {
			cnt = arr[i]%now==0 ? cnt + arr[i]/now : cnt + arr[i]/now+1;
		}
		return cnt;
	}
}
