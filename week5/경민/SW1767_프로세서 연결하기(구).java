import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 가장자리가 아닌 코어들의 좌표를 코어 리스트에 저장 후
 * 이중 for문을 통해 코어들 전선 그어보며 (연결한 코어 수의 최대) & (전선 길이 합의 최소) 구하기
 * 
 * 1. 가장 작은 길이로 선정 된 방향을 true 로 변경하여 다음 코어 탐색 시 지나가지 못하도록
 * 2. 코어 순서는 0 1 2 3 4 -> 1 2 3 4 0 -> 2 3 4 0 1 -> 3 4 0 1 2 -> 4 0 1 2 3  순서로 탐색할 수 있도록 cores.get((j + i) % cores.size()); 수식 이용
 * 
 */
public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		List<int[]> cores = new ArrayList<int[]>();

		for (int t = 1; t <= T; t++) {
			cores.clear();
			int n = Integer.parseInt(br.readLine());
			boolean[][] visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						if (i != 0 && i != n - 1 && j != 0 && j != n - 1)
							cores.add(new int[] { i, j }); // 가장자리 코어가 아니라면 코어 리스트에 저장
						visited[i][j] = true;
					}
				}
			}

			int result = Integer.MAX_VALUE; // 전선 길이의 합 중 가장 작은 길이
			int sum = 0; // 코어들의 전선 길이의 합
			int minLength = Integer.MAX_VALUE; // 코어의 상하좌우 중 연결할 수 있는 전선 중 가장 작은 길이
			int d = -1; // 코어의 상하좌우 중 가장 작은 길이의 전선 방향 ( 1: 상, 2: 하, 3: 좌, 4: 우 )
			int maxCount = 0; // 연결한 코어 수 최대값
			int count = 0; // 연결한 코어 수

			boolean flag = true; // 가장자리까지 전선을 연결할 수 있는지에 대한 flag
			boolean[][] copy = new boolean[n][n];

			// 지나갈 수 있는 경로인지 체크하기 위한 copy 배열 초기화
			for (int i = 0; i < cores.size(); i++) {
				for (int c = 0; c < n; c++) {
					copy[c] = Arrays.copyOf(visited[c], visited[c].length);
				}
				// (i=0) 0 1 2 3 4 -> (i=1) 1 2 3 4 0 -> (i=2) 2 3 4 0 1 -> (i=3) 3 4 0 1 2 -> (i=4) 4 0 1 2 3
				for (int j = 0; j < cores.size(); j++) {
					int x =  cores.get((j + i) % cores.size())[0];
					int y =  cores.get((j + i) % cores.size())[1];
					minLength = Integer.MAX_VALUE; 
					d = -1;

					// 상
					flag = true;
					for (int k = x - 1; k >= 0; k--) {
						if (copy[k][y]) { 
							// 지나갈 수 없는 경로면 break; 다음 방향 탐색
							flag = false; break;
						}
					}
					if (flag && minLength > x) {
						d = 1; minLength = x;
					}
					
					// 하
					flag = true;
					for (int k = x + 1; k < n; k++) {
						if (copy[k][y]) {
							flag = false; break;
						}
					}
					if (flag && minLength > n - x - 1) {
						d = 2; minLength = n - x - 1;
					}

					// 좌
					flag = true;
					for (int k = y - 1; k >= 0; k--) {
						if (copy[x][k]) {
							flag = false; break;
						}
					}
					if (flag && minLength > y) {
						d = 3; minLength = y;
					}
					
					// 우
					flag = true;
					for (int k = y + 1; k < n; k++) {
						if (copy[x][k]) {
							flag = false; break;
						}
					}
					if (flag && minLength > n - y - 1) {
						d = 4; minLength = n - y - 1;
					}

					// 가장 최소 길이의 전선 방향은 다음 코어 탐색 시 지나갈 수 없도록 true 로 변경 후 전선 길이의 합(sum)에 추가
					if (d == 1) {
						for (int k = x; k >= 0; k--) 
							copy[k][y] = true;
						sum += x;
					} else if (d == 2) {
						for (int k = x; k < n; k++) 
							copy[k][y] = true;
						sum += n - x - 1;
					} else if (d == 3) {
						for (int k = y; k >= 0; k--) 
							copy[x][k] = true;
						sum += y;
					} else if (d == 4) {
						for (int k = y; k < n; k++) 
							copy[x][k] = true;
						sum += n - y - 1;
					} else if (d == -1) {
						continue;
					}
					count++; // 연결한 코어 수 하나 증가
				}
				
				// 모든 코어를 다 탐색 후 
				
				if (maxCount < count) {
					maxCount = count;
					result = sum;
				} else if (maxCount == count) 
					result = Math.min(result, sum);
				
				sum = 0;
				count = 0;
			}
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
}