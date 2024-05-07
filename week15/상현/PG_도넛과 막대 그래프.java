import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 생성된 정점 - 각 그래프 수의 합은 2보다 크기 때문에 나가기만 하는 간선 2개 이상을 가지는 정점이 생성된 정점 
 * 막대 - 들어오는 간선은 있지만 나가는 간선이 없음
 * 8자 - 들어오고 나가는 정점이 2개가 있는 정점이 존재
 * 도넛 - 나가는 갼선의 개수 - 막대,8자 그래프의 수
 */
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        HashMap<Integer, Integer> in = new HashMap<>();
        HashMap<Integer, Integer> out = new HashMap<>();

        for (int[] edge : edges) {
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
        }

        for (Integer i : out.keySet()) {
            if (out.get(i) > 1) {
                if (!in.containsKey(i)) answer[0] = i;

                if (in.containsKey(i) && in.get(i) > 1) {
                    answer[3] += 1;
                }
            }
        }
        for (Integer i : in.keySet()) {
            if (!out.containsKey(i)) answer[2] += 1;
        }

        answer[1] = out.get(answer[0]) - answer[2] - answer[3];
        return answer;
    }
}