import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 완전탐색 
 * 각 i번째 열을 a로 바꿨을 때 b로 바꿨을 때 그대로 두었을 때의 경우별로 뻗어나가면서 심사를 통과할 수 있는지 확인하는 방법으로 접근
 * 예시로 W = 2일 때 0번째 열을 a로 바꾼다음 다음 열도 a로 바꾸는 경우 a a의 케이스를 확인하고 그 다음 a b의 케이스 그 다음은 a 원본 확인
 * 1. 처음에는 기저 조건 내에서 copyArr을 만들어서 a 또는 b로 바꿔주고 진행했지만 테케는 통과하지만 시간 초과 남
 * 2. 1번 가지치기로 바꾼 횟수값이 현재의 result 보다 작을경우 return 걸어줌 -> 또 시간초과
 * 3. 2번 기저조건에서 copyArr을 생성하지 않고 탐색을 진행하기 전에 값을 바꿔주고 진행 -> 또 시간초과
 * 4. 3번 K를 만족하는지 확인하는 부분에서 한개라도 만족을 못한다면 return 하도록 조건 추가 -> 550ms 통과 
 */

public class SW2112_보호필름 {
    private static int result;
    private static int[][] origin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            result = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());	

            int D = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            origin = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                	origin[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] copyArr = new int[D][W];
            for (int i = 0; i < D; i++) {
                System.arraycopy(origin[i], 0, copyArr[i], 0, W);
            }
     

            dfs(0, copyArr, D, W, 0, K);

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);

    }

    private static void dfs(int L,int[][] copyArr, int D, int W, int changeCnt, int K) {
        //2번
        if (changeCnt > result) {
            return;
        }
        if (L == D) {
            int pass = 0;
            for (int i = 0; i < W; i++) {
                int now = copyArr[0][i];
                int count = 1;
                for (int j = 1; j < D; j++) {
                    if (now == copyArr[j][i]){
                        count += 1;
                    }

                    if (count == K) {
                        pass = (pass | (1 << i));
                        break;
                    }
                    if (now != copyArr[j][i]) {
                        now = copyArr[j][i];
                        count = 1;
                    }
                }
                //4번
                if ((pass & (1 << i)) == 0) {
		return;
	    }
            }
            if ((pass & ((1 << W) - 1)) == ((1 << W) - 1)) {
                result = Math.min(result, changeCnt);
            }
            return;
        }
        //3번
        Arrays.fill(copyArr[L], 0);
        dfs(L + 1, copyArr, D, W, changeCnt + 1, K);

        Arrays.fill(copyArr[L], 1);
        dfs(L + 1, copyArr, D, W, changeCnt+1, K);

        System.arraycopy(origin[L], 0, copyArr[L], 0, W);
        dfs(L + 1, copyArr, D, W, changeCnt, K);
    }
}
