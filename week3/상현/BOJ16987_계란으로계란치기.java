package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987_계란으로계란치기 {
    private static int result;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Egg[] eggs = new Egg[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), true);
        }

        backTrack(0,0,eggs, N);
        System.out.println(result);
    }

    //L = depth / breakCount = 깨진 계란의 갯수 / eggs 계란 정보
    private static void backTrack(int L, int breakCount, Egg[] eggs, int N) {
        //N까지 확인했거나 깨진 계란의 수가 N - 1개 즉 한개 남았을 경우 종료
        if (L == N || breakCount == N - 1) {
            result = Math.max(breakCount, result);
            return;
        }

        //이미 깨진 계란일 경우 넘어감
        if (!eggs[L].isLive) {
            backTrack(L + 1, breakCount, eggs, N);
            return;
        }

        //랜덤하게 가기 때문에 자신을 내리치는 것을 제외한 N - 1번만큼 돈다
        for (int i = 0; i < N; i++) {
            //자기 자신이거나 이미 깨진 계란은 패스한다.
            if (i == L || !eggs[i].isLive) {
                continue;
            }

            //내리침
            eggs[L].negu -= eggs[i].weight;
            eggs[i].negu -= eggs[L].weight;

            //내리친 후 케이스 별로
            if (eggs[L].negu <= 0 && eggs[i].negu <= 0) { //둘 다 깨진 경우
                eggs[L].isLive = false;
                eggs[i].isLive = false;

                backTrack(L + 1, breakCount + 2, eggs, N);

                eggs[L].isLive = true;
                eggs[i].isLive = true;
            } else if (eggs[L].negu <= 0) {  //먼저 든 계란이 깨진 경우
                eggs[L].isLive = false;
                backTrack(L + 1, breakCount + 1, eggs, N);
                eggs[L].isLive = true;
            } else if (eggs[i].negu <= 0) {  //친 계란이 깨진 경우
                eggs[i].isLive = false;
                backTrack(L + 1, breakCount + 1, eggs, N);
                eggs[i].isLive = true;
            } else {  //둘 다 안깨진 경우
                backTrack(L + 1, breakCount, eggs, N);
            }
            eggs[L].negu += eggs[i].weight;
            eggs[i].negu += eggs[L].weight;

        }
    }

    private static class Egg {
        int negu;
        int weight;

        boolean isLive;

        public Egg(int negu, int weight, boolean isLive) {
            this.negu = negu;
            this.weight = weight;
            this.isLive = isLive;
        }
    }
}
