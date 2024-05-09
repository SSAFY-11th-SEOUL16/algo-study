import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 72 ms
 * 완전 탐색
 * 자리마다 +, -, 공백 넣어가며 DFS
 * 직전의 연산 정보는 따로 저장하다가
 * 공백이 아닌 연산을 만나면 수행
 * 가장 긴 길이에 대해 탐색 하면서
 * 출력 해야 하는 길이의 정답만 저장
 * */
public class BJ7490_0만들기 {
    private static final int MAX = 10;
    private static final int SIZE = 500;
    private static final int DIFF = '0';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char SPACE = ' ';
    
    private static int max;
    private static int[] num;
    private static int[] idx;
    private static byte[] expression;
    private static ArrayList<ArrayList<byte[]>> list;
    
    private static final void dfs(int sum, int temp, int depth) {
        int next;
        
        if (num[depth] >= 0 && sum + temp == 0) { // 출력 길이, 연산 합 0
            list.get(num[depth]).add(Arrays.copyOf(expression, idx[depth] + 2));
        }
        if (depth == max) { // 출력 최대 길이 도달
            return;
        }
        next = depth + 1;
        expression[idx[next]] = SPACE; // 공백
        dfs(sum, temp * 10 + (temp > 0 ? next : -next), next); // 직전 연산만 변경
        expression[idx[next]] = PLUS; // 덧셈
        dfs(sum + temp, next, next); // 연산 반영 후 DFS
        expression[idx[next]] = MINUS; // 뺄셈
        dfs(sum + temp, -next, next); // 연산 반영 후 DFS
    }
    
    public static void main(String[] args) throws IOException {
        int n;
        int t;
        int i;
        byte j;
        byte[] ans;
        BufferedReader br;
        
        num = new int[MAX];
        Arrays.fill(num, -1);
        idx = new int[MAX]; // 깊이 별 연산자 위치
        for (i = 1; i < MAX; i++) {
            idx[i] = ((i - 1) << 1) - 1;
        }
        list = new ArrayList<>(); // 정답 리스트
        br = new BufferedReader(new InputStreamReader(System.in));
        t = br.read() - DIFF;
        for (i = 0; i < t; i++) { // 출력할 길이들 입력
            br.read();
            n = br.read() - DIFF;
            num[n] = i;
            list.add(new ArrayList<>());
            max = Math.max(max, n);
        }
        expression = new byte[(max << 1) + 1];
        for (i = 1, j = '1'; i <= max; i++, j++) { // 식에 숫자 대입
            expression[idx[i] + 1] = j;
        }
        dfs(0, 1, 1); // DFS
        ans = new byte[SIZE];
        i = 0;
        for (ArrayList<byte[]> strs : list) { // 출력
            for (byte[] str : strs) {
                System.arraycopy(str, 0, ans, i, str.length);
                i += str.length;
                ans[i++] = '\n';
            }
            ans[i++] = '\n';
        }
        System.out.write(ans, 0, i);
    }
}
