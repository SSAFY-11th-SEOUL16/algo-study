package algorithm.baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_21608 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int powN = N*N;

        /*
            1. 좋아하는 학생이 가장 많이 인접한 칸
            2. 1에 해당하는 칸이 여러개라면 그 칸에서 비어있는 인접한 칸이 가장 많은 칸으로
            3. 2에 해당하는 칸이 여러개라면 행 번호 작은 칸 -> 열 변호 작은 칸
        */

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        int[][] loc = new int[N+2][N+2];

        for (int i = 0; i <= N+1; i++) {
            loc[0][i] = loc[i][0] = loc[N+1][i] = loc[i][N+1] = powN+1;
        }

        boolean[][] favorites = new boolean[powN+2][powN+2];

        int[][] rcs = new int[powN][2];
                
        for (int i = 0; i < powN; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());

            for (int j = 0; j < 4; j++) {
                favorites[n][Integer.parseInt(tokenizer.nextToken())] = true;
            }
            
            int len = 0;
            
            // 1. 좋아하는 학생이 가장 많이 인접한 칸
            int maxSum = 0;
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if(loc[r][c] != 0) continue;
                    int sum = 0; // 인접한 칸의 좋아하는 학생 수
                    for (int l = 0; l < 4; l++) {
                        int nr = r + dirs[l][0];
                        int nc = c + dirs[l][1];
                        // 좋아하는 학생이 있는 칸이라면 sum 증가
                        if(favorites[n][loc[nr][nc]]) sum++;
                    }
                    // 가장 많이 인접한 경우
                    if(maxSum <= sum){
                    	// 진짜 가장 많이 인접한 경우
                    	if(maxSum < sum){
                    		maxSum = sum;
                    		len = 0; // 처음부터 다시 등록
                    	}
                    	rcs[len][0]=r;
                    	rcs[len++][1]=c;                       
                    }
                }
            }

            int[] src = rcs[0];
            if(len > 1){
                // 2. 1에 해당하는 칸이 여러개라면 그 칸에서 비어있는 인접한 칸이 가장 많은 칸으로
                maxSum = 0;
                for(int j=0; j<len; j++){
                	int[] rc = rcs[j];
                	int r = rc[0];
                    int c = rc[1];
                    int sum = 0;
                    for (int l = 0; l < 4; l++) {
                        int nr = r + dirs[l][0];
                        int nc = c + dirs[l][1];
                        if(loc[nr][nc]==0) sum++;
                    }
                    // 3. 2에 해당하는 칸이 여러개라면 행 번호 작은 칸 -> 열 변호 작은 칸
                    if(maxSum < sum){
                        maxSum = sum;
                        src = rc;
                    }
                }
            }

            loc[src[0]][src[1]] = n;
        }

        int answer = 0;

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                int sum = 0;
                int num = 1;
                for (int l = 0; l < 4; l++) {
                    int nr = r + dirs[l][0];
                    int nc = c + dirs[l][1];
                    if(favorites[loc[r][c]][loc[nr][nc]]) {
                        sum = num;
                        num *= 10;
                    }
                }
                answer += sum;
            }
        }

        System.out.println(answer);
    }
}