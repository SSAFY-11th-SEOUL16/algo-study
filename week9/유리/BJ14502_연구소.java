import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, virusNum, wallNum, result;
	static int [][]data;
	static int [][]data2;
	static class loc{
		int x, y;
		loc(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static List<loc> virus;
	static List<loc> tmploc;
	static int []nr = {-1,1,0,0};
	static int []nc = {0,0,-1,1};
	static boolean [] check2;
	static loc tmpWall[];
	static Queue<loc> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		data = new int [n][m];
		virus = new ArrayList<loc>();
		tmploc = new ArrayList<loc>();
		virusNum = 0;
		wallNum = 0;
		result = 0;
		tmpWall = new loc[3];
		for(int x = 0; x < n; x++) {
			str = new StringTokenizer(br.readLine());
			for(int y = 0; y < m; y++) {
				int tmp = Integer.parseInt(str.nextToken());
				data[x][y] = tmp;
				if(tmp == 0) {
					tmploc.add(new loc(x, y));
				}else if(tmp == 1) {
					wallNum++;
				}else {
					virus.add(new loc(x, y));
					virusNum++;
				}
			}
		}
		//System.out.println("dfs 시작!");
		check2 = new boolean[tmploc.size()];
		dfs(0);
		System.out.println(result);
	}
	
	static void dfs(int count) {
		//System.out.println("count : "+ count);
		if(count == 3) {
			data2 = new int [n][m];
			for(int x = 0; x <n; x++) {
				for(int y = 0; y < m; y++) {
					data2[x][y] = data[x][y];
				}
			}
			
			for(int i = 0; i <3; i++) {
				loc tmp = tmpWall[i];
				data2[tmp.x][tmp.y] = 1;
				//System.out.println(tmp.x+"/"+tmp.y);
			}
			
			q = new ArrayDeque<>();
			for(int i = 0; i < virusNum; i++) {
				q.add(virus.get(i));
			}
			int tmpVirusNum=virusNum;
			while(!q.isEmpty()) {
				loc tmp = q.poll();
				for(int i = 0; i <4; i++) {
					int tmpX = tmp.x + nr[i];
					int tmpY = tmp.y + nc[i];
					if(tmpX >= 0 && tmpX <= n-1 && tmpY >=0 && tmpY <= m-1 && data2[tmpX][tmpY] == 0) {
						data2[tmpX][tmpY] = 2;
						q.add(new loc(tmpX, tmpY));
						tmpVirusNum ++;
						
						if(result > (n*m)-wallNum-tmpVirusNum) {
							return;
						}
						/**
						for(int x = 0; x< n;x++) {
							for(int y = 0; y< m;y++) {
								System.out.print(data2[x][y]+"\t");
							}
							System.out.println();
						}
						System.out.println();
						**/
					}
				}
			}

			int tmpResult = 0;
			//테스트용
			for(int x = 0; x<n; x++) {
				for(int y = 0; y <m; y++) {
					if(data2[x][y] == 0) {
						tmpResult++;
					}
				}
			}
			if(result < tmpResult) {
				result = tmpResult;
				//System.out.println(result);
				/**
				for(int x = 0; x< n;x++) {
					for(int y = 0; y< m;y++) {
						System.out.print(data2[x][y]+"\t");
					}
					System.out.println();
				}
				System.out.println("---------------------------------------------");
				**/
			}
			//System.out.println("tmpResult : " + tmpResult);
			return;
		}
		for(int i = 0; i < tmploc.size(); i++) {
			if(check2[i]) {continue;}
			tmpWall[count] = tmploc.get(i);
			check2[i] = true;
			dfs(count+1);
			check2[i] = false;
		}
	}

}
