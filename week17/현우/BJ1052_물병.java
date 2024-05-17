import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 92 ms
 * 비트마스킹
 * 물병 하나의 물의 양은 항상 2의 거듭제곱
 * 물병을 하나씩 사면서 비트의 개수가 K개 이하이면
 * 구매한 물병 수 출력
 * */
public class BJ1052_물병 {
	public static void main(String[] args) throws IOException {
		int n;
		int k;
		int ans;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for (ans = 0; Integer.bitCount(n) > k; ans++, n++); // 비트 개수 K개 초과인 동안 물병 구매
		System.out.print(ans); // 구매한 물병 수 출력
	}
}
