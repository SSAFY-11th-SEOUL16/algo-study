import java.io.*;
import java.util.*;

public class BJ18427_함께블록쌓기 {
    static int n, m, h;
    static ArrayList<ArrayList<Integer>> al;
    static int dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //학생 수
        m = Integer.parseInt(st.nextToken()); //최대 m개 블록
        h = Integer.parseInt(st.nextToken()); //높이
        al = new ArrayList<>();

        dp = new int[n + 1][h + 1];

        for (int i = 0; i <= n; i++) {
            al.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                al.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        //dp[i][j] : i번의 학생까지 고려했을 때 j 높이를 만드는 경우의 수
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= h; j++) {
            	//i번째 학생 블록 쓰는 경우
            	for(int k : al.get(i)) {
                    if(j > k) { //조합해서 만들 수 있을 때
                        if(dp[i-1][j-k] > 0) {
                            dp[i][j] += dp[i-1][j-k];
                        }
                    } else if(j == k) { //1개 만들어질 떄
                        dp[i][j] += 1;
                    }
                }
            	
                //i번째 학생 블록을 쓰지 않는 경우
                dp[i][j] += dp[i-1][j];

                dp[i][j] %= 10007;
            }
        }

        System.out.println(dp[n][h]);
    }
}


