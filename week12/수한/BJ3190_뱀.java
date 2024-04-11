package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_3190_뱀 {
    static class Coord{
        int x, y;
        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class ChangeDirInfo{
        int time;
        int dir;
        ChangeDirInfo(int time, int dir){
            this.time = time;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        boolean[][] isApple = new boolean[N][N];
        for (int i = 0; i < K; i++) {
            tokens = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(tokens.nextToken());
            int col = Integer.parseInt(tokens.nextToken());
            isApple[row-1][col-1] = true;
        }

        // 하 우 상 좌
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};

        Deque<Coord> snake = new ArrayDeque<>();
        snake.offer(new Coord(0,0));

        boolean[][] isSnake = new boolean[N][N];

        int L = Integer.parseInt(br.readLine());
        ChangeDirInfo[] changeDirInfos = new ChangeDirInfo[L+1];
        for (int i = 0; i < L; i++) {
            tokens = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(tokens.nextToken());
            char dir = tokens.nextToken().charAt(0);
            changeDirInfos[i] = new ChangeDirInfo(time,dir == 'L' ? 1 : 3);
        }
        changeDirInfos[L] = new ChangeDirInfo(10001,0);

        int curDir = 1;
        int curTime = 0;
        int changeDirIdx = 0;
        ChangeDirInfo changeDirInfo = changeDirInfos[0];
        while (true) {
            curTime++;

            Coord head = snake.peekLast();
            int nextX = head.x + dx[curDir];
            int nextY = head.y + dy[curDir];
            if(!isRange(nextX, nextY, N) || isSnake[nextX][nextY]){
                System.out.println(curTime);
                return;
            }
            isSnake[nextX][nextY] = true;
            snake.add(new Coord(nextX, nextY));
            if(isApple[nextX][nextY]){
                isApple[nextX][nextY] = false;
            }else {
                Coord tail = snake.poll();
                isSnake[tail.x][tail.y] = false;
            }

            if(curTime == changeDirInfo.time){
                curDir = (curDir+changeDirInfo.dir) % 4;
                changeDirInfo = changeDirInfos[++changeDirIdx];
            }
        }
    }

    static boolean isRange(int x, int y, int N){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}