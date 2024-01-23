import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int answer = 0;
        int MAX_LOC = 100000;

        boolean[] visited = new boolean[MAX_LOC+1];

        if(N < K){
            Deque<int[]> q = new ArrayDeque<>();
            q.add(new int[]{N,0});

            while(!q.isEmpty()){
                int[] info = q.poll();
                int idx = info[0];
                int cnt = info[1];

                if(idx == K){
                    answer = cnt;
                    break;
                }

                if(visited[idx]) continue;
                visited[idx] = true;

                int rightIdx = idx+1;
                if(rightIdx <= MAX_LOC && !visited[rightIdx]) q.add(new int[]{rightIdx,cnt+1});

                int leftIdx = idx-1;
                if(leftIdx >= 0 && !visited[leftIdx]) q.add(new int[]{leftIdx,cnt+1});

                int teleportIdx = idx * 2;
                if(teleportIdx <= MAX_LOC && !visited[teleportIdx]) q.addFirst(new int[]{teleportIdx,cnt});
            }
        }else{
            answer = N - K;
        }

        System.out.println(answer);
    }
}