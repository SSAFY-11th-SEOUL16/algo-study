package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3079_입국심사 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            상근, 친구들 총 M명
            공항 한줄

            입국 심사대 N개

            k번 심사관 시간 T_k

            시간 최솟값을 매개변수로 하여 이분 탐색

            maxT = 1_000_000_000 부터 시작

            현재 시간 안에 각 입국 심사대에서 처리할 수 있는 인원 수 구함
            그 인원 수의 합이 M보다 크거나 같으면 가능, 작으면 불가능
         */

        tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken()); // 심사대 수
        long M = Integer.parseInt(tokens.nextToken()); // 인원 수

        long[] times = new long[N];
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }

        long rt = 1_000_000_000 * (M / N + 1);
        long lt = 1;

        while(lt <= rt){
            long mt = (lt + rt) / 2;

            long cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += mt / times[i];
                if(cnt >= M) break;
            }

            if(cnt >= M){
                // 가능한 경우
                rt = mt - 1;
            }else{
                // 불가능한 경우
                lt = mt + 1;
            }
        }

        System.out.println(rt + 1);
    }
}