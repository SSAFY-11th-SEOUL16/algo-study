import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2467_용액 {
	
	/**
	 * 260 ms
	 * 투 포인터
	 * 양 끝부터 탐색하면서
	 * 합이 음수면 left 증가
	 * 합이 양수면 right 감소
	 * 0에 가장 가까운 left right 찾기
	 */
	
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, left, right, sum, min, numL, numR, i;
		int[] arr;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		numL = 0;
		numR = 0;
		left = 0; // 양 끝으로 left right 설정
		right = n - 1;
		min = INF; // 용액 합 최소
		while (left < right) {
			if (arr[left] >= 0) { // left가 0 혹은 양수에 도달하면 탐색 중지
				if (Math.abs(arr[left] + arr[left + 1]) < min) { // 최소값 계산
					numL = arr[left];
					numR = arr[left + 1];
				}
				break;
			} else if (arr[right] <= 0) { // right가 0 혹은 음수에 도달하면 탐색 중지
				if (Math.abs(arr[right - 1] + arr[right]) < min) { // 최소값 계산
					numL = arr[right - 1];
					numR = arr[right];
				}
				break;
			}
			sum = arr[left] + arr[right]; // 두 용액 합
			if (Math.abs(sum) < min) { // 최소값 계산
				min = Math.abs(sum);
				numL = arr[left];
				numR = arr[right];
                if (min == 0) { // 합이 0이면 탐색 중지
                    break;
                }
			}
			if (sum < 0) { // 합이 음수면
				left++; // left 증가
			} else { // 합이 양수면
				right--; // right 감소
			}
		}
		sb.append(numL).append(' ').append(numR);
		System.out.print(sb);
	}
}
