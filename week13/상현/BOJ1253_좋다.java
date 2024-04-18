import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
/**
 * 투포인터
 * 정렬 후 각 숫자별로 투포인터로 탐색 진행
 * lp 시작포인터 rp 맨끝 포인터로 지정
 * lp위치 값  + rp 위치값으로 더해가면서 확인
 */
public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int result = 0;
        for (int i = 0; i < n; i++) {
            int lp = 0;
            int rp = n - 1;
            int t = nums[i];

            while (lp < rp) {
                //더했을 때 t일때
                if (nums[lp] + nums[rp] == t) {
                    //i번 위치 즉 자기 자신이 아닐 경우에만 결과값 더해줌
                    if (lp != i && rp != i) {
                        result++;
                        break;
                    } else if (lp == i) {
                        lp++;
                    } else if (rp == i) {
                        rp--;
                    }
                } else if (nums[lp] + nums[rp] < t) {
                    //t보다 작다면 왼쪽 포인터를 증가
                    lp++;
                } else if (nums[lp] + nums[rp] > t) {
                    //t보다 크다면 오른쪽 포인터를 감소
                    rp--;
                }
            }
        }

        System.out.println(result);
    }
}
