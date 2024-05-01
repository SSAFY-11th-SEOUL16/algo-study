package algorithm.baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16562_친구비 {

    public static int find(int x, int[] parents){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x], parents);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            N : 학생 수
            k : 돈

            가장 적은 비용으로 모든 사람과 친구가 되는 방법

            친구 연결 그래프에서
            가장 적은 친구비를 가진 애와 친구 되기

            만약 전부 친구가 되었을 때 k원이 넘는다면 친구를 다 사귈 수 없음

            유니온 파인드
         */

        tokens = new StringTokenizer(br.readLine());

        int N, M, k;
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());

        int[] parents = new int[N+1];
        int[] minCost = new int[N+1];

        for (int i = 1; i <= N; i++) {
            minCost[i] = Integer.parseInt(tokens.nextToken());
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(tokens.nextToken());
            int w = Integer.parseInt(tokens.nextToken());

            int pv = find(v,parents);
            int pw = find(w,parents);

            if(pv == pw) continue;

            parents[pw] = pv; // pv를 root로 
            minCost[pv] = Math.min(minCost[pv],minCost[pw]); // 가장 적은 친구비 저장
            minCost[pw] = 0;
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += minCost[i];
        }

        System.out.println(sum <= k ? sum : "Oh no");
    }
}