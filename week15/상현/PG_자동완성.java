import java.util.HashMap;

/**
 * 트라이
 * 1. 입력받은 단어들을 통해 트라이 생성
 * 2. 입력을 하면서 각 문자열 까지 오는 단어가 몇개인지 확인하기 위해 count 개수 증가
 * ex) word, wo -> w와 o는 word와 wo가 똑같이 가지기 때문에 count가 2가 됨
 * 반대로 count가 1이라는 것은 해당 문자열 아래로는 같은 것이 없다는 것을 의미하므로 count가 1까지의 depth를 더해주면 됨
 *
 */
class Solution {
    private static class N {
        HashMap<Character, N> child;

        int count;

        public N() {
            child = new HashMap<>();
        }
    }

    private static N root = new N();

    public int solution(String[] words) {
        int answer = 0;
        for (String word : words) {
            add(word);
        }

        for (String word : words) {
            answer += count(word);
        }
        return answer;
    }


    private static void add(String s) {
        N now = root;

        for (int i = 0; i < s.length(); i++) {
            now.child.putIfAbsent(s.charAt(i), new N());
            now = now.child.get(s.charAt(i));
            now.count++;
        }

    }

    private static int count(String s) {
        N now = root;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            depth++;
            now = now.child.get(s.charAt(i));
            if (now.count == 1) {
                return depth;
            }
        }
        return depth;
    }
}