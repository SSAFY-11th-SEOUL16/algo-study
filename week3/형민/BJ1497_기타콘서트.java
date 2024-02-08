package day0208;

import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static String[] arr;
	static int resultCnt = Integer.MAX_VALUE;
	static int resultSum = Integer.MIN_VALUE;
	static int mSum = 0;
	static int visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new String[n];
		visited = new int[m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			arr[i] = st.nextToken();
		}

		for (int i = 0; i < n; i++) {
			mSum = 0;
			visited = new int[m];
			backtrack(i, 1);
		}

		if (resultCnt == Integer.MAX_VALUE) resultCnt = -1;
		System.out.println(resultCnt);
	}

	static void backtrack(int x, int cnt) {

		String curStr = arr[x];
		boolean flag = false;
		for (int j = 0; j < m; j++) {
			if (curStr.charAt(j) == 'Y') {
				// 쓸모있는 곡
				if (visited[j] == 0) {
					mSum++;
					flag = true;
				}
				visited[j]++;
			}
		}

		if (resultSum <= mSum && mSum != 0) {
			if (resultSum == mSum) {
				if (cnt < resultCnt)
					resultCnt = cnt;
			} else {
				resultSum = mSum;
				resultCnt = cnt;
			}
		}

		for (int i = x + 1; i < n; i++) {
			if (flag)
				backtrack(i, cnt + 1);
		}

		for (int j = 0; j < m; j++) {
			if (curStr.charAt(j) == 'Y') {
				visited[j]--;
				if (visited[j] == 0) {
					mSum--;
				}
			}
		}
	}

}
