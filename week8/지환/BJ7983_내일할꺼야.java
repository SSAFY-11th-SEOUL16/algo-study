package week8.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//Java8(1632ms)
//1일부터 가장 많이 놀기 위해서는 모든 과제들을 가능한 뒤로 미루어야 가능하다.
//따라서 끝내야하는 날짜(t)가 가장 뒤에 있는 과제부터 시작해 모든 과제들을 가능한 뒤로 미루어야하는 그리디로 접근해야한다.
//두가지 분기가 존재한다. 1) 이전 과제의(t가 더 큰 과제가) 시작이 현재 과제의 t보다 클경우 2) 작은경우
// 1번일 경우에는 현재 과제는 t-d부터 t까지이다. 2일 경우는 이전과제의 시작날짜 - d 부터 시작해야한다.
public class BJ7983_내일할꺼야 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		List<int[]> list = new ArrayList<>();

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			list.add(new int[] {d,t});
		}

		Collections.sort(list, (o1,o2) -> o2[1] - o1[1]);

		int tmp = list.get(0)[1];
		for(int i=0; i<n; i++) {
			int day = list.get(i)[0];
			int time = list.get(i)[1];
			if(tmp > time) tmp = time;
			tmp -= day;
		}
		System.out.println(tmp);
	}
}
