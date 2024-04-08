import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * JAVA
 * 268 ms
 * 
 * 투 포인터
 * 
 * 시작점(left)과 끝점(right)을 가리키는 투 포인터를 설정
 * 
 * 현재 포인터가 가르키는 두 용액의 특성합을 구함
 *   - 이전 값보다 0에 더 가까운 경우 갱신
 *   - 0보다 크거나 같으면 right를  왼쪽으로 이동
 *   - 0보다 작으면 left를 오른쪽으로 이동
 */

public class BJ2467_용액 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int min = Integer.MAX_VALUE;
		int left = 0, right = N - 1, mr = 0, ml = 0;

		while (left < right) { // 두 포인터가 서로 교차하지 않는 범위까지 탐색
			int sum = arr[left] + arr[right]; // left와 right가 가리키는 두 용액의 합을 계산
			if (min >= Math.abs(sum)) {
				min = Math.abs(sum);
				ml = left;
				mr = right;
			}

			if (sum >= 0) {
				right--;
			} else {
				left++;
			}
		}
		System.out.println(arr[ml] + " " + arr[mr]);
	}
}