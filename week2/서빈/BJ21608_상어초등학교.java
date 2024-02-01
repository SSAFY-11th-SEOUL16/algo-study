import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ21608_상어초등학교 {
    static int map[][];

    static HashMap<Integer, Integer[]> hashMap = new HashMap<>();
    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int[] s = new int[5];
            for (int j = 0; j < 5; j++) {
                s[j] = Integer.parseInt(st.nextToken());
            }
            hashMap.put(s[0], new Integer[]{s[1], s[2], s[3], s[4]});
            putStudent(s[0]);
        }

        int sum = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int count = 0;
                Integer friends[] = hashMap.get(map[i][j]);
                for (int k = 0; k < 4; k++) {
                    int nextY = i + dy[k];
                    int nextX = j + dx[k];
                    if (nextY < 0 || nextX < 0 || nextY >= map.length || nextX >= map.length)
                        continue;
                    int now = map[nextY][nextX];

                    for (int p = 0; p < 4; p++)
                        if (now == friends[p]) count++;
                }

                switch (count) {
                    case 1:
                        sum += 1;
                        break;
                    case 2:
                        sum += 10;
                        break;
                    case 3:
                        sum += 100;
                        break;
                    case 4:
                        sum += 1000;
                        break;
                }
            }
        }
        System.out.println(sum);
    }

    public static void putStudent(int student) {
        Integer friends[] = hashMap.get(student);
        int f1 = friends[0];
        int f2 = friends[1];
        int f3 = friends[2];
        int f4 = friends[3];

        ArrayList<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int friendsCount = 0;
                int emptyCount = 0;
                for (int k = 0; k < 4; k++) {
                    int nextY = i + dy[k];
                    int nextX = j + dx[k];
                    if (nextY < 0 || nextX < 0 || nextY >= map.length || nextX >= map.length)
                        continue;
                    int now = map[nextY][nextX];
                    if (now == f1 || now == f2 || now == f3 || now == f4)
                        friendsCount++;
                    if (now == 0)
                        emptyCount++;
                }
                list.add(new Integer[]{friendsCount, emptyCount, i, j});
            }
        }

        for (int i = 0; i < list.size(); i++) {
            int y = list.get(i)[2];
            int x = list.get(i)[3];
            if (map[y][x] == 0) {
                map[y][x] = student;
                return;
            }
        }
    }
}