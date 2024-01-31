import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main {
	static int N;
	static int M;
	static int [] data;
	static boolean[] check;
	static HashSet<Integer> sumList=new HashSet<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		data = new int [N];
		check = new boolean[N];
		tmp = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			data[i]= Integer.parseInt(tmp[i]);
		}
		
		dfs(0,0,0);
		
		if(sumList.size() == 0) {
			System.out.println(-1);
		}else {
			ArrayList<Integer> result = new ArrayList<>(sumList);
			Collections.sort(result);
			for(int i : result) {
				System.out.print(i +" ");
			}
		}
	}
	
	static void dfs(int depth, int sum, int startNum) {
		if(depth == M) {
			boolean check = true;
			for(int i = 2; i <=Math.sqrt(sum); i++) {
				if(sum%i ==0) {
					check = false;
					break;
				}
			}
			if(check) {
				sumList.add(sum);
			}
			return;
		}
		
		for(int i = startNum; i < N; i++) {
			if(!(check[i])){
				check[i] = true;
				dfs(depth+1, sum+data[i], startNum+1);
				check[i] = false;
			}
		}
	}
}
