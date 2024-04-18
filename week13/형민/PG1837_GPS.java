import java.util.*;

class Solution {
    ArrayList<ArrayList<Integer>> g = new ArrayList<>();

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;

        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            g.get(edge_list[i][0]).add(edge_list[i][1]);
            g.get(edge_list[i][1]).add(edge_list[i][0]);
        }

        boolean flag2 = false;
        int x = gps_log[0]; //현재 위치
        for (int i = 1; i < k; i++) {
            int nx = gps_log[i]; //다음 위치

            boolean flag = false;
            flag2 = false;

            for (int j : g.get(x)) {
                //다음 위치로 갈 수 있는 경우
                if(x == nx || j == nx) {
                    flag = flag2 = true;
                    break;
                }
            }

            //다음 위치로 못 가는 경우
            if(!flag) {
                int cnt = 2;
                for (int j = i-1; j >= 0; j--) {
                    int v = bfs(gps_log[j], nx, cnt); //최소 이동 경로
                    // if(v == -1)  { //못 갈때
                    //     continue;
                    // }
                    if(v <= cnt) { //머물다 가는 것도 되니까 cnt보다만 작으면됨
                        flag2 = true;
                        break;
                    }

                    cnt++;
                }
                // if(v == -1) break;
                if(!flag2) break;
                answer += (cnt - 1); //최소 수정 횟수 갱신
            }

            x = nx;
        }

        if(!flag2) return -1;
        return answer;
    }

    class Node {
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    int bfs(int x, int nx, int cnt) {
        Deque<Node> q = new ArrayDeque<>();
        boolean visited[] = new boolean[201];

        q.add(new Node(x, 0));
        visited[x] = true;

        int result = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node cur = q.pollFirst();

            if(cur.x == nx) {
                result = cur.cost;
                break;
            }

            for(int i : g.get(cur.x)) {
                if(visited[i]) continue;
                if(cnt < cur.cost + 1) continue;
                visited[i] = true;
                q.addLast(new Node(i, cur.cost + 1));
            }
        }

        return result;
    }
}