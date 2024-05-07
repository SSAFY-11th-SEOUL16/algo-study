class Solution {
    class Word{
        int cnt;
        Word[] words;
        
        public Word(){
            words = new Word[26];
            cnt = 1;
        }
        
    }
    
    public int solution(String[] words) {
        int answer = 0;
        
        /*
            1. 트라이 구성
                1.1 이미 만들어진 문자면 cnt++
                1.2 아니면 새로 생성
            2. root부터 어떤 문자까지 시작하는 단어가 1개인 경우에 출력
        */
        
        Word root = new Word();
        
        for(String word : words){
            Word temp = root;
            for(int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                int cIdx = c-'a';
                if(temp.words[cIdx] == null){
                    temp.words[cIdx] = new Word();
                }else{
                    temp.words[cIdx].cnt++;
                }
                temp = temp.words[cIdx];
            }
        }
        
        for(String word : words){
            Word temp = root;
            
            boolean isFin = true;
            for(int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                int cIdx = c-'a';
                if(temp.words[cIdx].cnt == 1){
                    answer += i+1;
                    isFin = false;
                    break;
                }
                temp = temp.words[cIdx];
            }
            
            if(isFin) answer += word.length();
        }
        
        return answer;
    }
}