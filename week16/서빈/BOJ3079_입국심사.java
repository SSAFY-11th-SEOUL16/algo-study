import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * java
 * 268 ms
 * 
 * 이분 탐색
 * 
 * - 이분 탐색을 이용해 최소 시간을 계산함
 * - 각 심사대의 최소 시간과 최대 시간을 구한 후, 그 범위 안에서 이분 탐색을 수행
 * - 중간 값으로 가능한 모든 사람을 처리할 수 있는지 확인
 */

public class BOJ3079_입국심사 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long N = Long.parseLong(st.nextToken());// 심사대 수
		long M = Long.parseLong(st.nextToken());// 상근이와 친구들의 수

		long[] time = new long[(int) N]; // 각 심사대에서 걸리는 시간을 저장할 배열
		long max = 0;
		long min = Long.MAX_VALUE;

		for (int i = 0; i < N; i++) {// 각 심사대의 심사 시간 입력
			time[i] = Long.parseLong(br.readLine());// 심사 시간을 배열에 저장
			max = Math.max(time[i], max);// 최대 심사 시간 갱신
			min = Math.min(min, time[i]);// 최소 심사 시간 갱신
		}

		long left = min; // 이분 탐색의 하한
		long right = max * M; // 이분 탐색의 상한

		while (left <= right) {
			long mid = (left + right) / 2;// 중간값 계산
			long cnt = 0;// 중간값에서 처리할 수 있는 최대 인원 수 계산
			for (int i = 0; i < N; i++) {
				cnt += mid / time[i]; // 각 심사대에서 중간값 시간 동안 처리할 수 있는 인원 수 더하기
				if (cnt > M) // 이미 M명 이상 처리 가능한 경우 반복 종료 => 이거 없어서 틀렸습니다 나왔음
					break;
			}

			if (cnt < M) {// 처리 인원 수가 목표보다 적으면
				left = mid + 1;// 하한값을 올림
			} else {// 처리 인원 수가 목표보다 많거나 같으면
				right = mid - 1;// 상한값을 내림
			}
		}
		System.out.println(left);
	}
}