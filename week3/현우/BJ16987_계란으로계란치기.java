import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16987_계란으로계란치기 {
	private static int n;
	private static int[] durability, weight;
	
	private static int dfs(int depth, int broken) {
		int max, temp, i;
		
		if (depth == n) {
			return broken;
		}
		if (durability[depth] <= 0) {
			return dfs(depth + 1, broken);
		}
		max = broken;
		for (i = 0; i < n; i++) {
			if (i == depth || durability[i] <= 0) {
				continue;
			}
			temp = broken;
			durability[i] -= weight[depth];
			if (durability[i] <= 0) {
				temp++;
			}
			durability[depth] -= weight[i];
			if (durability[depth] <= 0) {
				temp++;
			}
			max = Math.max(max, dfs(depth + 1, temp));
			durability[i] += weight[depth];
			durability[depth] += weight[i];
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i;
		
		n = Integer.parseInt(br.readLine());
		durability = new int[n];
		weight = new int[n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			durability[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}
		System.out.print(dfs(0, 0));
	}
}
