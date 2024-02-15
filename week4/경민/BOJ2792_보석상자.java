import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M;
	private static int[] arr;
	private static int answer = Integer.MAX_VALUE;

	// 학생이 보석을 num개 씩 가진다고 가정할 떄, 몇 명에게 나눠줄 수 있는지 리턴
	// N 보다 많으면 lt = mid + 1 => answer 구할 필요 없음
	// N 보다 적으면 rt = mid - 1 => num - 0 이 질투심
	// N 과 같으면 num - min (rest의 최소값) 이 질투심

	public static int solution(int num) {
		int index = 0, count = 0, rest = 0, min = 0;
		while (index < M) {
			count += arr[index] / num;
			rest = arr[index] % num;

			// 보석이 남는 경우
			if (rest != 0) {
				count++;
				min = Math.min(rest, min); // 남는 보석 중 가장 작은 보석
			}

			index++;
		}

		if (count < N) {
			answer = Math.min(answer, num - 0);
		} else if (count == N) {
			answer = Math.min(answer, num - min);
		}

		return count;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		int max = 0;
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (arr[i] > max)
				max = arr[i];
		}

		answer = max;
		int lt = 1;
		int rt = max;

		while (lt <= rt) {
			int mid = (lt + rt) / 2;

			if (solution(mid) > N) {
				lt = mid + 1;
			} else {
				rt = mid - 1;
			}
		}
		System.out.println(answer);

	}
}