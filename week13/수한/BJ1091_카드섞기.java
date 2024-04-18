package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1091_카드섞기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            N개 카드 이용
            카드를 수열 S를 통해 섞어서
            i번 카드를 P[i]에게 보내기 위한 최소 섞는 횟수

            섞고난 후
            k번째 카드는 k % 3번 플레이어에게 감
            
            각 플레이어가 가진 카드를 bit로 표현
         */

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i;
        }

        tokens = new StringTokenizer(br.readLine());
        int[] initP = new int[N];
        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            initP[i] = i % 3;
            P[i] = Integer.parseInt(tokens.nextToken());
        }

        tokens = new StringTokenizer(br.readLine());
        int[] S = new int[N];
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(tokens.nextToken());
        }

        long[] initCards = new long[3];
        long[] curCards = new long[3];
        long[] ansCards = new long[3];
        distributeCards(nums, initP, initCards);
        distributeCards(nums, initP, curCards);
        distributeCards(nums, P, ansCards);

        int answer = 0;
        while(true){
            // 현재 상태가 P와 같은지
            if(curCards[0] == ansCards[0] && curCards[1] == ansCards[1] && curCards[2] == ansCards[2]){
                break;
            }

            // 현재 상태가 처음 상태와 같은지
            if(answer != 0 && curCards[0] == initCards[0] && curCards[1] == initCards[1] && curCards[2] == initCards[2]){
                answer = -1;
                break;
            }

            // 섞기
            int[] nextNums = new int[N];
            for (int i = 0; i < N; i++) {
                int num = nums[i];
                int idx = S[i];
                nextNums[idx] = num;
            }
            nums = nextNums;

            Arrays.fill(curCards,0);
            distributeCards(nums, initP, curCards);
            answer++;
        }

        System.out.println(answer);
    }

    private static void distributeCards(int[] nums, int[] P, long[] cards){
        for (int i = 0; i < nums.length; i++) {
            cards[P[i]] |= (1L << nums[i]);
        }
    }
}