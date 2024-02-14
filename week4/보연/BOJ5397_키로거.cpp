#include <iostream>
#include <string>
#include <vector>
#include <list>

using namespace std;

//연결리스트 구현의 흔적
//const int NODE_SIZE = 5001;

//struct Node {
//	char data;
//	Node* prev;
//	Node* next;
//
//	Node(char data) : data(data), prev(nullptr), next(nullptr) {}; //생성자
//};
//
//class LinkedList {
//	Node* head;
//	Node* tail;
//	vector<Node*> nodeArr;
//	int nodeCnt;
//
//public:
//	LinkedList() : head(nullptr), tail(nullptr), nodeCnt(0) {
//		nodeArr.resize(NODE_SIZE, nullptr);
//	}
//
//	Node* getNewNode(char x) {
//		nodeArr[nodeCnt] = new Node(x);
//		return nodeArr[nodeCnt++];
//	}
//
//	void add(char x) {
//		Node* cur = tail;
//		Node* newNode = getNewNode(x);
//		cur->next = newNode;
//		newNode->prev = cur;
//		tail = newNode;
//	}
//};

int main() {
	int T;
	cin >> T;
	list<char>::iterator it;
	for (int tc = 1; tc <= T; tc++) {
		string input;
		cin >> input;
		list<char> list;
		it = list.begin(); //패스워드 삽입할 위치
		for (int i = 0; i < input.length(); i++) {
			char curr = input[i];
			if ((curr >= 65 && curr <= 90) || (curr >= 97 && curr <= 122) || (curr - '0' >= 0 && curr - '0' <= 9 )) {
				//list.add(curr);
				list.insert(it, curr);
			}

			if (curr == '-') {
				if (list.empty() || it == list.begin()) continue;
				it = list.erase(--it);
			}

			if (curr == '<') {
				if (it == list.begin()) continue;
				it--;
			}

			if (curr == '>') {
				if (it == list.end()) continue;
				it++;
			}
		}

		for (auto& c : list) {
			cout << c;
		}
		cout << '\n';
	}

	return 0;
}

//import java.util.LinkedList;
//import java.util.Scanner;
//
//public class Main {
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		int T = scanner.nextInt();
//		scanner.nextLine(); // Consume newline
//		for (int tc = 1; tc <= T; tc++) {
//			String input = scanner.nextLine();
//			LinkedList<Character> list = new LinkedList<>();
//			LinkedList<Character>.ListIterator it = list.listIterator(); // 패스워드 삽입할 위치
//
//			for (int i = 0; i < input.length(); i++) {
//				char curr = input.charAt(i);
//				if ((curr >= 65 && curr <= 90) || (curr >= 97 && curr <= 122) || (curr - '0' >= 0 && curr - '0' <= 9)) {
//					list.add(it.nextIndex(), curr);
//				}
//
//				if (curr == '-') {
//					if (list.isEmpty() || it.previousIndex() < 0) continue;
//					it.previous();
//					it.remove();
//				}
//
//				if (curr == '<') {
//					if (it.hasPrevious()) it.previous();
//				}
//
//				if (curr == '>') {
//					if (it.hasNext()) it.next();
//				}
//			}
//
//			for (char c : list) {
//				System.out.print(c);
//			}
//			System.out.println();
//		}
//	}
//}
