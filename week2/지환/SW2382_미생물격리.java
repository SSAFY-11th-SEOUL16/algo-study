package week2.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class SW2382_미생물격리 {
	static int[] dx = new int[] {-1,1,0,0};
    static int[] dy = new int[] {0,0,-1,1};
    static int n;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t=1; t<=test; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            List<Node> list = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken())-1;
                list.add(new Node(x, y, cnt, d));
            }
            
            
            for(int i=0; i<m; i++) {
            	//Node 이동 및 엣지일 경우 계산
            	for(int j=0; j<list.size(); j++) {
            		Node node = list.get(j);
                	node.move();
                	if(checkEdge(node.x, node.y)) {
                		node.changeDir();
                		if(node.removeCnt() == 0) {
                			list.remove(j);
                			list.add(node);
                			j--;
                		}
                	}
            	}
                
            	//위치가 같을 경우 머지
            	Collections.sort(list, 
                		Comparator.comparing(Node::getPos)
                		.thenComparing(Node::getCnt));
                
                for(int j=1; j<list.size(); j++) {
                	Node prev = list.get(j-1);
                	Node now = list.get(j);
                	if(now.getPos() == prev.getPos()) {
                		now.cnt += prev.cnt;
                		list.remove(j-1);
                		j--;
                	}
                }
            }
            
            int sum = 0;
            for(int i=0; i<list.size(); i++) {
            	sum+=list.get(i).cnt;
            }
            System.out.println("#" + t + " " + sum);
        }
    }
    
    static class Node {
    	int x;
    	int y;
    	int cnt;
    	int d;
    	
    	Node(int x, int y, int cnt, int d) {
    		this.x = x;
    		this.y = y;
    		this.cnt = cnt;
    		this.d = d;
    	}
    	
    	void move() {
    		x += dx[d];
    		y += dy[d];
    	}
    	void changeDir() {
    		switch(d) {
    		case 0: d=1; break;
    		case 1: d=0; break;
    		case 2: d=3; break;
    		case 3: d=2; break;
    		}
    	}
    	int removeCnt() {
    		cnt/=2;
    		return cnt;
    	}
    	int getPos() {
    		return x*n + y;
    	}
    	int getCnt() {
    		return cnt;
    	}
    }
 
    public static boolean checkEdge(int x, int y) {
        return x==0 || y==0 || x==n-1 || y==n-1;
    }
}
