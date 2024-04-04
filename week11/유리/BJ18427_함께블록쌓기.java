import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N,M,H;
	static LinkedList<ArrayList<Integer>> data;
	static int [][] dpData;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());
		H = Integer.parseInt(str.nextToken());
		data = new LinkedList<>();
		data.add(new ArrayList<>());
		dpData = new int [N+1][1001];
		for(int i = 1; i <= N; i++) {
			data.add(new ArrayList<>());
			str = new StringTokenizer(br.readLine());
			while(str.hasMoreElements()) {
				data.get(i).add(Integer.parseInt(str.nextToken()));
			}
		}
		
		
		for(int i = 0; i <= N; i++) {
			dpData[i][0] = 1;
		}
		
		/**
		System.out.println();
		for(int i2 = 0; i2 <= N; i2++) {
			for(int x2 = 0; x2 <=H; x2++) {
				System.out.print(dpData[i2][x2]+"\t");
			}
			System.out.println();
		}
		System.out.println("===============================");
		**/
		for(int i = 1; i <= N; i++) {
			for(int x = 1; x <= H; x++) {
				for(int tmp : data.get(i)) {
					if(x >= tmp) {
						dpData[i][x] += dpData[i-1][x-tmp];
						dpData[i][x]%=10007;
					}
				}
				dpData[i][x]+= dpData[i-1][x];
				dpData[i][x]%=10007;
			}
			
			/**
			for(int i2 = 0; i2 <= N; i2++) {
				for(int x2 = 0; x2 <=H; x2++) {
					System.out.print(dpData[i2][x2]+"\t");
				}
				System.out.println();
			}
			System.out.println("===============================");
			**/
		}

		System.out.println(dpData[N][H]);
	}
	
}
