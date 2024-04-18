import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1253_좋다 {
	
	/**
	 * 124 ms
	 * 투 포인터
	 * 입력된 수 정렬
	 * 각각의 num이 좋은 수인지 판단
	 * left = 0, right = 마지막 인덱스
	 * 합이 num 미만 -> left 1 증가
	 * 합이 num 초과 -> right 1 감소
	 * 다른 수들로 합이 num -> 좋은 수
	 */
	
	private static int n;
	private static int[] arr;
	
	private static final boolean isGood(int idx, int num) {
		int left, right;
		
		left = 0;
		right = n;
		while (left < right) {
			if (arr[left] + arr[right] < num) { // 합이 num 미만
				left++; // left 1 증가
			} else if (arr[left] + arr[right] > num) { // 합이 num 초과
				right--; // right 1 감소
			} else { // 합이 num
				if (left == idx) { // num 본인이 포함된 경우 무시
					left++;
				} else if (right == idx) {
					right--;
				} else { // 그 외 합이 num
					return true; // 좋은 수
				}
			}
		}
		return false;
	}
	
	private static final int count() {
		int i, cnt;
		
		cnt = 0;
		for (i = 0; i <= n; i++) {
			if (isGood(i, arr[i])) { // 좋은 수
				cnt++; // 카운트
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n--];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); // 숫자 정렬
		System.out.print(count()); // 좋은 수 개수 출력
	}
}
