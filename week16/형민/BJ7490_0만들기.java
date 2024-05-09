import java.io.*;
import java.util.*;


class Main {
    static int n;
    static ArrayList<String> list;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for(int t=0;t<tc;t++) {
        	n = Integer.parseInt(br.readLine());
        	list = new ArrayList<>();
        	
        	subset(2, "1");
        	
        	Collections.sort(list);
        	for(String s : list) {
        		sb.append(s + "\n");
        	}
        	
        	sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    static void subset(int cur, String str) {
    	if(cur==(n + 1)) {
    		//계산
    		int a = 1;
    		String b = "";
    		char mid='.';
    		
    		for(int i=1;i<str.length();i++) {
    			char v = str.charAt(i);
    			
    			if(v == '+' || v == '-') {
    				//식이 완성된 경우
    				if(!b.equals("")) {
    					if(mid == '+') a = a + Integer.parseInt(b);
    					else if(mid == '-') a = a - Integer.parseInt(b);
    					b = "";
    				}
    				mid = v;
    			}
    			else if(v == ' ') continue;
    			else {
    				if(mid =='.') a = a * 10 + Integer.parseInt(v + "");
    				else b += v;
    			}
    		}
    		
    		if(mid == '+') a = a + Integer.parseInt(b);
			else if(mid == '-') a = a - Integer.parseInt(b);
    		
    		if(a==0) {
    			list.add(str);
    		}
    		
    		return;
    	}
    	
    	subset(cur+1, str + "+" + cur);
    	subset(cur+1, str + " " + cur);
    	subset(cur+1, str + "-" + cur);
    }
}