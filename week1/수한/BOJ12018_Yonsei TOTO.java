import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        /*
            과목당 마일리지 1~36 분배
            과목당 마일리지 투자 순서로 수강인원 산출

            이미 분배되어있는 마일리지 상태를 보고
            주어진 마일리지를 통해 최대로 들을 수 있는 과목 개수 얻기
        */

        /*
            1. 과목 별 마일리지 정렬
            2. 성준의 과목을 수강하기 위한 최소 마일리지 리스트 생성
                a. 마일리지 등록 수(p)가 수강 인원(l)보다 작은 경우
                    - 해당 과목에 1 마일리지 등록
                b. 마일리지 등록 수(p)가 수강 인원(l)보다 크거나 같은 경우
                    - 해당 과목의 p-l 번째의 마일리지로 등록
            3. 마일리지 리스트 정렬
            4. 최소 마일리지부터 분배
            5. 현재 가지고 있는 마일리지를 초과하면 종료
        */

        // 성준의 과목을 수강하기 위한 최소 마일리지 리스트
        int[] mileages = new int[n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(br.readLine());

            // 과목 신청 사람 수
            int p = Integer.parseInt(tokenizer.nextToken());
            // 과목 수강 인원
            int l = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(br.readLine());

            if(p < l) mileages[i] = 1;
            else{
                int [] subectMileages = new int[p];
                for (int j = 0; j < p; j++) {
                    subectMileages[j] = Integer.parseInt(tokenizer.nextToken());
                }
                Arrays.sort(subectMileages);
                mileages[i] = subectMileages[p-l];
            }
        }

        Arrays.sort(mileages);

        int totalMileage = 0;
        int i;
        for (i = 0; i < n; i++) {
            totalMileage += mileages[i];
            if(totalMileage > m) break;
        }

        System.out.println(i);
    }
}