import java.io.*;
import java.util.*;

class Main {
    //DP[i][j][k]
    //i : 현재 발판 인덱스,  j : 왼발 현재 발판, k : 오른발 현재 발판
    static int[][][] DP;
    static ArrayList<Integer> list;	//밟는 발판 순서 저장 리스트
    static int size;
    
    //각 발판 이동할 때 드는 힘 저장 배열
    static int[][] width = { 
            {1, 2, 2, 2, 2}, 
            {0, 1, 3, 4, 3}, 
            {0, 3, 1, 3, 4}, 
            {0, 4, 3, 1, 3}, 
            {0, 3, 4, 3, 1}
    };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        list = new ArrayList<>();

        //발판 밟는 순서 저장
        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0)
                break;
            list.add(n);
        }

        size = list.size();
        DP = new int[size][5][5];
        int answer = search(0, 0, 0);		//DP[][][] 재귀를 이용한 탐색 진행
        bw.write(String.valueOf(answer));	//최소 힘 BufferedWriter 저장
        bw.flush();		//결과 출력
    }
    
    //재귀를 통해 DP[][][]를 구성하는 함수
    //idx : 현재 발판 인덱스, l : 왼발 , r : 오른발
    private static int search(int idx, int l, int r) {
        if(idx == size)	//발판 모두 밟았을 때
            return 0;

        if(DP[idx][l][r] != 0)	//이미 밟아본 발판일 경우
            return DP[idx][l][r];

        int nxt = list.get(idx);
        
        //search(idx+1, nxt, r) + width[l][nxt]) : 왼발로 다음 발판 밟기
        //search(idx+1, l, nxt) + width[r][nxt]) : 오른발로 다음 발판 밟기
        DP[idx][l][r] = Math.min(search(idx+1, nxt, r) + width[l][nxt],  search(idx+1, l, nxt) + width[r][nxt]);

        return DP[idx][l][r];
    }
}