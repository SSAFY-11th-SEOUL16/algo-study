import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Java8(1192ms)
public class BJ9466_텀프로젝트 {
	static int[] team;
	
	//방문했는지 여부 배열
	static boolean[] visited;
	//현재 사이클 내에 있는지 확인하는 배열
	//1->3->4->3 일 경우를 확인해야함
	static boolean[] incycle;
	static int n;
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		for(int i=0; i<t; i++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			team = new int[n+1];
			visited = new boolean[n+1];
			incycle = new boolean[n+1];
			res = 0;
			for(int j=1; j<=n; j++) {
				team[j] = Integer.parseInt(st.nextToken());
			}
			for(int j=1; j<=n; j++) {
				dfs(0, j, -1);
			}
			ans.append(n-res).append('\n');
		}
		System.out.print(ans);
	}
	
	public static void dfs(int cnt, int tmp, int prev) {
		//incycle[tmp]가 true면 현재 사이클 내에 있다는 의미
		//while문 통해 사이클에 포함된 개수 구함
		if(incycle[tmp]) {
			res++;
			while(tmp != prev) {
				tmp = team[tmp];
				res++;
			}
		}
		//한번 방문한 곳은 사이클인지 이미 확인해봤다는 것이므로 pass
		if(visited[tmp]) return;
		visited[tmp] = true;
		incycle[tmp] = true;
		dfs(cnt+1, team[tmp], tmp);
		//현재 도는 사이클에 빠져나왔으니까 false처리
		incycle[tmp] = false;
	}
}
