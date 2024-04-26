package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12892_생일선물 {

    static class Present implements Comparable<Present>{
        long p, v;
        public Present(long p, long v){
            this.p = p;
            this.v = v;
        }

        @Override
        public int compareTo(Present o) {
            return Long.compare(p,o.p);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            N : 친구 수
            P : 선물 가격
            V : 만족도
            선물의 가격 차이가 D 이상 차이나지 않게

            만족도 최대 합

            1. 선물 가격으로 정렬
            2. 현재 선물을 더했을 때 가격 차이가 D 이상 차이나면
               D 이상 차이나지 않게 낮은 가격의 선물을 버림
            3. 그렇지 않으면 다음 선물로 이동
         */

        tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int D = Integer.parseInt(tokens.nextToken());

        Present[] presents = new Present[N];
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(tokens.nextToken());
            int v = Integer.parseInt(tokens.nextToken());

            presents[i] = new Present(p,v);
        }

        Arrays.sort(presents);

        long maxV = 0;
        long totalV = 0;
        int st = 0;
        for (int ed = 0; ed < N; ed++) {
            long diff = presents[ed].p - presents[st].p;
            if(diff >= D){
                maxV = Math.max(maxV, totalV);
                while(diff >= D){
                    totalV -= presents[st++].v;
                    diff = presents[ed].p - presents[st].p;
                }
            }
            totalV += presents[ed].v;
        }
        maxV = Math.max(maxV, totalV);

        System.out.println(maxV);
    }
}