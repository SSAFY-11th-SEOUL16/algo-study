package week8.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Java(2864ms)
public class BJ1943_동전분배_1 {
	static int n;
	static boolean[] flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int t = 0; t < 3; t++) {
			n = Integer.parseInt(br.readLine());
			flag = new boolean[100055];
			flag[0] = true;
			int[][] arr = new int[n][2];
			int sum = 0;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int coin = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				sum += coin*cnt;
				arr[i][0] = coin;
				arr[i][1] = cnt;
			}
			if(sum%2==1) {
				System.out.println(0);
				continue;
			}

			for(int i=0; i<n; i++) {
				int nowCoin = arr[i][0];
				int nowCnt = arr[i][1];
				for(int j=sum/2; j>=0; j--) {
					for(int k=nowCoin; k<=nowCoin*nowCnt; k+=nowCoin) {
						if(j+k>sum/2) break;
						if(!flag[j]) continue;
						flag[j+k] = true;
					}
				}
			}
			System.out.println(flag[sum/2] ? 1 : 0);
		}
	}
}
