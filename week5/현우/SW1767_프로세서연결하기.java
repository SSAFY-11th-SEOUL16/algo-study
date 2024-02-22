import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SW1767_프로세서연결하기 {
	
	/*
	 * 139 ms
	 * 테두리에 있는 코어 탐색X
	 * 테두리 옆 코어는 테두리에 없으면 길이 1
	 * 가능한 전선 저장, 교차 관계 계산
	 * DFS로 전선을 선택하면서 최소 길이 계산
	 */
	
    private static final int INF = Integer.MAX_VALUE;
    private static final int SIZE = 14;
    private static final char POWER = '\0';
    private static final char CORE = '1';
    private static final char EMPTY = '0';

    private static int max, ans;
    private static int[] len;
    private static char[] map;
    private static boolean[] visited;
    private static LinkedList<Integer> cores, temp;
    private static ArrayList<LinkedList<Character>> lines, cross;
    
    private static void select(int core, int cnt, int sum) {
        if (core == cores.size()) { // 탐색 완료
            if (cnt > max) {
                max = cnt;
                ans = sum;
            } else if (cnt == max) {
                ans = Math.min(ans, sum);
            }
            return;
        }
        loop:
        for (char line : lines.get(core)) { // 현재 코어에 연결할 전선 선택
            for (char prev : cross.get(line)) {
                if (visited[prev]) { // 이전에 선택된 전선과 교차하면
                    continue loop; // 다른 전선 탐색
                }
            }
            visited[line] = true;
            select(core + 1, cnt + 1, sum + len[line]); // 다음 코어로 이동
            visited[line] = false;
        }
        select(core + 1, cnt, sum); // 현재 코어에 전선 연결 안함
    }
     
    private static void connect() {
        int curr, size, num, i;
        char idx;
        LinkedList<Character> list;
         
        size = cores.size() * 4 + 1;
        lines = new ArrayList<>(); // 각 코어의 연결 가능한 전선들 저장
        len = new int[size];
        idx = 0;
        for (int core : cores) { // 가로 전선들 저장
            list = new LinkedList<>();
            idx++;
            curr = core - 1; // 왼쪽 방향 탐색
            for (i = 0; map[curr] != CORE && map[curr] != POWER; i++) {
                map[curr] = idx;
                curr--;
            }
            if (map[curr] == POWER) { // 전원에 도달하면 전선 저장
                len[idx] = i;
                list.add(idx);
            }
            idx++;
            curr = core + 1; // 오른쪽 방향 탐색
            for (i = 0; map[curr] != CORE && map[curr] != POWER; i++) {
                map[curr] = idx;
                curr++;
            }
            if (map[curr] == POWER) { // 전원에 도달하면 전선 저장
                len[idx] = i;
                list.add(idx);
            }
            lines.add(list);
        }
        cross = new ArrayList<>();
        for (i = 0; i < size; i++) {
            cross.add(new LinkedList<>());
        }
        num = 0;
        for (int core : cores) { // 세로 전선들 저장
            list = lines.get(num++);
            idx++;
            curr = core - SIZE; // 위쪽 방향 탐색
            for (i = 0; map[curr] != CORE && map[curr] != POWER; i++) {
                if (map[curr] != EMPTY) { // 가로 전선과 만나면 교차 저장
                    cross.get(map[curr]).add(idx);
                    cross.get(idx).add(map[curr]);
                }
                curr -= SIZE;
            }
            if (map[curr] == POWER) { // 전원에 도달하면 전선 저장
                len[idx] = i;
                list.add(idx);
            }
            idx++;
            curr = core + SIZE; // 아래쪽 방향 탐색
            for (i = 0; map[curr] != CORE && map[curr] != POWER; i++) {
                if (map[curr] != EMPTY) { // 가로 전선과 만나면 교차 저장
                    cross.get(map[curr]).add(idx);
                    cross.get(idx).add(map[curr]);
                }
                curr += SIZE;
            }
            if (map[curr] == POWER) { // 전원에 도달하면 전선 저장
                len[idx] = i;
                list.add(idx);
            }
        }
        visited = new boolean[size]; // DFS에서 사용할 방문 배열
    }
     
    private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
        int n, pos, add, x, y, i, j;
         
        n = Integer.parseInt(br.readLine());
        for (i = 1; i <= n; i++) {
            map[(n + 1) * SIZE + i] = POWER;
            map[i * SIZE + n + 1] = POWER;
        }
        cores = new LinkedList<>(); // 탐색할 코어
        temp = new LinkedList<>(); // 테두리 옆 코어 임시 저장
        for (i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (j = 1; j <= n; j++) {
                pos = i * SIZE + j;
                map[pos] = st.nextToken().charAt(0);
                if (map[pos] == CORE && i != 1 && i != n && j != 1 && j != n) { // 테두리가 아니면
                    if (i == 2 || i == n - 1 || j == 2 || j == n - 1) { // 테두리 한 칸 옆이면
                        temp.add(pos);
                    } else {
                        cores.add(pos);
                    }
                }
            }
        }
        add = 0;
        for (int core : temp) { // temp에 저장한 코어들 옆 테두리가 비어있는지 확인
            x = core / SIZE;
            y = core % SIZE;
            if ((x == 2 && map[core - SIZE] == EMPTY) || (x == n - 1 && map[core + SIZE] == EMPTY) || (y == 2 && map[core - 1] == EMPTY) || (y == n - 1 && map[core + 1] == EMPTY)) {
                add++; // 비어있으면 정답 + 1
            } else {
                cores.add(core); // 막혀있으면 탐색할 코어에 추가
            }
        }
        connect(); // 전선 계산
        max = 0;
        ans = INF;
        select(0, 0, 0); // DFS
        return ans + add;
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
         
        int t, testCase;
         
        map = new char[SIZE * SIZE];
        t = Integer.parseInt(br.readLine());
        for (testCase = 1; testCase <= t; testCase++) {
            sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
        }
        System.out.print(sb);
    }
}