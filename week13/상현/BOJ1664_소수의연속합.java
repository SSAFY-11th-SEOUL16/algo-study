import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 투포인터
 * 에라토스테네스의 체로 n까지의 소수들을 걸러낸 다음 소수 배열을 만든다
 * 소수를 더하면서 n보다 크다면 lt의 값을 증가시키면서 해당 인덱스의 값을 빼줌
 */
public class Main {
    private static List<Integer>[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[n + 1];
        isPrime[0] = isPrime[1] = true;
        int primCnt = 0;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                primCnt++;
                for (int j = i + i; j <= n; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        int[] primeNum = new int[primCnt];
        int idx = 0;
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                primeNum[idx++] = i;
            }
        }

        int lt = 0;
        int sum = 0;
        for (int i = 0; i < primCnt; i++) {
            sum += primeNum[i];
            if (sum == n) result++;
            //더한 값이 크다면 lt위치를 증가시키며 해당 위치의 소수값을 빼줌
            if (sum > n) {
                while(sum > n) {
                    sum -= primeNum[lt++];
                    if (sum == n) result++;
                }
            }
        }

        System.out.println(result);
    }


}
