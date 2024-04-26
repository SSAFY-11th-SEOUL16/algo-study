import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//투포인터로 포인터를 두고 범위를 계산하며 슬라이딩 윈도우 방식으로 해결
public class BJ12892_생일선물 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int[][] present = new int[n][2];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			present[i][0] = Integer.parseInt(st.nextToken());
			present[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(present, Comparator.comparingInt(o -> o[0]));

		long max = 0;
		int left = 0;
		int right = 0;
		long amount = present[0][1];
		while(right < n && left <= right) {
			int diff = present[right][0] - present[left][0];
			if(diff < d) {
				max = Math.max(amount, max);
				right++;
				if(right < n) amount += present[right][1];
			} else {
				amount -= present[left][1];
				left++;
			}
		}

		System.out.println(max);
	}

}
