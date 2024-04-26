import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 204 ms
 * DP
 * dp[i][j][k] 3 차원 배열
 * = 왼발 i, 오른발 j, k 번째 일때 최소 힘
 * dp[i][j][k]와 dp[j][i][k]는 항상 동치이므로
 * (i, j) 조합으로 index 생성해서
 * idx[i][j]에 저장하고
 * dp[index][k] 2 차원 배열로 변경,
 * dp[idx[i][j]][k]로 참조
 * 왼발 중앙, 오른발 첫 노트 밟은 상태를 Top으로
 * 마지막 노트를 밟은 상태를 0으로
 * Top-Down DP
 * + 처음 오른발을 옮기는 힘 2
 * */
public class BJ2342_DanceDanceRevolution {
	private static final int DIFF = '0';
	private static final int MAX = 5;
	
	private static int size;
	private static int[] chart;
	private static int[][] idx, dp;
	
	private static final int getDp(int left, int right, int depth) {
		int note;
		
		if (depth == size) { // 마지막 노트까지 밟은 상태
			return 0;
		}
		if (dp[idx[left][right]][depth] != 0) { // DP 값이 저장되어 있으면
			return dp[idx[left][right]][depth]; // 저장된 값 반환
		}
		note = chart[depth]; // 밟아야 할 다음 노트
		if (note == left || note == right) { // 왼발이나 오른발을 다시 눌러야 하는 경우
			return dp[idx[left][right]][depth] = getDp(left, right, depth + 1) + 1; // 힘 1
		}
		return dp[idx[left][right]][depth] = Math.min( // 왼발 옮기는 경우, 오른발 옮기는 경우 중 작은 값 반환
				getDp(Math.min(note, right), Math.max(note, right), depth + 1) + (left == 0 ? 2 : ((note - left & 1) == 1 ? 3 : 4)),
				getDp(Math.min(left, note), Math.max(left, note), depth + 1) + ((note - right & 1) == 1 ? 3 : 4));
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int len;
		int index;
		String str;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		len = str.length() - 1;
		if (len == 0) { // 노트 없음
			System.out.print("0");
			return;
		}
		size = len >> 1;
		chart = new int[size];
		for (i = 0; i < size; i++) { // 채보 입력
			chart[i] = str.charAt(i << 1) - DIFF;
		}
		idx = new int[MAX - 1][MAX]; // 왼발, 오른발 조합 인덱스
		index = 0;
		for (i = 0; i < MAX; i++) { // 왼발
			for (j = i + 1; j < MAX; j++) { // 오른발
				idx[i][j] = index++; // 인덱스 생성
			}
		}
		dp = new int[index][size]; // 2 차원 DP
		System.out.print(getDp(0, chart[0], 1) + 2); // 왼발 중앙, 오른발 첫 노트 DP값 + 2
	}
}
