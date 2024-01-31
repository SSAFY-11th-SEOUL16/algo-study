package algorithm.SWEA.problems;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2382_미생물_격리
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

            int[][] assoList = new int[K+1][];
            assoList[0] = new int[]{0,0,0,0};

            for (int i = 1; i <= K; i++) {
                StringTokenizer tokens = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(tokens.nextToken());
                int y = Integer.parseInt(tokens.nextToken());

                int num = Integer.parseInt(tokens.nextToken());
                int dir = Integer.parseInt(tokens.nextToken());

                assoList[i] = new int[]{x,y,num,dir-1};
            }
            
        	int[][][] info = new int[N][N][2]; // 이동한 군집의 번호, 최대 미생물 수 저장
            for (int i = 0; i < M; i++) {
            	// 정보 초기화
            	for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						info[j][k][0] = 0;
						info[j][k][1] = 0;
					}
				}
                // 미생물 이동
            	for (int j = 1; j <= K; j++) {
            		int[] asso = assoList[j];
            		int num = asso[2];
            		
            		if(num == 0) continue;

            		int x = asso[0];
            		int y = asso[1];
            		int dirIdx = asso[3];
            		
                    x += dirs[dirIdx][0];
                    y += dirs[dirIdx][1];
                    
                    int curIdx = info[x][y][0];
                    int curMaxNum = info[x][y][1];
                    
                    if(curMaxNum < num){
                    	// 번호 및 최대 미생물 수 갱신
                    	info[x][y][0] = j;  
                    	info[x][y][1] = num;                  	

                    	// 현재 좌표 및 미생물 수 갱신                 
                    	asso[0] = x;
                    	asso[1] = y;
                    	asso[2] += assoList[curIdx][2];

                    	// 기존 군집 삭제
                    	assoList[curIdx][2] = 0;

	                    if(x == 0 || x == N-1 || y == 0 || y == N-1){
	                    	asso[2] /= 2;
	                    	
	                        if(dirIdx <= 1){
	                        	asso[3] = 1 - dirIdx;
	                        }else{
	                        	asso[3] = 5 - dirIdx;
	                        }
	                    }
                    }else{
                    	// 기존 군집에 미생물 수 추가
                    	assoList[curIdx][2] += num;
                    	// 현재 군집 삭제
                    	asso[2] = 0;
                    }
				}
            }

            int answer = Arrays.stream(assoList).mapToInt(a -> a[2]).sum();

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
    }
}