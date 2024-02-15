import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2792_보석상자 {
	/*
	 * 296 ms
	 * 
	 * 1(left) ~ 가장 많은 보석 개수(right) 중에서
	 * 정답이 될 수 있는 최소값 이분 탐색
	 * 각각의 보석을 최대 mid 개로 분배할 때 필요한 학생 수 계산
	 * i 번 보석 분배 시 필요한 학생 수 = i 번 개수 / mid, 나머지가 존재하면 + 1
	 * 실제 학생 수 N 보다 많은 학생 필요 시 분배 불가능
	 * 	mid 값 늘려보고 다시 탐색
	 * N 보다 적거나 같은 학생만으로 분배 가능
	 * 	mid 값 줄여보고 다시 탐색
	 * 조건을 만족하는 최소값 = lowerBound(마지막 right)
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, left, right, mid, student, i;
		int[] jewel;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		jewel = new int[m]; // 보석 개수
		right = 0;
		for (i = 0; i < m; i++) {
			jewel[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, jewel[i]); // right = 보석 개수 중 최대값
		}
		left = 1;
		while (left < right) { // 이분 탐색
			mid = (left + right) / 2; // 학생들에게 각각 최대 mid 개 만큼 분배
			student = 0; // 분배에 필요한 학생 수
			for (i = 0; i < m; i++) {
				student += (jewel[i] - 1) / mid + 1; // i 번 보석 분배에 필요한 학생 수
			}
			if (student > n) { // 최대 mid 개로 분배 불가능
				left = mid + 1; // 최대 개수 늘려보고 탐색
			} else { // 최대 mid 개로 분배 가능
				right = mid; // 최대 개수 줄여보고 탐색
			}
		}
		System.out.print(right); // 마지막으로 분배 가능했던 mid 값
	}
}
