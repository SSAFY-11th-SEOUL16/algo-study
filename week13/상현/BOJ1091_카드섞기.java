import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 구현
 * 초기 card 배열에 0 1 2 순서로 세팅
 * 만약 time이 0보다 크고 초기 세팅 상태로 된다면 종료
 * s 배열의 순서 대로 tmpCard에 지정
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        int[] p = new int[n];
        int[] s = new int[n];
        int[] cards = new int[n];
        int[] tmpCard = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
            cards[i] = i % 3;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }
        int time = 0;

        while (true) {
            //p배열과 순서가 같은지 확인
            int eqCnt = 0;
            //종료 조건을 위한 cnt 
            int brCnt = 0;
            for (int i = 0; i < n; i++) {
                if (cards[i] == p[i]) eqCnt++;
                if (cards[i] == i % 3) brCnt++;
            }

            if (eqCnt == n) {
                break;
            }
            if (brCnt == n && time > 0) {
                time = -1;
                break;
            }

            tmpCard = new int[n];
            for (int i = 0; i < n; i++) {
                //s 배열의 i번째 값을 cards의 위치에서 옮겨줌
                tmpCard[i] = cards[s[i]];
            }

            cards = tmpCard;
            time++;
        }
        System.out.println(time);
    }


}
