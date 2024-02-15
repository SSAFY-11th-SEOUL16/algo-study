package week4.상현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 참고 O
 * - 처음 시도는 조합으로 풀다가 잘 안풀려서 포기
 * 1. 경우의 수를 구하는 문제 각 인덱스의 값 = 자신보다 키가 큰 동물의 수
 *
 * 0 0 1을 기준으로 보면 2 1 이 들어가게 된다
 * 0번은 가장 큰 아이를 의미하며 2개가 의미하는 바는 토끼 or 냥이 둘 다 올 수 있다.
 * 그 다음 큰 아이가 1일 경우 고 or 토로 결정이 된다
 *  0  0  1
 * 냥 토 토
 * 토 냥 토
 * 냥 토 냥
 * 토 냥 냥
 *
 * - 토끼 / 고양이 두개만이 가능하기 때문에 count 배열에서 각 인덱스가 가질 수 있는 최대값은 2이며 그 이상을 가진다면 조합 불가능
 * - N보다 큰 값들이 나올 경우 ex)N = 2 / 5,8 같은 경우 sum 변수를 통해 검증 후 다르면 0 처리
 */
public class BOJ12907_동물원 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] count = new int[41];

        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            count[v]++;
        }

        //직전 count[i] 값이 1이라면 true 아니면 false
        boolean oneFlag = false;
        int result = 1;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (count[i] == 2) {
                if (!oneFlag) {
                    result *= 2;
                } else {
                    break;
                }
            } else if (count[i] == 1) {
                oneFlag = true;
            } else {
                break;
            }
            sum += count[i];
        }

        //마지막이 1로 끝났을 경우
        if (oneFlag) result *= 2;
        //TC에서 2 인데 5,8 같은 경우 예외 처리
        // or 중간에 0이 나와 끊겨서 sum이 N까지 못간 경우 조합할 수 없는 케이스 ex) 각 인덱스 값이 2 0 1 일 경우
        if (sum != N) result = 0;
        System.out.println(result);

    }
}
