import java.util.*;

public class Solution {
    static class Pair {
        int x, y, z, cnt;

        Pair(int x, int y, int z, int cnt) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.cnt = cnt;
        }
    }

    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] change = {
            {}, {5, 3, 0, 5}, {3, 5, 1, 5}, {2, 5, 5, 1}, {5, 2, 5, 0}, {5, 5, 5, 5}
    };

    static int solve(int[][] maps, int N) {
        int max_cnt = 0;
        Map<Integer, List<int[]>> hole = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (maps[i][j] >= 6 && maps[i][j] <= 10) {
                    hole.computeIfAbsent(maps[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (maps[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int cnt = bfs(maps, N, i, j, k, hole);
                        max_cnt = Math.max(max_cnt, cnt);
                    }
                }
            }
        }
        return max_cnt;
    }

    static int bfs(int[][] maps, int N, int startX, int startY, int startZ, Map<Integer, List<int[]>> hole) {
        Deque<Pair> q = new ArrayDeque<>();
        q.push(new Pair(startX, startY, startZ, 0));

        while (!q.isEmpty()) {
            Pair pair = q.pop();
            int x = pair.x;
            int y = pair.y;
            int z = pair.z;
            int cnt = pair.cnt;

            while (true) {
                x += moves[z][0];
                y += moves[z][1];

                if (x == startX && y == startY) break;
                if (maps[x][y] == 5 || maps[x][y] == -1) break;
                if (maps[x][y] >= 1 && maps[x][y] < 5) {
                    z = change[maps[x][y]][z];
                    if (z == 5) {
                        cnt *= 2;
                        cnt++;
                        break;
                    }
                    cnt++;
                } else if (maps[x][y] >= 6 && maps[x][y] <= 10) {
                    List<int[]> tmp = hole.get(maps[x][y]);
                    for (int[] arr : tmp) {
                        if (arr[0] != x || arr[1] != y) {
                            x = arr[0];
                            y = arr[1];
                            break;
                        }
                    }
                }
            }

            if (maps[x][y] == 5) {
                cnt *= 2;
                cnt++;
            }

            return cnt;
        }

        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(scanner.nextLine().trim());
            int[][] maps = new int[N + 2][N + 2];
            for (int i = 0; i <= N + 1; i++) {
                Arrays.fill(maps[i], 5);
            }

            for (int i = 1; i <= N; i++) {
                String[] input = scanner.nextLine().split(" ");
                for (int j = 1; j <= N; j++) {
                    maps[i][j] = Integer.parseInt(input[j - 1]);
                }
            }

            int result = solve(maps, N);
            System.out.println("#" + test_case + " " + result);
        }
    }
}
