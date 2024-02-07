import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1497_기타콘서트 {

    //곡의 개수를 연주하는데 필요한 기타 수 저장 배열 EX)3번째에 1이 들어가있다면 3곡을 연주하는데 1개의 기타만 필요하다는 의미
    private static int[] countArr;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] info = new boolean[N][M];

        int min = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String possible = st.nextToken();

            for (int j = 0; j < M; j++) {
                if (possible.charAt(j) == 'Y') {
                    if (min == -1) {
                        min = 1;
                    }
                    info[i][j] = true;
                }
            }
        }

        //하나도 연주 못하면 -1
        if (min == -1) {
            System.out.println(min);
            return;
        }

        //최소값을 구해야 하므로 IMV로 초기화
        countArr = new int[M + 1];
        Arrays.fill(countArr, Integer.MAX_VALUE);

        //조합 별로 구함
        for (int i = 1; i <= N; i++) {
            dfs(0,0,new int[i], i, new boolean[N], info);
        }

        for (int i = M; i > 0 ; i--) {
            if (countArr[i] == Integer.MAX_VALUE) {
                continue;
            }
            System.out.println(countArr[i]);
            break;
        }
    }

    /**
     * @param L         - depth
     * @param idx       - 조합 다음 idx
     * @param now       - 현재 기타 조합 idx ex) now에 1,2가 있다면 입력받은 기타의 1,2번을 조합한 상태
     * @param MAX       - 조합 개수를 의미 1이면 nC1을 의미
     * @param check     - 현재 now에 들어있는지 없는지 체크 배열
     * @param songInfo  - 기타가 어떤 곡들을 연주하는지 정보
     */
    private static void dfs(int L, int idx, int[] now, int MAX, boolean[] check, boolean[][] songInfo) {
        if (L == MAX) {
            int count = 0;
            boolean[] ch = new boolean[songInfo[0].length];
            //조합한 기타들로 몇곡을 연주할 수 있는지 확인
            for (int i = 0; i < MAX; i++) {
                for (int j = 0; j < songInfo[0].length; j++) {
                    //ch가 true라면 이미 다른 기타가 연주 가능한것
                    if (songInfo[now[i]][j] && !ch[j]) {
                        ch[j] = true;
                        count += 1;
                    }
                }
            }

            countArr[count] = Math.min(countArr[count], MAX);
            return;
        }

        for (int i = idx; i < check.length; i++) {
            if (check[i]) {
                continue;
            }
            now[L] = i;
            check[i] = true;
            dfs(L + 1, i + 1,now, MAX, check, songInfo);
            check[i] = false;
        }
    }

}
