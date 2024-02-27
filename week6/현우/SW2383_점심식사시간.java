import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW2383_점심식사시간 {
	
	/**
	 * 119 ms
	 * bit 증가시키면서 부분집합 탐색
	 * & 1 == 0: 계단 0으로 이동
	 * & 1 == 1: 계단 1로 이동
	 * 시간 증가시키면서 모두 계단을 내려올 때까지 진행
	 * pq0, pq1에 거리 + 1 삽입
	 * 현재 시각에 계단을 다 내려온 사람들
	 * q0, q1에서 poll
	 * pq에서 현재 시각 이전에 도착한 사람들
	 * q size 3까지 q0, q1에 삽입
	 */
	
	private static final int INF = Integer.MAX_VALUE;
	private static final int MAX_PEOPLE = 10;
	private static final int MAX_STAIRS = 2;
	private static final int FULL = 3;
	
	private static int peopleSize, stairsSize;
	private static int[] stairLen;
	private static int[][] people, stairs, dist;
	private static Queue<Integer> q0, q1;
	private static PriorityQueue<Integer> pq0, pq1;
	
	private static int calc(int bit) {
		int time, cnt, i;
		
		for (i = 0; i < peopleSize; i++, bit >>= 1) { // 비트 확인
			if ((bit & 1) == 0) { // 계단 0으로 이동
				pq0.add(dist[i][0] + 1); // 도착 시간 = 거리 + 1
			} else { // 계단 1로 이동
				pq1.add(dist[i][1] + 1); // 도착 시간 = 거리 + 1
			}
		}
		for (time = 0, cnt = 0; cnt < peopleSize; time++) { // 시간 증가시키면서 진행
			while (!q0.isEmpty() && q0.peek() == time) { // 계단 0을 다 내려온 사람들
				q0.poll(); // 계단에서 꺼냄
				cnt++; // 다 내려온 사람 카운트
			}
			while (!q1.isEmpty() && q1.peek() == time) { // 계단 1을 다 내려온 사람들
				q1.poll(); // 계단에서 꺼냄
				cnt++; // 다 내려온 사람 카운트
			}
			while (!pq0.isEmpty() && pq0.peek() <= time && q0.size() < FULL) { // 계단 0에 도착해 있는 사람들
				pq0.poll(); // 계단에 3 명이 찰 때까지 대기 줄에서 꺼냄
				q0.add(time + stairLen[0]); // 계단 0을 다 내려가는 시각 삽입
			}
			while (!pq1.isEmpty() && pq1.peek() <= time && q1.size() < FULL) { // 계단 1에 도착해 있는 사람들
				pq1.poll(); // 계단에 3 명이 찰 때까지 대기 줄에서 꺼냄
				q1.add(time + stairLen[1]); // 계단 1을 다 내려가는 시각 삽입
			}
		}
		return time - 1; // 마지막 시각 반환
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, max, num, bit, ans, i, j;
		
		n = Integer.parseInt(br.readLine());
		peopleSize = 0;
		stairsSize = 0;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				num = Integer.parseInt(st.nextToken());
				if (num != 0) {
					if (num == 1) { // 사람 위치 입력
						people[peopleSize][0] = i;
						people[peopleSize++][1] = j;
					} else { // 계단 위치 입력
						stairs[stairsSize][0] = i;
						stairs[stairsSize][1] = j;
						stairLen[stairsSize++] = num; // 계단 길이
					}
				}
			}
		}
		for (i = 0; i < peopleSize; i++) { // 사람과 계단 사이 거리 계산
			for (j = 0; j < stairsSize; j++) {
				dist[i][j] = Math.abs(people[i][0] - stairs[j][0]) + Math.abs(people[i][1] - stairs[j][1]);
			}
		}
		ans = INF;
		max = 1 << peopleSize;
		for (bit = 0; bit < max; bit++) { // 비트 증가시키면서 부분집합 탐색
			ans = Math.min(ans, calc(bit)); // 최소 시간 계산
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		people = new int[MAX_PEOPLE][2];
		stairs = new int[MAX_STAIRS][2];
		stairLen = new int[MAX_STAIRS];
		dist = new int[MAX_PEOPLE][MAX_STAIRS];
		q0 = new ArrayDeque<>();
		q1 = new ArrayDeque<>();
		pq0 = new PriorityQueue<>();
		pq1 = new PriorityQueue<>();
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
