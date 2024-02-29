import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	/*
	 * 어떤 사람이 몇 번 계단(0번 or 1번) 갈지 결정 -> 이동 -> 계단 내려가기 -> 최소시간 구하기
	 */
	static int result = Integer.MAX_VALUE, n;
	static int[][] moveT; // 각각의 계단까지 이동 시간 저장
	static List<Stair> stairs = new ArrayList<>();
	static List<Person> people = new ArrayList<>();
	static LinkedList<Person> cur = new LinkedList<>(); // 현재 계단에 있는 person 리스트 (리스트에서 0~2까지만 계단 내려가도록 구현)

	static class Person {
		int x;
		int y;
		int stair; // 몇 번 계단으로 갈지
		int floor; // 현재 층 수

		public Person(int x, int y, int stair, int floor) {
			this.x = x;
			this.y = y;
			this.stair = stair; // 계단 번호
			this.floor = floor; // 계단 층 수
		}
	}

	static class Stair {
		int x;
		int y;
		int floors; // 해당 계단의 층 수

		public Stair(int x, int y, int floors) {
			this.x = x;
			this.y = y;
			this.floors = floors;
		}
	}

	// stairNum계단에 total 명의 사람들이 전부 내려갔을 때의 시간 return
	// start는 사람이 계단에 처음으로 사람이 도착한 시간으로 1분 대기한 후 내려가야하므로 start+1분 부터 1분씩 추가하며 체크
	public static int cal_time(int start, int stairNum, int total) {
		int time = start + 1;
		int cnt = 0; // 계단을 완전히 내려간 사람 count
		cur.clear();

		while (true) {
			for (int i = 0; i < people.size(); i++) {
				if (people.get(i).stair != stairNum)
					continue;

				// 계단 입구 도착한 후 1분 후(time-1) 계단 큐에 추가
				if (moveT[stairNum][i] == time - 1)
					cur.add(people.get(i));
			}

			// 계단 내려가기. 단 앞의 3명만 !!
			if (!cur.isEmpty())
				for (int j = 0; j < 3 && j < cur.size(); j++)
					cur.get(j).floor++;

			// 계단을 완전히 다 내려간 경우, 계단 큐에서 제거 및 인덱스 i를 다시 앞으로 땡기기
			// 큐 제거 후 인덱스 다시 앞으로 안땡겼더니 무한으로 계단 내려갔다왔습니다 ; ㅠㅠ
			for (int i = 0; i < cur.size(); i++) {
				if (cur.get(i).floor == stairs.get(stairNum).floors) {
					cur.removeFirst();
					cnt++;
					i--;
				} else
					break;
			}

			// 시간 추가
			time++;

			// 모든 계단을 다 내려간 경우, 종료
			if (cur.isEmpty() && cnt == total)
				return time;

			// 구해놓은 최소 시간보다 더 크면 의미가 없으므로 그냥 종료
			if (time > result)
				return time;
		}
	}

	// 각각의 계단에 가장 빨리 도착하는 시간 구한 후, cal_time() 함수 호출
	public static int down_stairs(int total0, int total1) {
		int time = 0;
		int min0 = Integer.MAX_VALUE;
		int min1 = Integer.MAX_VALUE;

		for (int i = 0; i < people.size(); i++)
			if (people.get(i).stair == 0)
				min0 = Math.min(min0, moveT[0][i]);
			else
				min1 = Math.min(min1, moveT[1][i]);

		if (min0 == Integer.MAX_VALUE)
			return cal_time(min1, 1, total1);
		else if (min1 == Integer.MAX_VALUE)
			return cal_time(min0, 0, total0);
		else
			return Math.max(cal_time(min0, 0, total0), cal_time(min1, 1, total1));
	}

	public static void combi(int index, int total0, int total1) {
		if (index == people.size()) {
			// 모든 사람이 계단을 내려가 이동 완료하는 시간 구하기
			int down = down_stairs(total0, total1);

			// 사람들의 현재 층 수 다시 초기화
			for (int i = 0; i < people.size(); i++)
				people.get(i).floor = 0;

			result = Math.min(down, result);

			return;
		}

		// index 번 사람이 0번 계단 선택
		people.get(index).stair = 0;
		combi(index + 1, total0 + 1, total1);
		// index 번 사람이 1번 계단 선택
		people.get(index).stair = 1;
		combi(index + 1, total0, total1 + 1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if (tmp == 1)
						people.add(new Person(i, j, 0, 0));
					else if (tmp >= 2 && tmp <= 10)
						stairs.add(new Stair(i, j, tmp));
				}
			}

			// 사람마다 계단입구까지 이동시간 구하기
			moveT = new int[2][people.size()];

			for (int i = 0; i < 2; i++)
				for (int j = 0; j < people.size(); j++)
					// j번 사람이 i번째 계단으로 가는 이동시간
					moveT[i][j] = Math.abs(stairs.get(i).x - people.get(j).x)
							+ Math.abs(stairs.get(i).y - people.get(j).y);

			combi(0, 0, 0);

			sb.append('#').append(t).append(' ').append(result).append('\n');
			people.clear();
			stairs.clear();
			result = Integer.MAX_VALUE;
		}
		System.out.println(sb);
	}
}