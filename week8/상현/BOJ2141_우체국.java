package week8.상현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2141_우체국 {
	/**
	 * 그리디
	 * 사람을 기준으로 우체국을 설치하기 때문에 일직선 상의 마을을 반으로 쪼갠다고 할 경우 양쪽의 인구수가 같아지는 지점이 최소가 될 수 있음
	 * 1. 입력을 받으면서 전체 인구수 값을 구함
	 * 2. 마을 순으로 정렬 후 각 마을의 인구수를 더했을 때 전체인구수 + 1 / 2 한 값을 넘거나 같아지는 지점이 중간 지점이므로 해당 마을을 출력
	 */
	private static class Home implements Comparable<Home>{
		long p;
		long k;
		public Home(long p, long k) {
			super();
			this.p = p;
			this.k = k;
		}
		@Override
		public int compareTo(Home o) {
			// TODO Auto-generated method stub
			return Long.compare(this.p, o.p);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		
		Home[] homes = new Home[N];
		
		long total = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			homes[i] = new Home(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			
			total += homes[i].k;
		}
		

		Arrays.sort(homes);
		
		total = (total + 1)/2;
		long tmp = 0;
		for (int i = 0; i < N; i++) {
			tmp += homes[i].k;
			if (tmp >= total) {
				System.out.println(homes[i].p);
				break;
			}
		}
		
		
	}
}
