package algorithm.SWEA.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2382_미생물_격리_2
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());

        // 상, 하, 좌, 우
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            int K = Integer.parseInt(tokenizer.nextToken());

            int[][][] map = new int[N][N][2];

            for (int i = 0; i < K; i++) {
                StringTokenizer tokens = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(tokens.nextToken());
                int y = Integer.parseInt(tokens.nextToken());

                int num = Integer.parseInt(tokens.nextToken());
                int dir = Integer.parseInt(tokens.nextToken());

                map[x][y] = new int[]{num,dir-1};
            }

            int[][][] newMap = new int[N][N][2];
            int[][] maxNum = new int[N][N];
            for (int i = 0; i < M; i++) {
                // 미생물 이동
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        if(map[j][k][0] == 0) continue;

                        int[] dir = dirs[map[j][k][1]];
                        int nj = j + dir[0];
                        int nk = k + dir[1];

                        if(maxNum[nj][nk] < map[j][k][0]){
                            newMap[nj][nk][1] = map[j][k][1];
                            maxNum[nj][nk] = map[j][k][0];
                        }
                        newMap[nj][nk][0] += map[j][k][0];

                        if(nj == 0 || nj == N-1 || nk == 0 || nk == N-1){
                            newMap[nj][nk][0] /= 2;
                            if(newMap[nj][nk][1] <= 1){
                                newMap[nj][nk][1] = 1 - newMap[nj][nk][1];
                            }else{
                                newMap[nj][nk][1] = 5 - newMap[nj][nk][1];
                            }
                        }
                    }
                }

            	for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
                        map[j][k][0] = newMap[j][k][0];
                        map[j][k][1] = newMap[j][k][1];
                        maxNum[j][k] = 0;
						newMap[j][k][0] = 0;
						newMap[j][k][1] = 0;
					}
				}
            }

            int answer = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    answer += map[j][k][0];
                }
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
    }
}