import java.io.*;
import java.util.*;

public class BJ2229_조짜기 {
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[n+1];
		
		for(int i=1;i<=n;i++) arr[i] = Integer.parseInt(st.nextToken());
		
		//dp[i] : i번째 학생까지 고려했을 때 조가 잘 짜여진 정도의 최댓값
		int dp[] = new int[n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<i;j++) {
				dp[i] = Math.max(dp[i], dp[j - 1] + func(j, i));
			}
		}

		System.out.println(dp[n]);
	}
	//j ~ i 학생으로 조를 편성했을 때 조가 잘 짜여진 정도를 반환
	static int func(int startIdx, int endIdx) {
		int minV = Integer.MAX_VALUE;
		int maxV = Integer.MIN_VALUE;
		
		for(int i=startIdx;i<=endIdx;i++) {
			minV = Math.min(minV, arr[i]);
			maxV = Math.max(maxV, arr[i]);
		}
		
		return maxV - minV;
	}

}

