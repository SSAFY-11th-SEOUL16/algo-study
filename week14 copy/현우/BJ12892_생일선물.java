import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 472 ms
 * 투 포인터
 * 선물 가격 순으로 오름차순 정렬
 * 투 포인터 (0, 0)에서 출발
 * 양 끝 가격 차이가
 * D 이상이면 left 증가
 * D 미만이면 right 증가
 * */
public class BJ12892_생일선물 {
	private static final class Present implements Comparable<Present> {
		long p; // 가격
		long v; // 만족도
		
		Present(long p, long v) {
			this.p = p;
			this.v = v;
		}
		
		@Override
		public int compareTo(Present o) { // 가격 오름차순
			return Long.compare(this.p, o.p);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n;
		int d;
		int i;
		int left;
		int right;
		long sum;
		long max;
		Present[] presents;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		presents = new Present[n];
		for (i = 0; i < n; i++) { // 선물 입력
			st = new StringTokenizer(br.readLine());
			presents[i] = new Present(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(presents); // 가격순 정렬
		sum = presents[0].v; // 만족도 합
		max = sum; // 최대 합
		for (left = 0, right = 0;; sum -= presents[left++].v) { // (0, 0)에서 출발, left 증가하면서 탐색
			while (presents[right].p - presents[left].p < d) { // 가격차 D 미만인 동안
				max = Math.max(max, sum); // 최대 합 갱신
				if (++right == n) { // right 증가
					System.out.print(max); // N 도달시 종료
					return;
				}
				sum += presents[right].v; // right 선물 만족도 합에 추가
			}
		}
	}
}
