import java.util.*;
import java.io.*;

class Solution {
    static class TrieNode{
		Map<Character, TrieNode> childNode = new HashMap<>();
		int count = 0;

		public void insert(String word) {
			TrieNode trieNode = this;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);

				trieNode.childNode.putIfAbsent(c, new TrieNode());
				trieNode = trieNode.childNode.get(c);
				trieNode.count++;
			}
		}

		public int find(String word) { 
			TrieNode trieNode = this;
			int cnt = 0;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				TrieNode node = trieNode.childNode.get(c);
				trieNode = node;

				cnt++;
				if(trieNode.count == 1) return cnt;
			}
			return cnt;
		}
	}
	
	public static int solution(String[] words) {
		int answer = 0;
		
		TrieNode root = new TrieNode();
		for(String word : words) {
			root.insert(word);
		}
		
		for(String word : words) {
			answer += root.find(word);
		}
		
		return answer;
	}
}