import java.util.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        int[] counts = new int[words.length];
        
        Arrays.sort(words);
        
        for(int i=0;i<words.length - 1; i++) {
            String pre = words[i];
            String next = words[i+1];
            int len = Math.min(pre.length(), next.length());
            int count = 0;
            for(int j = 0; j<len;j++) {
                if(pre.charAt(j) != next.charAt(j))
                    break;
                count++;
            }
            
            if(count == len) {
                counts[i] = Math.max(counts[i], count);
            } else {
                counts[i] = Math.max(counts[i], count + 1);
            }
            counts[i+1] = Math.max(counts[i+1], count + 1);
            
        }
        
        for(int i : counts)
            answer += i;
        
        return answer;
    }
}