import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SW2112_보호_필름 {
    static int D, W, K;
    static int[][] map;
    static int[][] copy;
    static int result = 0;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            map = new int[D][W];
            copy = new int[D][W];
            result = D;
 
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    copy[i][j] = map[i][j];
                }
            }
            if (checkCell()) {
                System.out.println("#" + test_case + " " + 0);
                continue;
            } else {
                makeCell(0, 0);
                System.out.println("#" + test_case + " " + result);
            }
        }
    }
 
    private static boolean checkCell() {
        for (int i = 0; i < W; i++) {
            int cnt = 1;
            int prev = copy[0][i];
            int max = 0;
 
            for (int j = 1; j < D; j++) {
                if (prev == copy[j][i])
                    cnt++;
                else
                    cnt = 1;
                prev = copy[j][i];
                max = Math.max(max, cnt);
            }
            if (max < K)
                return false;
        }
        return true;
    }
 
    private static void makeCell(int count, int index) {
        if (count >= result)
            return;
        if (index == D) {
            if (checkCell()) {
                result = Math.min(result, count);
            }
            return;
        }
 
        makeCell(count, index + 1);
 
        for (int i = 0; i < W; i++) {
            copy[index][i] = 0;
        }
        makeCell(count + 1, index + 1);
 
        for (int i = 0; i < W; i++) {
            copy[index][i] = 1;
        }
        makeCell(count + 1, index + 1);
 
        for (int i = 0; i < W; i++) {
            copy[index][i] = map[index][i];
        }
    }
}