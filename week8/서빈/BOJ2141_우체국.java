import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 496 ms 
 * 전체 인구의 절반 이상이 되는 지점 찾기
 * 
 * 정렬 
 * 그리디 알고리즘
 * 
 * 위치를 오름차순으로 정렬한 후, 
 * 인구수를 누적하면서 전체 인구의 절반 이상이 거주하는 지점을 찾기 
 * 
 * 가능한 경우가 여러 가지인 경우에는 더 작은 위치를 출력 
 * ==> 정렬된 리스트에서 처음으로 전체 인구의 절반 이상이 되는 지점을 찾으면 됨!
 */

public class BOJ2141_우체국 {
	static class House implements Comparable<House> {
		long position;
		long people;

		public House(long position, long people) {
			this.position = position;
			this.people = people;
		}

		@Override
		public int compareTo(House o) { // Position을 기준으로 오름차순 정렬
			return Long.compare(this.position, o.position);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		List<House> houseList = new ArrayList<>();
		long sum = 0; // 전체 인구 수를 계산

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long position = Long.parseLong(st.nextToken());
			long people = Long.parseLong(st.nextToken());
			houseList.add(new House(position, people));
			sum += people;
		}

		Collections.sort(houseList);
		long result = 0;

		for (int i = 0; i < N; i++) {
			result += houseList.get(i).people; // 인구수를 누적
			if ((sum + 1) / 2 <= result) { // 전체 인구의 절반 이상이 거주하는 지점을 찾기
				System.out.println(houseList.get(i).position); // 전체 인구의 절반 이상이 되는 지점을 찾으면 바로 위치를 출력하고 프로그램을 종료
				break;
			}
		}
	}
}