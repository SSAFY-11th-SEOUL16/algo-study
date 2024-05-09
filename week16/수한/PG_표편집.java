import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmds) {
        char[] answer = new char[n];
        Arrays.fill(answer,'O');

        int[][] link = new int[n][2];
        
        for(int i=0; i<n; i++){
            link[i][0] = i-1;
            link[i][1] = i+1;
        }

        Stack<Integer> removed = new Stack<>();
        
        String op;
        int x;
        int bef, aft;
        for(String cmd : cmds){
            String[] opAndParam = cmd.split(" ");
            op = opAndParam[0];
            
            switch(op){
                case "U":
                    // 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
                    x = Integer.parseInt(opAndParam[1]);
                    while(x > 0){   
                        k = link[k][0];
                        x--;
                    } 
                    break;
                case "D":
                    // 재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
                    x = Integer.parseInt(opAndParam[1]);
                    while(x > 0){   
                        k = link[k][1];
                        x--;
                    } 
                    break;
                case "C":
                    // 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 
                    // 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
                    answer[k] = 'X';
                    removed.push(k);

                    bef = link[k][0];
                    aft = link[k][1];
                    if(bef != -1) link[bef][1] = aft;
                    if(aft != n) link[aft][0] = bef;
                    k = (aft != n) ? aft : bef;
                    break;
                case "Z":
                    // 가장 최근에 삭제된 행을 원래대로 복구합니다. 
                    // 단, 현재 선택된 행은 바뀌지 않습니다.
                    int idx = removed.pop();
                    answer[idx] = 'O';

                    bef = link[idx][0];
                    aft = link[idx][1];
                    if(bef != -1) link[bef][1] = idx;
                    if(aft != n) link[aft][0] = idx;
                    break;
            }
        }
        
        return String.valueOf(answer);
    }
}