package week8.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
//Java(624ms)
public class BJ2141_우체국 {
	static List<int[]> towns = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		long sum = 0;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int idx = Integer.parseInt(st.nextToken());
			int people = Integer.parseInt(st.nextToken());
			towns.add(new int[] {idx, people});
			sum += people;
		}
		
		long res = 0, left = 0;
		Collections.sort(towns, (o1,o2) -> o1[0] - o2[0]);
		for(int i=0; i<n; i++) {
			left+=towns.get(i)[1];
			if((sum+1)/2 <= left) {
				res = towns.get(i)[0];
				break;
			}
		}
		System.out.println(res);
	}
	
}
