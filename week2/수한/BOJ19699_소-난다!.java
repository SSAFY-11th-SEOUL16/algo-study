package algorithm.baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19699 {
    static int MAX_NUM;
    static int N,M;
    static int[] hs;
    static boolean[] primes;
    static boolean[] primeH;

    public static void findComb(int idx, int cnt, int h){
        if(cnt==M){
            primeH[h] = primes[h];
            return;
        }
        for (int i = idx; i < N; i++) {
            findComb(i+1,cnt+1,h+hs[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        hs = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hs[i] = Integer.parseInt(tokenizer.nextToken());
            MAX_NUM += hs[i];
        }

        StringBuilder sb = new StringBuilder();
        
        /**
         * N마리 소 중에서 몸무게 합이 소수가 되도록 M마리의 소를 선별했을 때 몸무게 합 모두 출력
         * 
         * === 로직 ===
         * 1. 모든소의 높이 합까지 소수인지 아닌지 저장하는 배열 (primes) 생성
         *    - 에라스토테네스의 체
         * 2. 소 M마리를 선별했을 때의 몸무게 합이 소수인 경우 저장하는 배열 (primeH) 생성  
         * 3. 조합을 통해 M마리 선별 후 몸무게 합이 h라면
         *    - primeH[h] = primes[h] 적용
         *    - h가 소수라면 true, 아니면 false라고 바로 저장 가능
         * 4. 소수인 경우가 하나도 없다면 -1 출력 아니면 소수들 전부 출력
         */

        // 에라스토 테네스의 체
        // 모든소의 높이 합까지 소수인지 아닌지 저장
        primes = new boolean[MAX_NUM+1];

        for (int i = 2; i <= MAX_NUM; i++) {
            primes[i] = true;
        }

        for (int i = 2; i <= MAX_NUM; i++) {
            if(primes[i]){
                for(int j = i+i; j <= MAX_NUM; j+=i){
                    primes[j] = false;
                }
            }
        }

        // 소 M마리를 선별했을 때의 몸무게 합이 소수인 경우 저장
        primeH = new boolean[MAX_NUM+1];
        
        // 소 M마리 선별
        findComb(0,0,0);

        // 소 M마리 선별했을 때 몸무게 합이 소수인 수 출력
        for (int i = 2; i <= MAX_NUM; i++) {
            if(primeH[i]) sb.append(i).append(" ");
        }

        // 만약 소수가 하나도 없다면 -1 출력
        if(sb.length()==0) System.out.println("-1");
        else System.out.println(sb);
    }
}