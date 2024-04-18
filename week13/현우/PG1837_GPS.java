import java.util.ArrayDeque;

public class PG1837_GPS {
	
	/**
	 * 19.04 ms
	 * 0-1 BFS
	 * 도착점부터 역으로 레벨 그래프 생성
	 * 좌표 = (거점, 시간)
	 * 거리 = 오류 수정 횟수
	 * (출발점, 1) 부터 (도착점, K)까지의 최단 경로
	 * 이동할 때 거리 증가가 0 또는 1 -> 0-1 BFS
	 * 거점의 레벨이 남은 시간 이하인 경우만 도착 가능
	 */
	
    private static final int MAX_N = 200;
    private static final int INF = Integer.MAX_VALUE;
    
    private static final class Node {
    	int idx;
    	Node next;
    	
    	Node(int idx, Node next) {
    		this.idx = idx;
    		this.next = next;
    	}
    }
    
    private static int n;
    private static int[] level = new int[MAX_N + 1];
    private static ArrayDeque<Integer> q = new ArrayDeque<>();
    private static Node[] adj;
    
    private static void bfs(int end) {
    	int curr, i;
    	Node next;
    	
        for (i = 1; i <= n; i++) {
            level[i] = INF;
        }
    	level[end] = 0; // 도착점 레벨 0
    	q.addLast(end);
    	while (!q.isEmpty()) {
    		curr = q.pollFirst();
    		for (next = adj[curr]; next != null; next = next.next) {
    			if (level[next.idx] == INF) { // 레벨 설정 안된 거점
    				level[next.idx] = level[curr] + 1; // 현재 레벨 + 1
    				q.addLast(next.idx);
    			}
    		}
    	}
    }
    
    private static int bfs01(int k, int[] gpsLog) {
        int idx, next, time, dist;
        int[] curr;
        boolean[][] visited;
        ArrayDeque<int[]> dq;
        Node node;
        
        visited = new boolean[n + 1][k + 1];
        dq = new ArrayDeque<>();
        dq.addLast(new int[] {gpsLog[0], 1, 0}); // (출발점, 1)
        for (;;) {
            curr = dq.pollFirst();
            idx = curr[0]; // 거점 번호
            time = curr[1]; // 시간
            dist = curr[2]; // 거리 (오류 수정 횟수)
            if (time == k) { // 시간 K 도달
                return dist;
            }
            for (node = adj[idx]; node != null; node = node.next) {
                next = node.idx;
            	if (level[next] < k - time && !visited[next][time + 1]) { // 거점의 레벨이 남은 시간 이하
            		if (next == gpsLog[time]) { // 로그와 같은 경우 거리 증가 0
            			dq.addFirst(new int[] {next, time + 1, dist}); // 덱 앞에 삽입
                    } else { // 로그와 다른 경우 거리 증가 1
                        dq.addLast(new int[] {next, time + 1, dist + 1}); // 덱 뒤에 삽입
                    }
                    visited[next][time + 1] = true; // 방문 처리
            	}
            }
        }
    }
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        this.n = n;
        adj = new Node[n + 1];
        for (int[] edge : edge_list) { // 간선 입력
        	adj[edge[0]] = new Node(edge[1], adj[edge[0]]); // LinkedList
        	adj[edge[1]] = new Node(edge[0], adj[edge[1]]);
        }
        bfs(gps_log[k - 1]); // 레벨 그래프 생성
        if (level[gps_log[0]] >= k) { // 출발점의 레벨이 k 이상
        	return -1;
        }
        return bfs01(k, gps_log); // 0-1 BFS
    }
}