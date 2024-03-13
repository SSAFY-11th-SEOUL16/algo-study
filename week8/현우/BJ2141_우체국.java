import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2141_우체국 {
	
	/**
	 * 412 ms
	 * 좌표 기준으로 정렬
	 * 우체국 오른쪽으로 한 칸 이동 시
	 * 좌측 사람 수만큼 거리 증가
	 * 우측 사람 수만큼 거리 감소
	 * 좌측, 우측 사람 수 차이가 가장 적은 마을에 배치
	 * = 좌측부터 누적합이 전체 사람의 절반이 넘어가는 곳에 배치
	 */
	
	private static class Town implements Comparable<Town> { // 마을
		int x, a;
		
		Town(int x, int a) {
			this.x = x; // 좌표
			this.a = a; // 사람 수
		}

		@Override
		public int compareTo(Town o) { // 좌표 비교
			return Integer.compare(this.x, o.x);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, x, a, i;
		long sum, prefix;
		Town town;
		PriorityQueue<Town> pq;
		
		n = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		sum = 0;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()); // 좌표
			a = Integer.parseInt(st.nextToken()); // 사람 수
			pq.add(new Town(x, a)); // PQ로 좌표 기준 정렬
			sum += a;
		}
		town = null;
		sum = (sum + 1) / 2; // 절반 초과
		prefix = 0;
		while (prefix < sum) { // 누적합이 절반 이하인 동안
			town = pq.poll();
			prefix += town.a; // 사람 수 누적합
		}
		System.out.print(town.x); // 사람 수 누적합이 절반을 초과하는 좌표에 배치
	}
}
