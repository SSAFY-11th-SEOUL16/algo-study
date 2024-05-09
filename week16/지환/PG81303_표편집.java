import java.util.*;

class PG81303_표편집 {
    public String solution(int n, int k, String[] cmd) {
        //C명령어일때 넣어주는 스택
        Stack<Integer> delete = new Stack<>();
        int size = n;
        int point = k;
        int move;
        for(String c: cmd) {
            String[] command = c.split(" ");
            if(command[0].equals("U")) {
                move = Integer.parseInt(command[1]);
                point = (point < move) ? 0 : point-move;
            } else if(command[0].equals("D")) {
                move = Integer.parseInt(command[1]);
                point = (point + move > size-1) ? size-1 : point + move;
            } else if(command[0].equals("C")) {
                delete.add(point);
                if(point == size-1) point--;
                size--;
            } else if(command[0].equals("Z")) {
                int idx = delete.pop();
                if(point >= idx) point++;
                size++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<size; i++) {
            sb.append("O");
        }
        //삭제되어있던 인덱스 insert
        while(!delete.isEmpty()) {
            int idx = delete.pop();
            sb.insert(idx, "X");
        }
        
        return sb.toString();
    }
}
