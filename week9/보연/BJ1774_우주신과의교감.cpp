#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

typedef pair<int, int> ci;

struct Edge {
	int x, y;
	double weight;
};

const int SIZE = 1001;
int N, M;
double ans;
int parents[SIZE];
ci pos[SIZE];
ci connect[SIZE];
vector<Edge> edgeList;

void input() {
	cin >> N >> M;
	memset(parents, 0, sizeof(parents));
	memset(pos, 0, sizeof(pos));
	memset(connect, 0, sizeof(connect));
	for (int i = 1; i <= N; i++) {
		int x, y;
		cin >> x >> y;
		pos[i] = { x, y };
	}
	for (int i = 0; i < M; i++) {
		int x, y;
		cin >> x >> y;
		connect[i] = { x, y };
	}
}

double getDist(int x1, int y1, int x2, int y2) {
	return sqrt(pow(abs(x1 - x2), 2) + pow(abs(y1 - y2), 2));
}

bool cmp(Edge e1, Edge e2) {
	return e1.weight < e2.weight;
}

int find(int v) {
	if (v == parents[v]) return v;
	return parents[v] = find(parents[v]);
}

bool unionNode(int u, int v) {
	int a_root = find(u);
	int b_root = find(v);
	if (a_root == b_root) return false;
	parents[b_root] = a_root;
	return true;
}

void solve() {
	//간선리스트 구하기
	for (int i = 1; i < N - 1; i++) {
		for (int j = i + 1; j < N; j++) { //조합 구하기
			double dist = getDist(pos[i].first, pos[i].second, pos[j].first, pos[j].second);
			edgeList.push_back({ i, j, dist }); //i번과 j번 사이의 간선
		}
	}

	//오름차순 정렬
	sort(edgeList.begin(), edgeList.end(), cmp);

	//크루스칼 알고리즘
	for (int i = 1; i <= N; i++) {
		parents[i] = i;
	}
	for (int i = 0; i < M; i++) {
		unionNode(connect[i].first, connect[i].second); //이미 연결된 통로
	}
	ans = 0.0f;
	for (int i = 0; i < edgeList.size(); i++) {
		if (unionNode(edgeList[i].x, edgeList[i].y)) {
			ans += edgeList[i].weight;
		}
	}
}

int main()
{
	input();
	solve();
	cout << fixed;
	cout.precision(2);
	cout << ans;
	return 0;
}
