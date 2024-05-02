/**
 * 120.17 ms
 * 트라이
 * 트라이에 단어들 삽입
 * 삽입 하면서 노드의 카운트 증가
 * 카운트가 1인 노드에 도달하거나
 * 단어를 모두 입력 할 때까지 입력
 * */
public class PG17685_자동완성 {
    private static final int SIZE = 26;
    private static final int DIFF = 'a';
    
    private static final class Trie {
        int cnt;
        Trie[] next;
        
        Trie() {
            next = new Trie[SIZE];
        }
        
        final void insert(String str) { // 단어 삽입
            int i;
            int len;
            int idx;
            Trie curr;
            
            len = str.length();
            for (curr = this, i = 0; i < len; curr = curr.next[idx], i++) {
                curr.cnt++; // 노드 카운트 증가
                idx = str.charAt(i) - DIFF;
                if (curr.next[idx] == null) {
                    curr.next[idx] = new Trie();
                }
            }
            curr.cnt++; // 마지막 노드 카운트 증가
        }
        
        final int search(String str) {
            int i;
            int len;
            Trie curr;
            
            len = str.length(); // 카운트가 1인 노드에 도달하거나 단어를 모두 입력 할 때까지 입력
            for (curr = this, i = 0; i < len && curr.cnt != 1; curr = curr.next[str.charAt(i++) - DIFF]);
            return i;
        }
    }
    
    public int solution(String[] words) {
        int cnt;
        Trie root;
        
        root = new Trie();
        for (String word : words) {
            root.insert(word); // 트라이에 단어 삽입
        }
        cnt = 0;
        for (String word : words) {
            cnt += root.search(word); // 입력해야 하는 문자수 누적
        }
        return cnt;
    }
} 