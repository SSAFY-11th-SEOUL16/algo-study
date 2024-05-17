import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 76 ms
 * 그리디
 * 최대값과 최소값의 차이가 V 이상이 될 때까지 입력
 * 차이가 V 이상인 경우가 없으면 다 풀기
 * 마지막 인덱스와 짝수개 차이가 나면서
 * 값의 차이가 V 이상인 곳이 존재하는지 확인
 * 최소 문제 수 출력
 * */
public class BJ1332_풀자 {
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		int i;
		int n;
		int v;
		int num;
		int min;
		int max;
		int left;
		int right;
		int minIdx;
		int maxIdx;
		int[] arr;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		min = INF;
		max = -1;
		minIdx = 0;
		maxIdx = 0;
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (min > arr[i]) {
				min = arr[i]; // 최소값
				minIdx = i;
			}
			if (max < arr[i]) {
				max = arr[i]; // 최대값
				maxIdx = i;
			}
			if (max - min >= v) { // 차이가 V 이상이면 멈춤
				break;
			}
		}
		if (i == n) { // 차이가 V 이상인 경우가 없으면 다 풀기
			System.out.print(n);
			return;
		}
		if (minIdx < maxIdx) { // 마지막이 최대값
			if (((maxIdx - minIdx) & 1) == 1) { // 최대값 인덱스와 최소값 인덱스 차이가 홀수
				num = arr[maxIdx];
				for (i = maxIdx - 2; i >= 0; i -= 2) { // 마지막 인덱스와 짝수개 차이가 나면서
					if (num - arr[i] >= v) { // 값의 차이가 V 이상인 곳이 존재
						minIdx = i; // 최소값 인덱스 변경
						break;
					}
				}
			}
			left = minIdx; // 꼭 풀어야 하는 문제 인덱스
			right = maxIdx;
		} else { // 마지막이 최소값
			if (((minIdx - maxIdx) & 1) == 1) { // 최대값 인덱스와 최소값 인덱스 차이가 홀수
				num = arr[minIdx];
				for (i = minIdx - 2; i >= 0; i -= 2) { // 마지막 인덱스와 짝수개 차이가 나면서
					if (arr[i] - num >= v) { // 값의 차이가 V 이상인 곳이 존재
						maxIdx = i; // 최대값 인덱스 변경
						break;
					}
				}
			}
			left = maxIdx; // 꼭 풀어야 하는 문제 인덱스
			right = minIdx;
		}
		System.out.print(((left + 1 >> 1) + 1) + (right - left + 1 >> 1)); // 최소 문제 수 출력
	}
}
