import java.util.*;
import java.util.Map.Entry;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, int[]> exchangeCnts = new HashMap<>();
        
        for(int[] edge : edges) {
            int a = edge[0];
            int b= edge[1];
            
            if(!exchangeCnts.containsKey(a))
                exchangeCnts.put(a, new int[]{0, 0});
            if(!exchangeCnts.containsKey(b))
                exchangeCnts.put(b, new int[]{0, 0});
            
            exchangeCnts.get(a)[0]++;
            exchangeCnts.get(b)[1]++;
        }
        
        for(Entry<Integer, int[]> entry : exchangeCnts.entrySet()) {
            int key = entry.getKey();
            int[] exchangeCnt = entry.getValue();
            
            // 2번이상 주고받지 않고 받은 것만 있는 정점은 생성점
            if(exchangeCnt[0] >=2 && exchangeCnt[1] == 0)
                answer[0] = key;
            
            // 받은 것만 있는 정점의 수는 막대 그래프 수
            else if(exchangeCnt[0] == 0 && exchangeCnt[1] > 0) {
                answer[2]++;
                
            } 
            
            // 주고받은 횟수가 각각 2번 이상인 정점의 수는 8자 그래프 수
            else if(exchangeCnt[0] >= 2 && exchangeCnt[1] >= 2)
                answer[3]++;
        }
        // 도넛 그래프의 수는 생성점의 주는 것 중 2종류의 그래프 수를 제외한 수
        answer[1] = exchangeCnts.get(answer[0])[0] - answer[2] - answer[3];
        return answer;
    }
}