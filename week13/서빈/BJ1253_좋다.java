import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
- 156 ms

- 투 포인터

- 입력된 수를 정렬한 후 투 포인터를 사용하여 좋은 수를 찾음
- 각 수에 대해 합을 찾는 과정에서 중복 탐색을 방지하기 위해 왼쪽과 오른쪽 포인터의 위치를 현재 수와 비교하여 조절

- 현재 인덱스와 같으면 각 포인터를 이동
- 두 포인터가 만나면 탐색 종료
- 현재 수가 합보다 큰 경우 왼쪽 포인터 이동
- 현재 수가 합보다 작은 경우 오른쪽 포인터 이동
- 같은 경우 좋은 수 ++
 */

public class BJ1253_좋다 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers); // 배열을 정렬

		int result = 0;
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N - 1;
			while (true) {
				if (left == i) { // 왼쪽 포인터가 현재 인덱스와 같으면 오른쪽으로 이동
					left++;
				} else if (right == i) { // 오른쪽 포인터가 현재 인덱스와 같으면 왼쪽으로 이동
					right--;
				}
				if (left >= right) { // 포인터가 만나면 탐색 종료
					break;
				}
				if (numbers[i] == numbers[left] + numbers[right]) { // 좋은 수인 경우 결과 증가
					result++;
					break;
				} else if (numbers[i] > numbers[left] + numbers[right]) { // 현재 수가 합보다 큰 경우 왼쪽 포인터 이동
					left++;
				} else if (numbers[i] < numbers[left] + numbers[right]) { // 현재 수가 합보다 작은 경우 오른쪽 포인터 이동
					right--;
				}
			}
		}
		System.out.println(result);
	}
}