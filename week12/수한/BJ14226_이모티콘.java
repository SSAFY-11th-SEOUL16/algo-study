package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14226_이모티콘 {

    static class Sticker{
        int num;
        int numCopy;
        int cnt;
        Sticker(int num, int numCopy, int cnt){
            this.num = num;
            this.numCopy = numCopy;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            이모티콘 S개 보내려고 함
            이미 1개 입력

            1. 화면 이모티콘 복사
            2. 붙여넣기
            3. 1개 삭제
         */

        int S = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[S+1][S+1];
        Queue<Sticker> q = new ArrayDeque<>();
        visited[1][0] = true;
        q.add(new Sticker(1,0,0));

        while(!q.isEmpty()){
            Sticker sticker = q.poll();

            if(sticker.num == S){
                System.out.println(sticker.cnt);
                break;
            }

            // 복사하기
            if(!visited[sticker.num][sticker.num]){
                visited[sticker.num][sticker.num] = true;
                q.add(new Sticker(sticker.num,sticker.num,sticker.cnt+1));
            }

            // 붙혀넣기
            int nextNum = sticker.num+sticker.numCopy;
            if(nextNum <= S && !visited[nextNum][sticker.numCopy]){
            	visited[nextNum][sticker.numCopy] = true;
                q.add(new Sticker(nextNum,sticker.numCopy,sticker.cnt+1));
            }

            // 1개 삭제하기
            nextNum = sticker.num-1;
            if(nextNum >= 0 && !visited[nextNum][sticker.numCopy]){
                visited[nextNum][sticker.numCopy] = true;
                q.add(new Sticker(nextNum,sticker.numCopy,sticker.cnt+1));
            }
        }

    }
}