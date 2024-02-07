import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1497_기타콘서트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, curr, max, min, i, j, k;
		long sum, temp;
		long[] guitar;
		char[] str;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		guitar = new long[n];
		sum = 0;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			str = st.nextToken().toCharArray();
			for (j = 0; j < m; j++) {
				guitar[i] <<= 1;
				if (str[j] == 'Y') {
					guitar[i]++;
				}
			}
			sum |= guitar[i];
		}
		if (sum == 0) {
			System.out.print("-1");
			return;
		}
		min = n;
		max = (1 << n) - 1;
		for (i = 1; i <= max; i++) {
			temp = 0;
			curr = 0;
			for (j = i, k = 0; j > 0; j >>= 1, k++) {
				if ((j & 1) == 1) {
					curr++;
					temp |= guitar[k];
				}
			}
			if (temp == sum) {
				min = Math.min(min, curr);
			}
		}
		System.out.print(min);
	}
}
