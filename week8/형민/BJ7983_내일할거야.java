import java.io.*;
import java.util.*;

public class BJ7983_내일할거야 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int arr[][] = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); //과제 끝내는데 걸리는 기간
            arr[i][1] = Integer.parseInt(st.nextToken()); //마감일자
        }

	//마감일자를 기준으로 오름차순 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        //최소 과제 시작일 설정
        int minWorkStart = arr[0][1] - arr[0][0] + 1;

        for (int i = 1; i < n; i++) {
	    //현재 최소 과제 시작일
            int cur = arr[i][1] - arr[i][0] + 1;
            //이전 과제와 겹칠 때
            if(cur <= arr[i-1][1]) {
                minWorkStart = minWorkStart - (arr[i - 1][1] - cur + 1);
            } else { //마감일자 갱신 => 더 빨리 끝낼 수 있기 때문
                arr[i][1] = arr[i - 1][1] + arr[i][0];
            }
        }

        System.out.println(minWorkStart - 1);
    }
}
