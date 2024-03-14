package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1943_동전_분배 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Set<Integer> set = new HashSet<>();
		StringTokenizer tokens;
		for (int i = 0; i < 3; i++) {
			int N = Integer.parseInt(br.readLine());
			/* 
			 * 7 7 7
			 * 5 5 5
			 * 3 3
			 * 
			 * 두 사람이 S원을 가져야 한다면
			 * 
			 * i 동전의 개수가 C[i]일 때
			 * 
			 * 각각 0~C[i]만큼 가져갈 수 있음
			 * 
			 * i 동전에서 K만큼 가져갔다면
			 * 
			 * 21
			 * 
			 * 0
			 * 
			 * 0 7
			 * 
			 * 0 7 14
			 * 
			 * 0 7 14 21
			 * 
			 * 0 5 7 12 14 19 21 ---
			 * 
			 * 0 5 7 10 12 14 16 19 
			 * 
			 * 남은 동전의 합이 목표에 도달하지 못하면 불가능한 경우!
			 * 
			 */

			set.add(0);

			int sum = 0;
			for (int j = 0; j < N; j++) {
				tokens = new StringTokenizer(br.readLine());
				
				int num = Integer.parseInt(tokens.nextToken());
				int cnt = Integer.parseInt(tokens.nextToken());	

				Set<Integer> nextSet = new HashSet<>();
				for (int n : set) {
					int alpha = 0;
					for (int k = 0; k <= cnt; k++) {
						if(nextSet.contains(n+alpha)) break;
						nextSet.add(n+alpha);
						alpha += num;
					}
				}
				set = nextSet;
				sum += num * cnt;
			}
			
			if(sum % 2 == 0 && set.contains(sum / 2)) {
				System.out.println(1);
			}else {
				System.out.println(0);
			}
			set.clear();
		}
	}
}