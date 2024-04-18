package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1644_소수의_연속합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
            1) 아레스토 테네스의 체로 소수 판별
            2) 입력 받은 수(N)보다 작거나 같으면서 가장 큰 소수부터 더함
            3) 현재 합이 N보다 크거나 같으면 그 합을 이루는 가장 큰 소수 뺌
               3.1) 만약 N과 같으면 경우의 수 1 증가
            4) 현재 합이 N보다 작으면 현재 합을 이루는 소수 다음으로 가장 큰 소수 더함

            23 => 23 (o)
                  19 + 17 = 36 > 23 (x)
                  17 + 13 = 30 > 23 (x)
                  13 + 11 = 24 > 23 (x)
                  11 + 7 + 5 = 23 == 23 (o)
                  7 + 5 + 3 + 2 = 17 < 23 (x)
         */

        int N = Integer.parseInt(br.readLine());

        boolean[] isPrime = new boolean[N+1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= N; i++) {
            if(isPrime[i]){
                for (int j = i+i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int answer = 0;
        Queue<Integer> primes = new ArrayDeque<>();
        int sum = 0;
        for (int i = N; i >= 2; i--) {
            if(isPrime[i]){
                primes.offer(i);
                sum += i;
                if(sum == N){
                    answer++;
                    sum -= primes.poll();
                }else if(sum > N){
                    sum -= primes.poll();
                }
            }
        }

        System.out.println(answer);
    }
}