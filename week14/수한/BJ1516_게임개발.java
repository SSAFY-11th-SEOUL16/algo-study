package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1516_게임개발 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine());

        List<Integer>[] nextWorks = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            nextWorks[i] = new ArrayList<>();
        }

        int[] degrees = new int[N+1];
        int[] times = new int[N+1];
        int[] preMaxTimes = new int[N+1];
        for (int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(br.readLine());

            times[i] = Integer.parseInt(tokens.nextToken());

            while(true){
                int work = Integer.parseInt(tokens.nextToken());

                if(work == -1) break;

                nextWorks[work].add(i);
                degrees[i]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <=N; i++) {
            if(degrees[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()){
            int work = q.poll();

            int curTime = times[work] + preMaxTimes[work];

            for(int nextWork : nextWorks[work]){
                degrees[nextWork]--;
                preMaxTimes[nextWork] = Math.max(preMaxTimes[nextWork],curTime);
                if(degrees[nextWork] == 0){
                    q.offer(nextWork);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(times[i] + preMaxTimes[i]).append("\n");
        }
        System.out.println(sb);
    }
}