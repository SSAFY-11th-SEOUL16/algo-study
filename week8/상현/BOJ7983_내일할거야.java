package week8.상현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 그리디
 *
 */
public class BOJ7983_내일할거야 {
	private static class HomeWork implements Comparable<HomeWork>{
		int d;
		int t;
		public HomeWork(int d, int t) {
			super();
			this.d = d;
			this.t = t;
		}
		@Override
		public int compareTo(HomeWork o) {
			// TODO Auto-generated method stub
			return o.t - this.t;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		
		PriorityQueue<HomeWork> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			pq.add(new HomeWork(d, t));
		}

		int startD = pq.peek().t;
		
		while(!pq.isEmpty()) {
			HomeWork h = pq.poll();

			//마감일이 현재 시작해야되는 날짜보다 작거나 같을 경우 해당 숙제 마감일 - 걸리는 기간으로 시작일을 바꿔줌
			//그렇지 않다면 현재 시작일에서 해당 숙제의 기간만큼 빼줌
			if (h.t <= startD) {
				startD = h.t - h.d;
			} else {
				startD -= h.d;
			}
		}
		
		System.out.println(startD);
	}
}