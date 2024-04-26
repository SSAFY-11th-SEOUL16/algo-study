import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * java
 * 488 ms
 * 
 * 투 포인터
 * 
 * 선물을 가격에 따라 정렬한 후, 투 포인터를 사용하여 최대 기쁨의 합을 구함
 * 왼쪽과 오른쪽 포인터를 조절하여 가격 차이가 D 이상 나는 경우 왼쪽 포인터를 이동시켜 기쁨의 합을 갱신함
 */

public class BOJ12892_생일선물 {
	static int N, D;
	static Gift[] gift;

	static class Gift implements Comparable<Gift> {
		int P, V;

		public Gift(int p, int v) {
			P = p;
			V = v;
		}

		@Override
		public int compareTo(Gift o) {
			return Integer.compare(this.P, o.P); // 생일 선물을 가격 순서로 정렬
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		gift = new Gift[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			gift[i] = new Gift(p, v);
		}
		Arrays.sort(gift);

		int left = 0; // 투 포인터의 왼쪽 포인터
		long maxVal = 0; // 최대 만족도 합
		long currentVal = 0; // 현재 만족도 합
		for (int right = 0; right < N; right++) { // 오른쪽 포인터를 이동시키며 탐색
			currentVal += gift[right].V; // 오른쪽 선물의 만족도를 현재 만족도에 추가
			while (gift[right].P - gift[left].P >= D) { // 가격 차이가 D 이상 나는 지 확인
				currentVal -= gift[left].V; // 왼쪽 선물의 만족도를 현재 만족도에서 제거
				left++; // 왼쪽 포인터를 이동
			}
			maxVal = Math.max(maxVal, currentVal); // 최대 만족도 업데이트
		}
		System.out.println(maxVal);
	}
}