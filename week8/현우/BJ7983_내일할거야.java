import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ7983_내일할거야 {
	
	/**
	 * 1328 ms
	 * 마감일 기준 내림차순 정렬
	 * 가장 늦은 과제부터 시작일 계산
	 * 현재 과제 마감일과 뒷 과제 시작일 비교
	 * 더 작은 쪽을 마감일로 시작일 계산
	 * 마지막 시작일 출력
	 */
	
	private static final int INF = Integer.MAX_VALUE;
	
	private static class Task implements Comparable<Task> { // 과제
		int d, t;
		
		Task(int d, int t) {
			this.d = d; // 걸리는 일 수
			this.t = t; // 마감일
		}

		@Override
		public int compareTo(Task o) { // 마감일 비교 (내림차순)
			return Integer.compare(o.t, this.t);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, end, i;
		ArrayList<Task> tasks;
		
		n = Integer.parseInt(br.readLine());
		tasks = new ArrayList<>();
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			tasks.add(new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(tasks); // 마감일 기준 내림차순 정렬
		end = INF;
		for (i = 0; i < n; i++) { // 가장 늦은 과제부터 시작일 계산
			end = Math.min(end, tasks.get(i).t) - tasks.get(i).d;
		} // 현재 과제 마감일과 뒷 과제 시작일 비교
		System.out.print(end); // 마지막 시작일 출력
	}
}
