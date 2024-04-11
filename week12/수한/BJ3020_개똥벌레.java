package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3020_개똥벌레{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        tokens = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int H = Integer.parseInt(tokens.nextToken());

        /*
            1. 종유석 : 중간부터 누적합
            2. 석순 : 아래부터 중간까지 누적합
            3. num이 최소가 되는 값과 그 개수 구함
         */

        int[] numObstacle = new int[H];

        boolean isBottom = true;
        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(br.readLine());
            if(isBottom){
                numObstacle[0] += 1;
                numObstacle[size] -= 1;
                isBottom = false;
            }else{
                numObstacle[H-size] += 1;
                isBottom = true;
            }
        }

        for (int i = 1; i < H; i++) {
            numObstacle[i] += numObstacle[i-1];
        }

        int min = Integer.MAX_VALUE;
        int minCnt = 0;
        for (int i = 0; i < H; i++) {
            int numObs = numObstacle[i];
            if(min > numObs){
                minCnt = 1;
                min = numObs;
            }else if(min == numObs){
                minCnt++;
            }
        }

        System.out.println(min+" "+minCnt);

    }
}