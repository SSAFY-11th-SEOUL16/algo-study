import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA2382_미생물격리 {

	/*
	 * 풀이 참고함...
	 * 시뮬레이션 문제 자체를 처음 풀었습니다ㅜ
	 * 이번에는 이런식으로 시뮬레이션을 구현하는구나 맛보는 느낌으로 풀었습니다
	 * */
	private static int[][] move = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			//입력
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			List<Microbe> micros = new ArrayList<>();
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				Microbe micro = new Microbe(x, y, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				micros.add(micro);
				
			}
			
			List<Microbe> result = simulation(map, micros, M, N);
			
			int sum = 0;
			
			for (Microbe microbe : result) {
				sum += microbe.fungus;
			}
			
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}

		System.out.println(sb);

	}

	private static List<Microbe> simulation(int[][] map, List<Microbe> micros,int M, int N) {
		for (int day = 0; day < M; day++) {
			//살아남은 군집만 넣을거야
			List<Microbe> tmpList = new ArrayList<>();
			//map에 표시할 list의 idx 군집 번호
			int idx = 0;
			//-1로 채움
			map = new int[N][N];
			for (int j = 0; j < N; j++) {
				Arrays.fill(map[j], -1);
			}
			
			Collections.sort(micros, (o1, o2) -> {
				return o2.fungus - o1.fungus;
			});

			for (int j = 0; j < micros.size(); j++) {
				Microbe nowMicro = micros.get(j);
				
				int nx = nowMicro.x + move[nowMicro.direction - 1][0];
				int ny = nowMicro.y + move[nowMicro.direction - 1][1];
				
				nowMicro.x = nx;
				nowMicro.y = ny;
				//약품 질해진 셀 도착 시 방향 전환
				if (nx == 0 || nx == N || ny == 0 || ny == N) {
					nowMicro.fungus /= 2;
					nowMicro.direction = nowMicro.direction % 2 == 0 ? nowMicro.direction - 1 : nowMicro.direction + 1;				
				}
				
				if(map[nx][ny] == -1) {
					tmpList.add(nowMicro);
					map[nx][ny] = idx++;	
				} else {
					tmpList.get(map[nx][ny]).fungus += nowMicro.fungus;
				}
				
			}
			
			micros.clear();
			micros.addAll(tmpList);
		}
		
		return micros;
	}
	
	private static class Microbe {
		int x;
		int y;
		int fungus;
		int direction;
		
		public Microbe(int x, int y, int fungus, int direction) {
			this.x = x;
			this.y = y;
			this.fungus = fungus;
			this.direction = direction;
		}
		
	}

}
