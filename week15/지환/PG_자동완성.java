class PG_자동완성 {
    public int solution(String[] words) {
        int answer = 0;
        Trie root = new Trie();
        for(String word: words) {
            root.insert(word);
        }
        for(String word: words) {
            answer+=root.search(word);
        }
        return answer;
    }
    
    static class Trie {
        int cnt;
        Trie[] next = new Trie[26];
        
        void insert(String word) {
            int len = word.length();
            Trie cur = this;
            for(int i=0; i<len; i++) {
                cur.cnt++;
                if(cur.next[word.charAt(i)-'a'] == null) {
                    cur.next[word.charAt(i)-'a'] = new Trie();
                }
                cur = cur.next[word.charAt(i)-'a'];
            }
            cur.cnt++;
        }
        
        int search(String word) {
            int len = word.length();
            Trie cur = this;
            for(int i=0; i<len; i++) {
                if(cur.cnt == 1) return i;
                cur = cur.next[word.charAt(i)-'a'];
            }
            return len;
        }
    }
}
