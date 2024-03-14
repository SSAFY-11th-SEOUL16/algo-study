import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1332 ms 
 * 최대 며칠 동안 놀 수 있는지 찾기
 *
 * 정렬 
 * 그리디 알고리즘
 * 
 * Deadline을 기준으로 내림차순 정렬 
 * 가장 마지막에 끝내야 할 과제부터 시작해서 각 과제를 수행할 때 필요한 시간을 역순으로 계산
 */

public class BOJ7983_내일할거야 {
	static class Task implements Comparable<Task> {
		int time;
		int deadline;

		public Task(int time, int deadline) {
			this.time = time;
			this.deadline = deadline;
		}

		@Override
		public int compareTo(Task o) { // Deadline을 기준으로 내림차순 정렬
			return Integer.compare(o.deadline, this.deadline);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;

		List<Task> taskList = new ArrayList<>();

		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int deadline = Integer.parseInt(st.nextToken());
			taskList.add(new Task(time, deadline));
		}

		Collections.sort(taskList);

		int lastEndPoint = taskList.get(0).deadline; // 가장 마지막 날 저장
		for (int i = 0; i < taskList.size(); ++i) {
			if (taskList.get(i).deadline <= lastEndPoint) { // 과제 마감일이 lastEndPoint보다 작거나 같으면
				lastEndPoint = taskList.get(i).deadline - taskList.get(i).time;
			} else { // 과제 마감일이 lastEndPoint보다 크다면
				lastEndPoint -= taskList.get(i).time;
			}
		}
		System.out.print(lastEndPoint); // 연속적으로 아무 것도 하지 않고 놀 수 있는 마지막 날짜인 lastEndPoint 출력
	}
}