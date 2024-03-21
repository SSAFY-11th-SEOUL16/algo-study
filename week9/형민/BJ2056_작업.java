import java.io.*;
import java.util.*;

public class BJ2056_작업 {
    static int n;
    static ArrayList<ArrayList<Integer>> al = new ArrayList<>();
    static int indegree[], processTime[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        indegree = new int[n + 1]; //차수
        processTime = new int[n + 1]; //작업 시간

        for (int i = 0; i <= n; i++) al.add(new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            processTime[i] = time;

            int m = Integer.parseInt(st.nextToken()); //선행작업 개수
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                al.get(num).add(i);
                indegree[i]++;
            }
        }

        int cnt = 0; //끝낸 작업의 수
        int time = 0; //현재 시간
        while(true){
            for (int i = n; i >= 1; i--) {
            	//가능한 작업인 경우
                if (indegree[i] == 0) {
                    processTime[i]--;
                    if (processTime[i] == 0) {
                        indegree[i] = -1;
                        cnt++;
                        for (int j : al.get(i)) indegree[j]--;
                    }
                }
            }
            time++;

            //모든 작업을 끝낸 경우
            if(cnt == n) break;
        }

        System.out.println(time);
    }
}
