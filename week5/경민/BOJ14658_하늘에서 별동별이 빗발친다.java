import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<int[]> list = new ArrayList<int[]>();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}

		int count = 0;
		int max = 0;
		for (int[] s1 : list) {
			for (int[] s2 : list) {
				int x = s1[0];
				int y = s2[1];

				for (int[] s3 : list) {
					if (s3[0] >= x && s3[0] <= x + L && s3[1] >= y && s3[1] <= y + L) {
						count++;
					}
				}
				max = Math.max(max, count);
				count = 0;
			}
		}
		System.out.println(K - max);
	}
}
