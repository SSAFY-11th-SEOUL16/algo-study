/**
 * 24.29 ms
 * 그래프
 * 생성한 정점
 * = 진입 간선 존재 X & 진출 차수 > 1인 정점
 * 막대 모양 그래프 수
 * = 진입 간선 존재 & 진출 차수 0인 정점 수
 * 8자 모양 그래프 수
 * = 진입 간선 존재 & 진출 차수 > 1인 정점 수
 * 도넛 모양 그래프 수
 * = 생성 정점 진출 차수 - 막대 수 - 8자 수
 * */
public class PG258711_도넛과막대그래프 {
    private static final int SIZE = 1000001;
    private static final int MADE = 0;
    private static final int DONUT = 1;
    private static final int STICK = 2;
    private static final int FIGURE_8 = 3;
    private static final int NUM = 4;
    
    public int[] solution(int[][] edges) {
        int i;
        int max;
        int[] result;
        int[] outDegree;
        boolean[] in;
        
        max = 0;
        in = new boolean[SIZE];
        outDegree = new int[SIZE];
        for (int[] edge : edges) {
            outDegree[edge[0]]++; // 진출 차수 계산
            in[edge[1]] = true; // 진입 간선 존재 여부
            max = Math.max(max, Math.max(edge[0], edge[1])); // 가장 큰 정점 번호
        }
        result = new int[NUM];
        for (i = 1; i <= max; i++) { // 모든 정점
            if (outDegree[i] > 1) { // 진출 차수 > 1
                if (in[i]) { // 진입 간선 존재
                    result[FIGURE_8]++; // 8자 모양 그래프
                } else { // 진입 간선 존재 X
                    result[MADE] = i; // 생성한 정점
                }
            } else if (in[i] && outDegree[i] == 0) { // 진입 간선 존재 & 진출 차수 0
                result[STICK]++; // 막대 모양 그래프
            }
        } // 도넛 모양 그래프 = 생성 정점 진출 차수 - 막대 수 - 8자 수
        result[DONUT] = outDegree[result[MADE]] - result[STICK] - result[FIGURE_8];
        return result;
    }
}