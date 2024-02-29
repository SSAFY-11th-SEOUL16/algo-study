package algorithm.SWEA.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기2
{
	static class Block{
		int num;
		boolean isBroken;

		public Block(int num, boolean isBroken) {
			this.num = num;
			this.isBroken = isBroken;
		}
		
		public Block(int num) {
			this(num,false);
		}
	}
	
	static Block[][] blocks;
	static Block[][] copyBlocks;
	static int N,W,H;
	static int[] wIdx;
	static int answer;
	static int[] size; // 가로 축마다 쌓인 블록의 수
	
	static int[] dw = {1,0,-1,0};
	static int[] dh = {0,1,0,-1};
	
	public static boolean isBound(int h, int w) {
		return w < 0 || w >= W || h < 0 || h >= H;
	}
	
	public static void breaking(int h, int w, Block[][] blocks) {
		Block b = blocks[h][w];
		
		if(b == null || b.isBroken) return;
		
		b.isBroken = true;
		
		if(b.num == 1) return;
		
		for (int i = 0; i < 4; i++) {
			int nw = w;
			int nh = h;
			for (int j = 1; j < b.num; j++) {
				nw += dw[i];
				nh += dh[i];
				if(isBound(nh,nw)) break;
				breaking(nh,nw, blocks);
			}
		}
	}
	
	public static void simulation() {
		for (int i = 0; i < H; i++) {
			copyBlocks[i] = blocks[i].clone();
		}
		
		int[] copySize = size.clone();
		
		for (int i = 0; i < N; i++) {
			int w = wIdx[i]; // 떨어뜨리는 위치
			int h = H-copySize[w]; // 맨 위 블럭 위치
			
			if(h == H) break; // 이 경우보다 다른 경우가 더 최소일 것이므로
			
			// 블록 깨기
			breaking(h,w,copyBlocks);

			// 깨진 블록 삭제하기
			for (int j = 0; j < W; j++) {
				int s = H-1;
				for (int k = H-1; k >= H-copySize[j]; k--) {
					if(!copyBlocks[k][j].isBroken) {
						copyBlocks[s][j] = copyBlocks[k][j];
						if(k != s) {
							copyBlocks[k][j] = null;
						}
						s--;
					}else {
						copyBlocks[k][j].isBroken = false;
						copyBlocks[k][j] = null;
					}
				}
				copySize[j] = H-1-s;
			}
		}
		
		int numBlocks = 0;
		for (int i = 0; i < W; i++) {
			numBlocks += copySize[i];
		}
		
		answer = Math.min(answer, numBlocks);
	}
	
	public static void perm(int cnt) {
		if(cnt == N) {
			simulation();
			return;
		}
		
		for (int i = 0; i < W; i++) {
			wIdx[cnt] = i;
			perm(cnt+1);
		}
	}
	
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        
        /*
         * N 개의 구슬을 떨어뜨려 최대한 많은 벽돌 깨기
         * 
         * 벽돌은 연쇄적으로 깨짐
         * 
         * 12E4 = 12^4
         * 
         * W * H <= 180
         * 
         * 가로축 index 마다 linked list를 생성하고 쌓음
         * 
         * list에는 Block 객체를 둠
         * Block 객체는 숫자와 깨졌는지 정보가 담김
         * 
         */
        
        StringTokenizer tokens;
    	blocks = new Block[15][13]; // H, W
    	copyBlocks = new Block[15][13];
    	
    	size = new int[13];
    	
    	wIdx = new int[4];
        
    	StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
        	tokens = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(tokens.nextToken());
        	W = Integer.parseInt(tokens.nextToken());
        	H = Integer.parseInt(tokens.nextToken());
        	
        	for (int i = 0; i < H; i++) {
        		Arrays.fill(blocks[i], null);
			}
        	
        	Arrays.fill(size, 0);

        	for (int i = 0; i < H; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					int num = Integer.parseInt(tokens.nextToken());
					if(num != 0) {
						blocks[i][j] = new Block(num);
						size[j]++;
					}
				}
			}
        	
        	answer = Integer.MAX_VALUE;
        	
        	perm(0);
        	
        	sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
        
        System.out.println(sb);
    }
}