import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,m,k, result = 0;
	static int cost[];
	static ArrayList<Integer>[] friend;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		k = Integer.parseInt(str.nextToken());
		
		cost = new int[n+1];
		friend = new ArrayList[n+1];
		str = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(str.nextToken());
			friend[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			str = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(str.nextToken());
			int tmp2 = Integer.parseInt(str.nextToken());
			
			friend[tmp].add(tmp2);
			friend[tmp2].add(tmp);
		}
		
		int count = 0;
		boolean check[] = new boolean[n+1];
		
		for(int i = 1; i <= n; i++) {
			if(check[i]) {continue;}
			//System.out.println(i + "========================");
			Queue<Integer> q = new ArrayDeque<>();
			check[i] = true;
			q.add(i);
			int tmpCost = cost[i];
			while(!q.isEmpty()) {
				int tmp = q.poll();
				for(int tmp2 : friend[tmp]) {
					if(check[tmp2]) {continue;}
					//System.out.println(tmp2);
					tmpCost = Math.min(tmpCost, cost[tmp2]);
					check[tmp2] = true;
					q.add(tmp2);
					count++;
				}
			}
			//System.out.println(tmpCost);
			result += tmpCost;
			if(count == n) {
				break;
			}
		}
		
		if(k >= result) {
			System.out.println(result);
		}else {
			System.out.println("Oh no");
		}
	}
}
