#include <iostream>
#include <vector>
using namespace std;

const int SIZE = 10001;
int N, M, K, MIN;
int friend_money[SIZE];
int parent[SIZE];
bool visited[SIZE];
vector<int> min_money;

int find(int v) {
    if(parent[v] == v) return v;
    return parent[v] = find(parent[v]);
}

bool unionNode(int u, int v) {
    int a_root = find(u);
    int b_root = find(v);
    if(a_root == b_root) return false;
    if(friend_money[a_root] > friend_money[b_root]) {
        parent[a_root] = b_root;
    }
    else {
        parent[b_root] = a_root;
    }
    return true;
}

int main(){
    cin >> N >> M >> K;
    for(int i=1; i<=N; i++) {
        cin >> friend_money[i];
        parent[i] = i;
    }
    
    for(int i=0; i<M; i++) {
        int v, w;
        cin >> v >> w;
        unionNode(v, w);
    }
    
    int sum = 0;
    int cnt = 0;
    for(int i=1; i<=N; i++) {
        int curr = find(i);
        if(visited[curr]) {
            continue;
        }
        sum += friend_money[curr];
        visited[curr] = true;
    }
    
    if(K >= sum) {
        cout << sum;
    } else {
        cout << "Oh no";
    }
    
    return 0;
}
