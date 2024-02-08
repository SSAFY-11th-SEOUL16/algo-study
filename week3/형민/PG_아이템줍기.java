import java.util.*;

class Solution {
    int result = Integer.MAX_VALUE;
    int arr[][];
    int targetX, targetY;
    int SIZE = 110;

    class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        targetX = itemX * 2;
        targetY = itemY * 2;
        characterX *= 2;
        characterY *= 2;

        arr = new int[SIZE][SIZE];

        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;

            //테두리 그리기
            for (int j = x1; j <= x2; j++) {
                for (int k = y1; k <= y2; k++) {
                    //테두리인 경우
                    if ((j == x1 || j == x2 || k == y1 || k == y2) && arr[j][k] != 2) {
                        arr[j][k] = 1;
                    } else { //사각형 내부인 경우
                        arr[j][k] = 2;
                    }
                }
            }
        }

        arr[targetX][targetY] = 1;
        bfs(characterX, characterY);

        return result / 2;
    }

    int dx[] = {0, 0, -1, 1};
    int dy[] = {-1, 1, 0, 0};

    void bfs(int x, int y) {
        Deque<Node> q = new ArrayDeque<>();
        q.addLast(new Node(x, y, 0));

        while (!q.isEmpty()) {
            Node cur = q.pollFirst();
            if (cur.x == targetX && cur.y == targetY) {
                result = cur.cnt;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                //범위 초과
                if (nx < 0 || ny < 0 || nx >= SIZE || ny >= SIZE) continue;

                //테두리가 아닐 경우
                if (arr[nx][ny] != 1) continue;

                arr[nx][ny] = 0;
                q.addLast(new Node(nx, ny, cur.cnt + 1));
            }
        }
    }
}
