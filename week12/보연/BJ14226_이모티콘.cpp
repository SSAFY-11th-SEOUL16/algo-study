#include <iostream>
#include <queue>
using namespace std;

/*
[이모티콘]
1. 조건 3개에 만족하도록 모든 경우의 수를 탐색한다.
2. 탐색할 때 현재 클립보드에 있는 이모티콘 수와 화면에 있는 이모티콘 수를 재방문하지 않도록 방문체크 한다.
3. 방문체크할때 범위를 벗어나지 않았는지 체크
4. 화면의 개수가 S에 도달하면 시간 출력
*/

struct Node {
    int clip; //클립보드에 있는 이모티콘 수 
    int monitor; //화면에 있는 이모티콘 수
    int t; //현재 시간
};

const int SIZE = 1001;
int S;

void input() {
	cin >> S;
}

void solve() {
	queue<Node> que;
	bool visited[SIZE][SIZE] = {0,};
	que.push({0, 1, 0});
	
	while(!que.empty()) {
	    Node curr = que.front();
	    que.pop();
	    
	    if(curr.monitor == S) {
	        cout << curr.t;
	        break;
	    }
	    visited[curr.clip][curr.monitor] = true;
	    if(curr.monitor > 0 && curr.monitor <= 1000) {
	        //클립보드로 복사하기
    	    if(!visited[curr.monitor][curr.monitor]) {
    	        que.push({curr.monitor, curr.monitor, curr.t + 1});
    	    }
    	    
    	    //삭제하기
    	    if(!visited[curr.clip][curr.monitor - 1]) {
    	        que.push({curr.clip, curr.monitor - 1, curr.t + 1});
    	    }
	    }
	    
	    //클립보드에 있는 것을 화면에 붙여넣기
	    if(curr.monitor + curr.clip <= 1000 && curr.clip > 0) {
	        que.push({curr.clip, curr.monitor + curr.clip, curr.t + 1});
	    }
	}
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    
	input();
	solve();

	return 0;
}
