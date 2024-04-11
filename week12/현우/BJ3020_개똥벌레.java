import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3020_개똥벌레 {
	
	/**
	 * 224 ms
	 * 누적합
	 * 가장 아래 칸 N / 2 개의 장애물 존재
	 * 석순이 끝난 이후의 위치부터 장애물 감소
	 * 종유석이 시작하는 위치부터 장애물 증가
	 * 가장 아래 칸 부터 가장 위 칸까지 누적합
	 * 장애물이 가장 적은 칸 카운트
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, h, min, cnt, i;
		int[] arr;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		arr = new int[h];
		n >>= 1; // N / 2
		arr[0] = n; // 가장 아래 칸 N / 2 개의 장애물 존재
		for (i = 0; i < n; i++) {
			arr[Integer.parseInt(br.readLine())]--; // 석순이 끝난 이후의 위치부터 장애물 감소
			arr[h - Integer.parseInt(br.readLine())]++; // 종유석이 시작하는 위치부터 장애물 증가
		}
		min = arr[0];
		cnt = 1;
		for (i = 1; i < h; i++) {
			arr[i] += arr[i - 1]; // 가장 아래 칸 부터 가장 위 칸까지 누적합
			if (arr[i] == min) { // 장애물이 가장 적은 칸 카운트
				cnt++;
			} else if (arr[i] < min) { // 최소값 갱신
				min = arr[i];
				cnt = 1;
			}
		}
		sb.append(min).append(' ').append(cnt);
		System.out.print(sb);
	}
}
