import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3190_뱀 {
	
	/**
	 * 76 ms
	 * 시뮬레이션
	 * 1 차원 맵 사용
	 * 가장자리에 벽 두르기
	 * 뱀의 몸체도 벽으로 취급
	 * 다음 방향 전환 정보 저장
	 * 방향 전환 시간 도달 시 전환 수행
	 * 벽에 부딪히면 게임 종료
	 */
	
	private static final int INF = Integer.MAX_VALUE;
	private static final int WALL = -1;
	private static final int APPLE = 1;
	private static final int EMPTY = 0;
	private static final int MAX = 10000;
	private static final char LEFT = 'L';
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, k, x, l, size, max, pos, next, dir, time, diff, cnt, head, tail, i;
		int[] map, snake, d;
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		size = n + 2;
		max = size * size;
		d = new int[] {-size, 1, size, -1};
		map = new int[max]; // 1 차원 맵
		for (i = 1; i <= n; i++) { // 가장자리에 벽 두르기
			map[i] = WALL;
			map[(n + 1) * size + i] = WALL;
			map[i * size] = WALL;
			map[i * size + n + 1] = WALL;
		}
		for (i = 0; i < k; i++) { // 사과 입력
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) * size + Integer.parseInt(st.nextToken())] = APPLE;
		}
		dir = 1; // 오른쪽 방향
		l = Integer.parseInt(br.readLine()); // 방향 전환 횟수
		cnt = 1; // 방향 전환 카운트
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken()); // 다음 방향 전환 시간
		if (st.nextToken().charAt(0) == LEFT) {
			diff = 3; // 좌회전
		} else {
			diff = 1; // 우회전
		}
		snake = new int[MAX]; // 뱀
		head = 0; // 머리 인덱스
		tail = 0; // 꼬리 인덱스
		pos = 1 * size + 1; // 처음 위치 (1, 1)
		snake[head] = pos;
		for (time = 1;; time++, pos = next) { // 게임 시작
			next = pos + d[dir]; // 다음 위치
			switch (map[next]) {
			case WALL: // 벽에 부딪히면
				System.out.print(time);
				return; // 게임 종료
			case EMPTY: // 빈 공간
				map[snake[tail++]] = EMPTY; // 꼬리 위치 빈칸 처리, 꼬리 인덱스 1 증가
			case APPLE:
				map[next] = WALL; // 뱀의 몸체도 벽으로 취급
				snake[++head] = next; // 다음 위치 머리로 설정, 머리 인덱스 1 증가
			}
			if (time == x) { // 방향 전환 시간 도달
				dir = (dir + diff) % 4; // 저장된 좌회전 혹은 우회전 수행
				if (cnt++ == l) { // 마지막 방향 전환
					x = INF; // 다음 방향 전환 시간은 INF로 설정
				} else {
					st = new StringTokenizer(br.readLine());
					x = Integer.parseInt(st.nextToken()); // 다음 방향 전환 시간
					if (st.nextToken().charAt(0) == LEFT) {
						diff = 3; // 좌회전
					} else {
						diff = 1; // 우회전
					}
				}
			}
		}
	}
}
