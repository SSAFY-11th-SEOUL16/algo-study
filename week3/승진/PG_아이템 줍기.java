import java.util.*;

public class Solution {
    static class Node {
        int x, y, cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = Integer.MAX_VALUE;
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] maps = new int[102][102];

        List<int[]> tmp = new ArrayList<>();
        for (int[] r : rectangle) {
            tmp.add(new int[]{r[0] * 2, r[1] * 2, r[2] * 2, r[3] * 2});
        }
        rectangle = tmp.toArray(new int[0][]);

        for (int[] r : rectangle) {
            for (int i = r[0] + 1; i < r[2]; i++) {
                for (int j = r[1] + 1; j < r[3]; j++) {
                    maps[i][j] = 2;
                }
            }

            for (int i = r[0]; i <= r[2]; i++) {
                if (maps[i][r[1]] != 2) {
                    maps[i][r[1]] = 1;
                }
                if (maps[i][r[3]] != 2) {
                    maps[i][r[3]] = 1;
                }
            }

            for (int i = r[1]; i <= r[3]; i++) {
                if (maps[r[0]][i] != 2) {
                    maps[r[0]][i] = 1;
                }
                if (maps[r[2]][i] != 2) {
                    maps[r[2]][i] = 1;
                }
            }
        }

        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(characterX * 2, characterY * 2, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int cnt = node.cnt;

            if (x == itemX * 2 && y == itemY * 2) {
                answer = Math.min(answer, cnt);
                continue;
            }
            maps[x][y] = 2;

            for (int[] move : moves) {
                int nx = x + move[0];
                int ny = y + move[1];
                if (maps[nx][ny] == 1) {
                    q.offer(new Node(nx, ny, cnt + 1));
                }
            }
        }

        return answer / 2;
    }

    public static void main(String[] args) {
        int[][] rectangle = {{1, 1, 7, 4}};
        int characterX = 1;
        int characterY = 1;
        int itemX = 7;
        int itemY = 4;

        int result = solution(rectangle, characterX, characterY, itemX, itemY);
        System.out.println(result);
    }
}
