import java.util.*;

class PG_도넛과막대그래프 {
    static int[] in = new int[1000001];
    static int[] out = new int[1000001];
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        for(int[] edge: edges) {
            in[edge[1]]++;
            out[edge[0]]++;
        }
        
         for(int i=1; i<=1000000; i++) {
            if(in[i] == 0 && out[i] >= 2) {
                answer[0] = i;
            } else if (in[i] > 0 && out[i] == 0) {
                answer[2]++;
            } else if (in[i] >= 2 && out[i] == 2) {
                answer[3]++;
            }
        }
        int cnt = out[answer[0]];
        answer[1] = cnt - answer[2] - answer[3];
        return answer;
    }
    
}
