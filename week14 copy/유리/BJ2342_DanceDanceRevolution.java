import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer> move = new ArrayList<>();
	static int [][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		while(true) {
			int tmp = Integer.parseInt(str.nextToken());
			if(tmp == 0) {break;}
			move.add(tmp);
		}
		dp = new int[5][5][move.size()+1];
		for(int i = 0; i < 5; i++) {
			for(int x = 0; x < 5; x++) {
				Arrays.fill(dp[i][x], -1);
			}
		}
		System.out.println(solve(0,0,0));
	}
	private static int solve(int left, int right, int cnt) {
		if(cnt == move.size()){
			return 0;
		}
		
		if(dp[left][right][cnt] != -1) {
			return dp[left][right][cnt];
		}
		
		dp[left][right][cnt] = Math.min(solve(move.get(cnt),right, cnt+1) + energy(left, move.get(cnt)), solve(left, move.get(cnt), cnt+1) + energy(right,move.get(cnt)));
		return dp[left][right][cnt];
	}

	static int energy(int pos, int des) {
		int num = Math.abs(pos-des);
		if(pos ==0) return 2;
		else if(num == 0) return 1;
		else if(num == 1|| num ==3) return 3;
		else return 4;
	}
}
