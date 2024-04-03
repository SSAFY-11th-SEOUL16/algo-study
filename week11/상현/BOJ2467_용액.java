import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 투 포인터
 * 제일 처음 포인터와 제일 끝 포인터를 시작
 * 앞쪽의 포인터와 뒤쪽이 가리키는 포인터의 값을 더했을 때 차이값이 현재 최소값보다 작다면 갱신해줌
 * 차이가 양수일 경우 뒤쪽 포인터를 감소 시키고 음수라면 앞쪽 포인터를 증가시킴
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        int mid = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N - 1;
        int r1 = 0;
        int r2 = 0;

        int min = Integer.MAX_VALUE;

        while(start < end) {
            //차이
            int diff = arr[start] + arr[end];
            //현재 최소값보다 작을 경우 갱신
            if (Math.abs(diff) < min) {
                r1 = arr[start];
                r2 = arr[end];
                min = Math.abs(diff);
            }

            //양수일 경우
            if (diff > 0) {
                end--;
            } else {
                start++;
            }
        }

        System.out.println(r1 + " " + r2);
    }

}