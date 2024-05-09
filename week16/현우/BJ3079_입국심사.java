import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 248 ms
 * 이분 탐색
 * 총 걸리는 시간 기준으로 이분 탐색
 * 모든 심사대에 mid값 이하로 최대한 배정
 * 모두 배정 가능하면 시간 감소 후 탐색
 * 배정 불가능 하면 시간 증가 후 탐색
 * lowerBound 출력
 * */
public class BJ3079_입국심사 {
    private static final Long MAX = 1_000_000_000L;
    
    private static int n;
    private static long m;
    private static long[] t;
    
    private static final boolean validate(long time) {
        int i;
        long people;
        
        people = m;
        for (i = 0; i < n; i++) {
            people -= time / t[i]; // 한 심사대에 배정할 수 있는 친구
            if (people <= 0) { // 모두 배정 가능
                return true;
            }
        }
        return false; // 배정 실패
    }
    
    private static final long lowerBound() {
        long mid;
        long left;
        long right;
        
        left = 1;
        right = ((m - 1) / n + 1) * MAX; // 모든 심사대가 MAX 시간이 걸릴 경우
        while (left < right) {
            mid = left + right >> 1;
            if (validate(mid)) { // 모두 배정 가능 하면
                right = mid; // 시간 감소 후 탐색
            } else { // 배정 불가능 하면
                left = mid + 1; // 시간 증가 후 탐색
            }
        }
        return right;
    }
    
    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader br;
        StringTokenizer st;
        
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());
        t = new long[n];
        for (i = 0; i < n; i++) {
            t[i] = Long.parseLong(br.readLine()); // 심사대 별 소요 시간
        }
        System.out.print(lowerBound());
    }
}
