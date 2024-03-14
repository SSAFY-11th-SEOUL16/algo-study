#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

struct Edge{
  int to, weight;

  bool operator<(const Edge other)const{
    return weight > other.weight;
  }
};

const int N_MAX = 1001;
const int M_MAX = 10001;
int N, M, X;
vector<Edge> edgeList[M_MAX];
int minDist[N_MAX];
int answer[N_MAX];

void input() {
    cin >> N >> M >> X;
    memset(edgeList, 0, sizeof(edgeList));
    for(int i=0; i<M; i++) {
        int from, to, weight;
        cin >> from >> to >> weight;
        //edgeList.push_back({from, to, weight});
        edgeList[from].push_back({to, weight});
    }
}

void dijkstra1(int start) {
    priority_queue<Edge> pq;
    bool visited[N_MAX] = {0,};
    for(int i=1; i<=N; i++) {
        minDist[i] = 2e9;
    }
    pq.push({start, 0}); //시작 정점
    minDist[start] = 0;
    //int ans = 0;

    //start -> X까지 가는 최단경로
    while(!pq.empty()) {
        Edge curr = pq.top();
        pq.pop();
        visited[curr.to] = true;

        for(Edge edge : edgeList[curr.to]) {
            if(!visited[edge.to] && 
            edge.weight + minDist[curr.to] < minDist[edge.to]) {
                minDist[edge.to] = edge.weight + minDist[curr.to];
                //edge.weight = minDist[edge.to];
                //pq.push(edge);
                pq.push({edge.to, minDist[edge.to]});
                
            }
        }
    }
    answer[start] = minDist[X];
}

void dijkstra2(int start){
    priority_queue<Edge> pq;
    bool visited[N_MAX] = {0,};
    for(int i=1; i<=N; i++) {
        minDist[i] = 2e9;
    }
    pq.push({X, 0}); //시작점
    minDist[X] = 0;
    
    while(!pq.empty()) {
        Edge curr = pq.top();
        pq.pop();
        
        visited[curr.to] = true;
        
        for(Edge edge : edgeList[curr.to]) {
            if(!visited[edge.to] 
            && minDist[edge.to] > minDist[curr.to] + edge.weight){
                minDist[edge.to] = minDist[curr.to] + edge.weight;
                edge.weight = minDist[edge.to];
                pq.push({edge.to, minDist[edge.to]});
            }
        }
    }
    
    answer[start] += minDist[start];
}

void printArr() {
    for(int i=1; i<=N; i++) {
        cout << answer[i] << ' ';
    }
    cout << '\n';
}

void solve() {
    for(int i=0; i<N; i++) {
        //각 점에서 X까지 가는 최단 경로
        dijkstra1(i + 1);
        //X에서 각 점까지 가는 최단 경로
        dijkstra2(i + 1);
    }
    
    int ans = 0;
    for(int i=1; i<=N; i++) {
        if(ans < answer[i]) ans = answer[i];
    }
    cout << ans;
}

//216ms
//최단경로 : 다익스트라 알고리즘

int main()
{
    input();
    //printArr();
    solve();
    return 0;
}

