import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2477_차량정비소
{
    static class Customer{
        int num;
        int numA; // 접수 창구 번호
        int endTime; // 끝나는 시간
        Customer (int num){
            this.num = num;
        }
    }

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
         * ai : 고장 접수 처리 시간
         * bi : 정비 시간
         *
         * 총 K 명
         *
         * tk : k번 고객 도착시간
         *
         * 접수 창구
         * - 고객번호 낮은 순서대로 - 먼저 기다리는 고객 우선
         * - 창구번호 작은 곳
         *
         * 정비 창구
         * - 먼저 기다리는 고객 우선
         * - 두 명 이상의 고객들이 동시에 접수 완료하면? 이용했던 접수 창구번호가 작은 고객 우선
         * - 정비 창구가 많으면 ? 정비 창구번호 작은 곳
         * 
         * 접수 -> 정비
         *
         *
         */

        int T = Integer.parseInt(br.readLine());

        StringTokenizer tokens;

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            tokens = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(tokens.nextToken()); // 접수 창구 개수
            int M = Integer.parseInt(tokens.nextToken()); // 정비 창구 개수
            int K = Integer.parseInt(tokens.nextToken()); // 고객수
            int A = Integer.parseInt(tokens.nextToken()); // 찾으려는 첩수 창구번호
            int B = Integer.parseInt(tokens.nextToken()); // 찾으려는 정비 창구번호

            int[] ptA = new int[N+1]; // 접수 창구 처리시간
            int[] ptB = new int[M+1]; // 정비 창구 처리시간

            tokens = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                ptA[i] = Integer.parseInt(tokens.nextToken());
            }

            tokens = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                ptB[i] = Integer.parseInt(tokens.nextToken());
            }

            int[][] sum = new int[N+1][M+1]; // 정비, 접수 창구 고객 번호 합

            Customer[] cusA = new Customer[N+1]; // 접수 창구 고객 정보
            Customer[] cusB = new Customer[M+1]; // 정비 창구 고객 정보

            Queue<Customer> qA = new ArrayDeque<>();
            Queue<Customer> qB = new ArrayDeque<>();

            // 고객 받기
            tokens = new StringTokenizer(br.readLine());
            int cusNum = 1;
            int arriveTime = Integer.parseInt(tokens.nextToken());
            int numComplete = 0;

            int tk = 0;
            while(numComplete < K){
                // 기다리는 사람 리스트에 추가
                while(tk == arriveTime){
                    qA.add(new Customer(cusNum++));
                    if(!tokens.hasMoreTokens()) break;
                    arriveTime = Integer.parseInt(tokens.nextToken());
                }

                // 접수 창구 처리
                for (int i = 1; i <= N; i++) {
                    // 지금 시간이 끝나는 시간인 경우
                    if(cusA[i] != null && cusA[i].endTime == tk){
                        Customer cus = cusA[i];

                        // 정비 창구 기다리는 곳에 추가
                        qB.add(cus);

                        cusA[i] = null;
                    }

                    // 접수 창구 빈 자리에 기다리는 사람 넣기
                    if(cusA[i] == null && !qA.isEmpty()){
                        Customer cus = qA.poll(); // 기다리는 사람 가져오기

                        cus.endTime = tk + ptA[i]; // 끝나는 시간
                        cus.numA = i; // 고객 접수 창고 번호 갱신
                        cusA[i] = cus; // 현재 접수 창고에 있는 고객 번호 갱신
                    }
                }

                // 정비 창구 처리
                for (int i = 1; i <= M; i++) {
                    // 지금 시간이 정비가 끝나는 시간인 경우
                    if(cusB[i] != null && cusB[i].endTime == tk){
                        Customer cus = cusB[i];

                        sum[cus.numA][i] += cus.num;

                        numComplete++;

                        cusB[i] = null;
                    }

                    // 정비 창구 빈 자리에 기다리는 사람 넣기
                    if(cusB[i] == null && !qB.isEmpty()){
                        Customer cus = qB.poll(); // 기다리는 사람 가져오기

                        cus.endTime = tk + ptB[i]; // 끝나는 시간
                        cusB[i] = cus; // 현재 접수 창고에 있는 고객 번호 갱신
                    }
                }
                tk++;
            }

            sb.append("#").append(t).append(" ").append(sum[A][B] == 0 ? "-1" : sum[A][B]).append("\n");
        }
        System.out.println(sb.toString());
    }
}