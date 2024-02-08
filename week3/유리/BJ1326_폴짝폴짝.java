import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,a,b, result;
	static int [] data;
	static boolean check2;
	static boolean [] check = new boolean [10001];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		n = Integer.parseInt(br.readLine());
		data = new int[n+1];
		str = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			data[i] = Integer.parseInt(str.nextToken());
		}
		str = new StringTokenizer(br.readLine());
		a = Integer.parseInt(str.nextToken());
		b = Integer.parseInt(str.nextToken());
		
		if(a==b) {
			System.out.println(0);
		}else {
			result = Integer.MAX_VALUE;
		}
		
		Queue<int[]> q = new ArrayDeque<>();
		check[a] = true;
		q.add(new int[]{a, 0});
		
		polJak:
		while(!q.isEmpty()) {
			int [] tmp = q.poll();
			int num = data[tmp[0]];
			int next = tmp[0];
			
			while(true) {
				next+= num;
				if(next > n)break;
				if(check[next]) continue;
				check[next] = true;
				
				if(next == b) {
					check2 = true;
					result = tmp[1]+1;
					break polJak;
				}
				q.add(new int[] {next, tmp[1]+1});
				
			}
			
			next = tmp[0];
			while(true) {
				next-= num;
				if(next < 1)break;
				if(check[next]) continue;
				check[next] = true;
				
				if(next == b) {
					check2 = true;
					result = tmp[1]+1;
					break polJak;
				}
				q.add(new int[] {next, tmp[1]+1});
				
			}
		}
		
		if(check2) {
			System.out.println(result);
		}else {
			System.out.println(-1);
		}
	}
}
