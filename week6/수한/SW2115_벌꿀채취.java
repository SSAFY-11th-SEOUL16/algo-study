import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취
{
    static int N, M, C;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    static int[][] maxVal; // 2번째 사람이 i,j에서 채취했을 때의 최대 값

    /*
        x,y에서 오른쪽으로 M개 벌꿀을 남을 때 최대로 담을 수 있는 벌꿀 양 저장
        cnt : 처리한 벌꿀 개수
        cost : 수확한 벌꿀 양
        val : 수익
        sx, sy : 시작 벌꿀 위치
    */
    public static void comb(int cnt, int cost, int val, int sx, int sy){
        if(cost > C) return; // 최대 벌꿀 양을 초과한다면? return

        maxVal[sx][sy] = Math.max(maxVal[sx][sy], val); // val : 수익 

        if(cnt == M) return;

        // 현재 벌꿀 포함 X
        comb(cnt+1,cost, val, sx, sy);
        // 현재 벌꿀 포함 O
        comb(cnt+1,cost+map[sx][sy+cnt], val+map[sx][sy+cnt]*map[sx][sy+cnt], sx, sy);
    }

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());

        /*
            일꾼 2명

            N : 벌통 크기       // 3 ≤ N ≤ 10
            M : 꿀 채취 벌통 수  // 1 ≤ M ≤ 5
            가로 연속된 M개의 벌통 선택후 채취 가능

            하나의 벌통에서 채취한 꿀은 하나의 용기에 담아야함

            C : 채취가능한 꿀 최대 양 // 10 ≤ C ≤ 30
         */

        StringTokenizer tokens;

        for (int t = 1; t <= T; t++) {
            tokens = new StringTokenizer(br.readLine());

            N = Integer.parseInt(tokens.nextToken());
            M = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());

            map = new int[N][N];

            // 벌꿀 정보 저장
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            maxVal = new int[N][N-M+1]; // x, y에서 오른쪽으로 M만큼 벌통을 놓았을 때 가능한 최대 채취 양

            // 가능한 최대 채취 양 저장
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N-M+1; j++) {
                    comb(0,0,0,i,j);
                }
            }

            int answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N-M+1; j++) {
                    // 같은 라인
                    int val = 0;
                    for (int k = j+M; k < N-M+1; k++) {
                        val = Math.max(val, maxVal[i][k]);
                    }
                    
                    // 다른 라인
                    for (int k = i+1; k < N; k++) {
                        for (int l = 0; l < N-M+1; l++) {
                            val = Math.max(val, maxVal[k][l]);
                        }
                    }

                    answer = Math.max(answer, maxVal[i][j]+val);
                }
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}