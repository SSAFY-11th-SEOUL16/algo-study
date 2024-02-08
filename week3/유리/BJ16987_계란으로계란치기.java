import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, result = 0;
	static int [][] tmp;
	static boolean [] check;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		n = Integer.parseInt(br.readLine());
		tmp = new int[n][2];
		for(int i = 0; i < n; i++) {
			str = new StringTokenizer(br.readLine());
			tmp[i][0] = Integer.parseInt(str.nextToken());
			tmp[i][1] = Integer.parseInt(str.nextToken());
		}
		check =new boolean [n];
		calEgg(0, 0, 0);
		System.out.println(result);
	}
	
	static void calEgg(int depth, int handEgg, int max) {
		//테스트용
		//for(int i = 0; i < n; i++) {
		//	System.out.print(check[i]+ " ");
		//}
		//System.out.println();
		
		
		for(int i = 0; i<n; i++) {
			if(handEgg == i || check[i]) {
				continue;
			}
			int tmpMax = max;
			//System.out.println("depth : "+depth+ " handEgg : " + handEgg+ "test2 : " + i);
			tmp[i][0] -= tmp[handEgg][1];
			tmp[handEgg][0] -= tmp[i][1];
			
			if(tmp[i][0] <= 0) {
				//System.out.println("test3");
				check[i] = true;
				tmpMax++;
			}
			if(tmp[handEgg][0] <= 0) {
				//System.out.println("test4");
				check[handEgg] = true;
				tmpMax++;
			}
			
			if(tmpMax > result) {
				result = tmpMax;
			}
			
			if(handEgg+1 != n) {
				for(int x = handEgg+1; x<=n-1; x++) {
					if(check[x]) {
						continue;
					}else {
						//System.out.println("test5");
						calEgg(depth+1, x, tmpMax);
						break;
					}
				}
				
			}
			
			check[i] = false;
			check[handEgg] = false;
			tmp[i][0] += tmp[handEgg][1];
			tmp[handEgg][0] += tmp[i][1];
			
			
			/*
			if(depth == 0) {
				for(int z = 0; z < n; z++) {
					tmp[z] = data[z].clone();
				}
				check =new boolean [n];
			}
			 * */
		}
	}
}
