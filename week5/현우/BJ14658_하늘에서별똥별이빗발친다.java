import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ14658_하늘에서별똥별이빗발친다 {
	
	/*
	 * 108 ms
	 * 두 별이 트램펄린 안에 같이 들어올 경우
	 * 두 별을 모서리에 오게 하는 시작 지점 생성
	 * 별이 있는 위치, 혹은 새로 저장된 지점을 시작으로 트램펄린 배치
	 * 내부에 들어오는 별 개수 탐색
	 * K - 별 개수 최대
	 */
	
	private static int l, k;
	private static int[][] stars;
	
	private static boolean trampoline(int[] star1, int[] star2) { // 두 별이 트램펄린 안에 같이 들어오는지 반환
		return Math.abs(star1[0] - star2[0]) <= l && Math.abs(star1[1] - star2[1]) <= l;
	}
	
	private static int count(int[] start) { // 배치된 트램펄린 안의 별 개수 카운트
		int cnt, i;
		
		cnt = 0;
		for (i = 0; i < k; i++) {
			if (stars[i][0] >= start[0] && stars[i][1] >= start[1] && trampoline(start, stars[i])) {
				cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int x, y, max, i, j;
		LinkedList<int[]> starts;
		
		st = new StringTokenizer(br.readLine());
		Integer.parseInt(st.nextToken());
		Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		stars = new int[k][2];
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Integer.parseInt(st.nextToken());
			stars[i][1] = Integer.parseInt(st.nextToken());
		}
		starts = new LinkedList<>();
		for (i = 0; i < k; i++) {
			for (j = i + 1; j < k; j++) {
				if (trampoline(stars[i], stars[j])) { // 두 별이 한 트램펄린 안에 올 수 있는 경우
					x = Math.min(stars[i][0], stars[j][0]); // 두 별을 모서리에 오게 하는 새로운 지점 생성
					y = Math.min(stars[i][1], stars[j][1]);
					if (!((x == stars[i][0] && y == stars[i][0]) || (x == stars[j][1] && y == stars[j][1]))) {
						starts.add(new int[] {x, y}); // 새로운 지점 저장
					}
				}
			}
		}
		max = 0;
		for (i = 0; i < k; i++) { // 별 위치를 시작점으로 트램펄린 배치
			max = Math.max(max, count(stars[i]));
		}
		for (int[] start : starts) { // 새로 만든 지점을 시작점으로 트램펄린 배치
			max = Math.max(max, count(start));
		}
		System.out.print(k - max);
	}
}
