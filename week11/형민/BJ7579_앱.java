import java.io.*;
import java.util.*;

public class BJ7579_앱 {
    static int n, m;
    static Memory memorys[];

    static class Memory {
        int useCost, notUseCost;

        public Memory(int useCost, int notUseCost) {
            this.useCost = useCost;
            this.notUseCost = notUseCost;
        }

        public void setNotUseCost(int notUseCost) {
            this.notUseCost = notUseCost;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //활성화된 앱의 개수
        m = Integer.parseInt(st.nextToken()); //필요한 메모리

        memorys = new Memory[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            memorys[i] = new Memory(Integer.parseInt(st.nextToken()), 0);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            memorys[i].setNotUseCost(Integer.parseInt(st.nextToken()));
        }


        int dp[] = new int[m+1];
        
        for(int i=0;i<=m;i++) dp[i] = Integer.MAX_VALUE;
        
        //dp[j]: j 메모리를 확보하는 최소의 비용
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
		//j 메모리보다 현재 앱이 사용 중인 메모리가 적을 때
            	if(j > memorys[i].useCost) {
                	if(dp[j - memorys[i].useCost] == Integer.MAX_VALUE) continue; //j - memorys[i].useCost 메모리를 확보하는 비용이 없는 경우
                	else dp[j] = Integer.min(dp[j - memorys[i].useCost] + memorys[i].notUseCost, dp[j]);
                } else {
                	dp[j] = Integer.min(memorys[i].notUseCost, dp[j]);
                }
            }
        }


        System.out.println(dp[m]);
    }

}


