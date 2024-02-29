import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기
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
	
	static List<Block>[] blocks;
	static int N,W,H;
	static int[] wIdx;
	static int answer;
	
	static int[] dw = {1,0,-1,0};
	static int[] dh = {0,1,0,-1};
	
	public static boolean isBound(int w, int h) {
		return w < 0 || w >= W || h < 0 || h >= H;
	}
	
	public static void breaking(int w, int h, List<Block>[] blocks) {
		Block b = blocks[w].get(h);
		
//		System.out.println(w + " " + h + " : " + b.num);
		
		if(b.isBroken) return;
		
		b.isBroken = true;
		
		if(b.num == 1) return;
		
		for (int i = 0; i < 4; i++) {
			int nw = w;
			int nh = h;
			for (int j = 1; j < b.num; j++) {
				nw += dw[i];
				nh += dh[i];
				if(isBound(nw,nh)) break;
				if(nh >= blocks[nw].size()) continue;
				breaking(nw,nh, blocks);
			}
		}
	}
	
	public static void simulation() {
		List<Block>[] copyBlocks = new LinkedList[W];
		
		for (int i = 0; i < W; i++) {
			copyBlocks[i] = new LinkedList<>();
			for (int j = 0; j < blocks[i].size(); j++) {
				copyBlocks[i].add(blocks[i].get(j));
			}
		}
		
		for (int i = 0; i < N; i++) {
			int w = wIdx[i]; // 떨어뜨리는 위치
			int h = copyBlocks[w].size()-1; // 맨 위 블럭 위치
			
			if(h == -1) break; // 이 경우보다 다른 경우가 더 최소일 것이므로
			
			breaking(w,h,copyBlocks);

			for (int j = 0; j < W; j++) {
				for (int k = 0; k < copyBlocks[j].size(); k++) {
					Block b = copyBlocks[j].get(k);
					if(b.isBroken) {
						b.isBroken = false;
						copyBlocks[j].remove(k--);
					}
				}
			}
		}
		
		int numBlocks = 0;
		for (int i = 0; i < W; i++) {
			numBlocks += copyBlocks[i].size();
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
    	blocks = new LinkedList[13];

    	for (int i = 0; i < 13; i++) {
    		blocks[i] = new LinkedList<>();
		}
    	
    	wIdx = new int[4];
        
    	StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
        	tokens = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(tokens.nextToken());
        	W = Integer.parseInt(tokens.nextToken());
        	H = Integer.parseInt(tokens.nextToken());
        	
        	for (int i = 0; i < W; i++) {
        		blocks[i].clear();
			}
        	
        	for (int i = 0; i < H; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					int num = Integer.parseInt(tokens.nextToken());
					if(num != 0) {
						blocks[j].add(0,new Block(num));
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