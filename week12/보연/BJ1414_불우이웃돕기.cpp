#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

/*
[불우이웃돕기]

1. 총 랜선 길이 합에 MST 값을 뺀다.
2. 부모가 같다면 이미 연결되어 있는 컴퓨터다.

*/

struct Node {
    int u, v, w;
};

const int SIZE = 10000;
int N, total;
int parent[SIZE];
vector<Node> edgeList;

bool cmp(Node n1, Node n2) {
    return n1.w < n2.w;
}

void printArr() {
    for(int i=0; i<edgeList.size(); i++) {
	    cout << edgeList[i].w;
	    cout << ' ';
	}
}

void input() {
    total = 0;
	cin >> N;
	for(int i=1; i<=N; i++) {
	    for(int j=1; j<=N; j++) {
	        char ch;
	        cin >> ch;
	        if(ch == '0') continue;
	        if(ch >= 'a' && ch <= 'z') {
	            edgeList.push_back({i, j, ch - 'a' + 1});
	            total += (ch - 'a' + 1);
	        }
	        if(ch >= 'A' && ch <= 'Z'){
	            edgeList.push_back({i, j, ch - 'A' + 27});
	            total += (ch - 'A' + 27);
	        }
	        
	    }
	    parent[i] = i;
	}
}

int find(int v) {
    if(parent[v] == v) return v;
    return parent[v] = find(parent[v]);
}

void unionNode(int u, int v) {
    int a_root = find(u);
    int b_root = find(v);
    if(a_root == b_root) return;
    parent[b_root] = a_root;
}

void solve() {
	//printArr();
	sort(edgeList.begin(), edgeList.end(), cmp);
	
	int cnt = 0;
	int val = 0;
	for(int i=0; i<edgeList.size(); i++) {
	    Node curr = edgeList[i];
	    if(curr.u == curr.v) continue;
	    if(find(curr.u) == find(curr.v)) continue;
	    unionNode(curr.u, curr.v); //랜선 연결
	    cnt++;
	    val += curr.w;
	    //cout << cnt << " : " << val <<'\n';
	    if(cnt == N -1) break;
	}
	//cout << total << " " << val<<'\n';
	if (cnt < N - 1) cout << "-1";
	else cout << total - val;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    
	input();
	solve();

	return 0;
}
