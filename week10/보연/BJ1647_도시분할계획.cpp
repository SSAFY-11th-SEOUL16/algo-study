#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Node {
	int num;
	int weight;

	bool operator<(const Node &other) const {
		return weight > other.weight;
	}
};


const int SIZE = 100001;
int N, M;
vector<Node> nodeArr[SIZE];


void input() {
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int from, to, weight;
		cin >> from >> to >> weight;
		nodeArr[from].push_back({ to, weight });
		nodeArr[to].push_back({ from, weight });
	}
}

void solve(int start) {
    priority_queue<Node> pq;
	bool visited[SIZE] = { 0, };

	pq.push({ start, 0 });
	int cnt = 0; //연결된 노드 수
	int MAX = 0; //제일 유지비가 큰 경로
	int total = 0;
	while (!pq.empty()) {
		Node curr = pq.top();
		pq.pop();

		//방문
		if (visited[curr.num]) continue;
		visited[curr.num] = true;
		total += curr.weight;
		if (MAX < curr.weight) MAX = curr.weight;

		//다 돌았는지 확인
		if (++cnt == N) break;

		//모든 정점에 대해서 방문하지 않은 정점 중, 인접한 노드 중, 최솟값 고르기
		for (int i = 0; i < nodeArr[curr.num].size(); i++) {
			Node next = nodeArr[curr.num][i];
			if (visited[next.num]) continue;
			pq.push({ next.num, next.weight });
			
		}

	}

	cout << total - MAX;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
	input();
	solve(1);
	return 0;
}
