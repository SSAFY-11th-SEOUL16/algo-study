import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기3
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
	
	static int N,W,H;
	static int numBlocks;
	static int answer;
	
	static int[] dw = {1,0,-1,0};
	static int[] dh = {0,1,0,-1};
	
	public static boolean isBound(int h, int w) {
		return w < 0 || w >= W || h < 0 || h >= H;
	}
	
	public static void breaking(int h, int w, Block[][] blocks) {
		Block b = blocks[h][w];
		
		b.isBroken = true;
		
		if(b.num == 1) return;
		
		for (int i = 0; i < 4; i++) {
			int nw = w;
			int nh = h;
			for (int j = 1; j < b.num; j++) {
				nw += dw[i];
				nh += dh[i];
				if(isBound(nh,nw)) break;
				if(blocks[nh][nw] == null || blocks[nh][nw].isBroken) continue;
				breaking(nh,nw, blocks);
			}
		}
	}
	
	public static int simulation(int w, Block[][] blocks, int[] topIdxs) {
		// 블록 깨기
		breaking(topIdxs[w],w,blocks);

		int numBroken = 0;
		// 깨진 블록 삭제하기
		for (int j = 0; j < W; j++) {
			int topIdx = H-1; // 현재 제일 높은 블럭 위치
			for (int k = H-1; k >= topIdxs[j]; k--) {
				if(!blocks[k][j].isBroken) {
					blocks[topIdx][j] = blocks[k][j];
					if(k != topIdx) blocks[k][j] = null;
					topIdx--;
				}else {
					numBroken++;
					blocks[k][j] = null;
				}
			}
			topIdxs[j] = topIdx+1;
		}
		
		return numBroken;
	}
	
	public static void perm(int cnt, Block[][] blocks, int[] topIdxs, int numBroken) {
		if(answer == numBlocks) return;
		
		if(cnt == N || numBroken == numBlocks) {
			answer = Math.max(answer, numBroken);
			return;
		}

		Block[][] copyBlocks = new Block[H][W];
		
		int[] copyTopIdxs;
		
		for (int i = 0; i < W; i++) {
			if(topIdxs[i] == H) continue;
			
			for(int k = 0; k < W; k++) {
				for (int j = topIdxs[k]; j < H; j++) {
					copyBlocks[j][k] = new Block(blocks[j][k].num);
				}
			}
				
			copyTopIdxs = topIdxs.clone();
			
			perm(cnt+1, copyBlocks, copyTopIdxs, numBroken + simulation(i, copyBlocks, copyTopIdxs));
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
        Block[][] blocks = new Block[15][13]; // H, W
    	
    	int[] topIdxs = new int[13]; // 가로 축마다 쌓인 블록의 수
    	
    	StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
        	tokens = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(tokens.nextToken());
        	W = Integer.parseInt(tokens.nextToken());
        	H = Integer.parseInt(tokens.nextToken());
        	
        	Arrays.fill(topIdxs, H);

        	numBlocks = 0;
        	for (int i = 0; i < H; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					int num = Integer.parseInt(tokens.nextToken());
					if(num != 0) {
						blocks[i][j] = new Block(num);
						topIdxs[j]--;
						numBlocks++;
					}else {
						blocks[i][j] = null;
					}
				}
			}
        	
        	answer = 0;
        	
        	perm(0, blocks, topIdxs, 0);
        	
        	sb.append("#").append(t).append(" ").append(numBlocks - answer).append("\n");
		}
        
        System.out.println(sb);
    }
}