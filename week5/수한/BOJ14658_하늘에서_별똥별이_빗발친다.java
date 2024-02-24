package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14658 {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        /*
         * 지표면에 떨어지는 별똥별의 수 최소화
         * L*L 트램펄린 준비
         * 
         * 0 0 1 0 0
         * 1 0 0 0 0
         * 1 1 0 0 0
         * 0 0 1 1 1
         * 0 1 0 1 0
         * 1 0 0 0 1
         * 
         * 누적합???
         * ㄴㄴ 메모리 초과
         * 
         * 250_000_000_000
         * 
         * K가 100개????
         * 
         * K개 중 k를 고르는 경우의 수?
         * KCk
         * 
         * 100C50 (X)
         * 
         * 백트래킹??
         * ㄴㄴ 시간초과
         */
        
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int L = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        
        int[][] stars = new int[K][2];
        for (int i = 0; i < K; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			stars[i][0] = Integer.parseInt(tokenizer.nextToken());
			stars[i][1] = Integer.parseInt(tokenizer.nextToken());
		}
        
        // 오름차순 정렬
        Arrays.sort(stars,(a,b)-> a[0]==b[0] ? a[1] - b[1] : a[0] - b[0]);

        int answer = 1;
		
        int minX, minY, maxX, maxY;
        for (int i = 0; i < K; i++) {
			// 맨 위 별부터 트램펄린 탐색
        	// 트램펄린은 왼쪽, 아래쪽부터 펼침

        	// 위쪽 별똥별 구하기        	        	
        	int[] upStar = stars[i];
        	minX = upStar[0];
			maxX = minX + L;
			
			for (int j = i+1; j < K; j++) {
        		// 기준 별똥별 구하기
	        	int[] tempStar = stars[j];
	        	
	        	// 위쪽 별똥별에서 최대로 내려서 설치해도 불가능한 경우
	        	if(tempStar[0] - upStar[0] > L) break;
	        	
	        	// 위쪽 별똥별을 포함하지 못하는 경우
	        	if(Math.abs(upStar[1] - tempStar[1]) > L) continue;
	        	
				minY = Math.min(tempStar[1], upStar[1]);
				maxY = minY + L;

	        	int cnt = 0;
				for (int k = i; k < K; k++) {
					// 나머지 별똥별 구하기
					int[] star = stars[k];
					if(star[0] < minX || star[0] > maxX || star[1] < minY || star[1] > maxY) continue;
					cnt++;
				}
				answer = Math.max(answer, cnt);
			}
		}
        
        
        System.out.println(K - answer);
    }
}