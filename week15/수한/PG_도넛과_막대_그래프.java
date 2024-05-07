package algorithm.programmers;

import java.util.ArrayList;

public class Kakao_2024_겨울인턴십_그래프 {
    class Solution {

        public int[] solution(int[][] edges) {
            int[] answer = {0,0,0,0};

            ArrayList<Integer>[] graph = new ArrayList[1000001];

            for (int i = 1; i < 1000001; i++) {
                graph[i] = new ArrayList<>();
            }

            // 들어오는 간선이 있는지
            boolean[] hasInEdges = new boolean[1000001];
            // 정점 저장
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                hasInEdges[edge[1]] = true;
            }

            // 추가 정점 찾기
            int plusNode = 1;
            // 나가는 선이 2개 이상이고 들어오는 선이 없는 정점
            for (int i = 1; i < 1000001; i++) {
                if(graph[i].size() > 2 || (graph[i].size() == 2 && !hasInEdges[i])){
                    plusNode = i;
                    break;
                }
            }
            answer[0] = plusNode;

            // 추가 정점으로부터 나가는 선들에서 구성될 수 있는 모양 탐색
            boolean[] visited = new boolean[1000001];

            for (int num : graph[plusNode]) {
                while(true){
                    visited[num] = true;
                    if(graph[num].size() == 2){
                        answer[3]++;
                        break;
                    }else if (graph[num].size() == 1){
                        if(visited[num]){
                            answer[1]++;
                            break;
                        }
                        num = graph[num].get(0);
                    }else{
                        answer[2]++;
                        break;
                    }
                }
            }

            return answer;
        }
    }
    public static void main(String[] args) {
    }
}