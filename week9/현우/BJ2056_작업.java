import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BJ2056_작업 {
	
	/**
	 * 444 ms
	 * 위상 정렬 단순버전
	 * 첫 번째 작업 완료 시각
	 * = 첫 번째 작업에 걸리는 시간
	 * N 번 작업 완료 시각
	 * = 선행 작업 중 가장 늦게 끝나는 시각
	 * + N 번 작업에 걸리는 시간
	 * 가장 늦게 끝나는 작업의 완료 시각 출력
	 */
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, num, weight, max, i, j;
        int[] distance;

        n = Integer.parseInt(br.readLine());
        distance = new int[n + 1]; // 작업들 각각의 완료 시각
        max = 0;
        for (i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            distance[i] = weight = Integer.parseInt(st.nextToken()); // i 번 작업에 걸리는 시간
            num = Integer.parseInt(st.nextToken()); // 선행 작업 개수
            for (j = 0; j < num; j++) { // 선행 작업 중 가장 늦게 끝나는 시각 + i 번 작업에 걸리는 시간
                distance[i] = Math.max(distance[i], distance[Integer.parseInt(st.nextToken())] + weight);
            }
            if (max < distance[i]) {
                max = distance[i]; // 가장 늦게 끝나는 작업의 완료 시각
            }
        }
        System.out.print(max);
    }
}