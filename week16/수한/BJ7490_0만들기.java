package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7490_0만들기 {
    static char[] ops = {' ','+','-'};
    static char[] selectedOps;
    static StringBuilder resSb = new StringBuilder();

    static int N;
    static public void dfs(int cnt, int result, int curNum, int curOp, char[] formula){
        if(cnt+1 == N){
            result += curOp == '+' ? curNum : -curNum;

            if(result == 0){
                resSb.append(String.valueOf(formula)).append("\n");
            }
            return;
        }

        int num = cnt+2;
        int numIdx = 2*(num-1);

        formula[numIdx] = (char)('0'+num);

        for (int i = 0; i < 3; i++) {
            formula[numIdx-1] = ops[i];
            if(i == 0){
                dfs(cnt+1,result,curNum * 10 + num,curOp,formula);
            }else{
                dfs(cnt+1,result + (curOp == '+' ? curNum : -curNum),num,ops[i],formula);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
            1~N 오름차순 숫자
            '+'(43), '-'(45), ' '(32) 숫자 사이에 삽입

            수식 계산 후 0이 될 수 있는지

            N <= 9

            수식 최대 8개
            중복 허용 순열 3^8 (ok)
         */

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            selectedOps = new char[N];

            char[] formula = new char[2*N-1];
            formula[0] = '1';

            dfs(0,0,1,'+',formula);

            resSb.append("\n");
        }

        System.out.println(resSb);
    }
}