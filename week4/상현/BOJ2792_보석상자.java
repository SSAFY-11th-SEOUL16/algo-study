import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이분탐색, 파라매트릭 서치
 * 1. 질투심의 최대값을 입력과 동시에 구한다.
 * 2. 특정 개수의 구슬을 기준으로 각 구슬들을 나눠줄 경우 몇명에게 나눠줄 수 있는지를 구해가며 범위를 좁힌다.(이분 탐색)
 *    테스트케이스를 기준으로 처음에는 4개의 구슬을 나눠준다고 할 경우 각 색의 구슬들은 2명, 1명에게 나눠주어 총 3명이 구슬을 받는다.
 *    이때 3명이 받는 경우는 5명보다 적기 때문에 더 많은 사람이 받을 수 있는 구슬의 개수를 알기 위해서는 나눠주려는 구슬을 줄일 필요가 있다.
 *    나눠주려는 구슬을 줄이기 위해서는 최대값을 현재 나눠준 구슬의 개수 - 1개로 바꿔준다.
 * 3. 반대로 나누어줄 수 있는 사람의 수가 N명의 사람보다 많다면 구슬을 더 많이 나눠줘도 된다는 의미이다.
 *    이 경우 최소값을 mid + 1값으로 바꿔준다.
 * 4. 위 두가지를 lt <= rt가 만족할때까지 반복하며 질투심이 최소가 되는 값을 구하는 문제
 */
public class BOJ2792_보석상자 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[M];
        int max = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int lt = 1;     //최소값(질투심 최소값), 모든
        int rt = max;   //최대값(질투심 최대값)
        int mid = 0;    //중간값(나눠주는 구슬의 개수)

        while(lt <= rt) {
            //나눠주려는 구슬의 개수
            mid = (lt + rt) / 2;

            //나눠줄 수 있는 사람 수
            int count = 0;
            for (int i = 0; i < M; i++) {
                count += arr[i] / mid;
                if (arr[i] % mid != 0) {
                    count += 1;
                }
            }

            //구슬을 나눠줄 수 있는 사람수가 N보다 크다면
            //나눠주야 하는 구슬의 개수를 올려줘야 하기 때문에 최소값을 증가 시켜준다
            if (count > N) {
                lt = mid + 1;
            }
            //구슬을 나눠줄 수 있는 사람수가 N보다 작거나 같다면
            //나눠줘야 하는 구슬의 개수를 줄여야 하기 때문에 최대값 감소
            else {
                rt = mid - 1;
            }
        }

        System.out.println(lt);

    }
}
