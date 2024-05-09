/**
 * 57.23 ms
 * LinkedList
 * prev[], next[]로 LinkedList 구현
 * N 번 노드로 양 끝 표시
 * 삭제 : stack에 노드 번호와 prev, next 정보 저장 후
 * 노드의 prev와 next를 연결
 * 복원 : stack에서 노드를 꺼내 기존 prev, next와 재연결
 * 커서 이동은 삭제, 복원 직전에만 수행
 * */
public class PG81303_표편집 {
    private static final int RADIX = 10;
    private static final char U = 'U';
    private static final char D = 'D';
    private static final char C = 'C';
    private static final char Z = 'Z';
    private static final char O = 'O';
    private static final char X = 'X';
    
    public String solution(int n, int k, String[] cmd) {
        int i;
        int head;
        int move;
        int[] node;
        int[] prev;
        int[] next;
        int[][] stack;
        char[] ans;
        
        ans = new char[n]; // 정답 배열
        for (i = 0; i != n; i++) {
            ans[i] = O;
        }
        prev = new int[n + 1];
        next = new int[n + 1];
        for (i = 0; i <= n; i++) { // LinkedList
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        prev[0] = n; // N 번 노드로 양 끝 표시
        next[n] = 0;
        move = 0;
        head = 0;
        stack = new int[cmd.length][];
        for (String query : cmd) {
            switch (query.charAt(0)) {
            case U: // 위 이동
                move -= Integer.parseInt(query, 2, query.length(), RADIX); // 이동 수 저장
                break;
            case D: // 아래 이동
                move += Integer.parseInt(query, 2, query.length(), RADIX); // 이동 수 저장
                break;
            case C: // 삭제
                for (; move < 0; move++, k = prev[k]); // 누적된 이동 수행
                for (; move > 0; move--, k = next[k]);
                ans[k] = X; // 삭제 표시
                stack[head++] = new int[] {prev[k], k, next[k]}; // prev, 노드 번호, next 정보 stack에 저장
                prev[next[k]] = prev[k]; // prev와 next 연결
                next[prev[k]] = next[k];
                k = next[k] == n ? prev[k] : next[k]; // 커서가 마지막에 위치하면 위로 한 칸 이동
                break;
            case Z: // 복원
                for (; move < 0; move++, k = prev[k]); // 누적된 이동 수행
                for (; move > 0; move--, k = next[k]);
                node = stack[--head]; // stack에서 노드 꺼냄
                prev[node[2]] = node[1]; // 기존 prev, next와 재연결
                next[node[0]] = node[1];
                ans[node[1]] = O; // 복원 표시
            }
        }
        return new String(ans, 0, n);
    }
}