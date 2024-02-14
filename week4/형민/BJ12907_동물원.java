import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int arr[];
    static boolean visited[];
    static int result = 0, max = -1;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        Arrays.sort(arr);
        backtrack(-1, -1);
        System.out.println(result);
    }

    static void backtrack(int curIdx, int curValue) {
        //조합1을 만든 경우
        if (curValue == max) {
            boolean flag = false;
            int pre = -1;
            int cnt1 = 0, cnt2 = 0; //조합1의 동물 수, 조합2의 동물 수

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    if ((pre + 1) == arr[i]) {
                        cnt2++;
                        pre = arr[i];
                    }
                    else {
                        flag = true;
                        break;
                    }
                } else {
                    cnt1++;
                }
            }

            //조합1, 조합2 둘 다 가능한 조합인 경우
            if (!flag) {
                //조합1과 조합2의 수가 같다면 모든 경우를 구하기에 +1
                if(cnt1 == cnt2) result++;
                else result += 2;
            }
            return;
        }

        int pre = curValue;
        for (int i = curIdx + 1; i < n; i++) {
            if (!visited[i] && (pre + 1) == arr[i]) {
                visited[i] = true;
                backtrack(i, arr[i]);
                visited[i] = false;
            }
        }
    }
}
