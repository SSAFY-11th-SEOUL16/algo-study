import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, max = 0;
	static int[] S;
	static int[] W;

	// a와 b 인덱스의 계란을 깰 때,
	public static void hit(int a, int b) {
		S[a] -= W[b];
		S[b] -= W[a];
	}

	// 백트래킹 
	public static void back(int a, int b) {
		S[a] += W[b];
		S[b] += W[a];
	}

	public static void recur(int index, int count) { // 손에 든 계란 index와 깬 계란 개수 count
		// 기저사례) 마지막 index의 계란이거나 모든 계란이 깨졌거나
		if (index >= N || (count == N - 1 && index == N - 1) || count == N) {
			max = Math.max(max, count);
			return;
		}

		if (S[index] <= 0) // 깨져있는 계란이면 다음 계란 호출 
			recur(index + 1, count);

		for (int i = 0; i < N; i++) {
			if (i == N - 1 && count == N - 1)
				recur(i, count);
			if (index == i)
				continue; // 손에 든 계란과 같은 계란이면 패쓰
			if (S[i] <= 0 || S[index] <= 0)
				continue; // 이미 깨진 계란이면 패쓰

			hit(i, index);

			if (S[i] <= 0 && S[index] <= 0) {
				recur(index + 1, count + 2);
			} else if ((S[i] <= 0 && S[index] > 0) || (S[i] > 0 && S[index] <= 0)) {
				recur(index + 1, count + 1);

			} else {
				recur(index + 1, count);
			}
			
			back(i, index);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		S = new int[N];
		W = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st.nextToken());
		}

		recur(0, 0);

		System.out.println(max);

	}
}