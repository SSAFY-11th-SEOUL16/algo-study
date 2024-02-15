import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * arr[동물의 대답] 으로 counting 한 후, max 값부터 0 까지 체크
 * 1. 값이 2 인 경우가 나오면, 그 이후로 나오는 값은 모두 2 여야함. ( 2가 아니면 #5 경우로 답은 0 )
 * 2. 값이 2 인 경우는 고양이, 토끼 / 토끼, 고양이 가 될 수 있으므로 *2
 * 예 : 1 0 2 0 1     ( arr[1] = 2 이므로 * 2 )
 *     토 토 토 고 고
 *     고 토 토 고 토
 * 3. 반복문이 종료된 answer 값에 고양이 자리에 토끼, 토끼자리에 고양이 두는 경우까지 계산해서 *2
 * 예 : 1 0 2 0 1
 *     토 토 토 고 고
 *     고 고 고 토 토
 *
 *  특수한 경우
 *  #1. 동물의 수인 N보다 더 큰 값을 입력 받을 때 => 답은 0
 *  #2. 동물의 대답이 2번 보다 더 많이 입력 받을 때 (예시 : 0 0 0 ... ) => 답은 0
 *  #3. 최대값이 0이면서 동물의 수인 N이 2이하일 때 (예시 : N=1 0  N=2 0 0 ) => 답은 2
 *  #4. 입력값이 모두 2개씩 짝 지어질 때 ( 예시 : 0 0 1 1, 0 0 1 1 2 2 ) => 답은 2^(N/2)
 *  #5. 값이 2인 경우가 나온 이후로, 2가 아닌 값이 나온 경우 => 답은 0
 *  #6. arr[?] = 0이 나온 경우 => 답은 0
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;

        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp >= N) { // #1
                System.out.println(0);
                return;
            }
            arr[tmp]++;
            if (arr[tmp] > 2) { // #2
                System.out.println(0);
                return;
            }
            max = Math.max(max, tmp);
        }

        int answer = 1;
        boolean flag = false;


        if (max == 0 && N <= 2) { // #3
            System.out.println(2);
            return;
        }

        int count = 0;


        for (int i = max; i >= 0; i--) {
            if (arr[i] == 2) {
                count++;
            }
        }
        if (count == max + 1) { // #4
            System.out.println((int) Math.pow(2, N / 2));
            return;
        }

        while (max >= 0) {
            if (arr[max] > 0) {
                if (flag && arr[max] < 2) { // #5
                    System.out.println(0);
                    return;
                } else if (arr[max] == 2) {
                    answer *= 2;
                    flag = true;
                }
            } else { // #6
                System.out.println(0);
                return;
            }
            max--;
        }

        System.out.println(answer * 2);

    }
}