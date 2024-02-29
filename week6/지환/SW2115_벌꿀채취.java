import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Java(119ms)
public class SW2115_벌꿀채취 {
	static int n, m, c;
	static int[][] board;
	static int[] honeyRows; //행별 꿀의 최대값 -> 추후 여기서 2개를 뽑기 위한 배열
	static int res; //결과값
	static int subSetMax; //벌통의 크기에서 나올수 있는 최대값
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		for(int t=1; t<=test; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			board = new int[n][n];
			honeyRows = new int[n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			res = 0;
			solve();
			System.out.println("#" + t + " " + res);
			
		}
	}
	
	public static void solve() {
		for(int i=0; i<n; i++) {
			honeyRows[i] = getRowMaxProfit(i);
		}
		combiRows(0, 0, 0);
	}
	//밑의 함수들을 통해 행별 나올 수 있는 꿀의 최대값 배열을 구했다.
	//행 2개를 선택해 최대로 꿀을 채취하는 경우를 구하면 된다.
	public static void combiRows(int idx, int cnt, int sum) {
		if(cnt == 2) {
			res = Math.max(sum, res);
			return;
		}
		for(int i=idx; i<n; i++) {
			combiRows(i+1, cnt+1, sum + honeyRows[i]);
		}
	}
	//한 행에서 나올 수 있는 꿀의 최대값을 구한다.
	public static int getRowMaxProfit(int row) {
		int max = 0;
		for(int i=0; i<=n-m; i++) {
			subset(row, i, 0, 0, 0);
			max = Math.max(subSetMax, max);
			subSetMax = 0;
		}
		return max;
	}
	
	//한 행에서 벌통의 크기만큼 가능한 부분집합들의 최대값을 구한다.
	public static void subset(int row, int col, int cnt, int honeySum, int profit) {
		if(honeySum > c) return;
		if(cnt == m) {
			subSetMax = Math.max(subSetMax, profit);
			return;
		}
		subset(row, col, cnt+1, honeySum + board[row][col + cnt], profit + (int)Math.pow(board[row][col + cnt], 2));
		subset(row, col, cnt+1, honeySum, profit);
	}
}
