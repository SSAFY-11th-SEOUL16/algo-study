#include <iostream>
#include <cstring>
#include <vector>
using namespace std;

const int SIZE = 100001;
int N, M;
vector<int> person[SIZE];
int answer[SIZE];

void init() {
    memset(answer, 0, sizeof(answer));
}

void input() {
    cin >> N >> M;
    for(int i=1; i<=N; i++) {
        int num;
        cin >> num;
        if(num == -1) continue;
        person[num].push_back(i);
    }
    
    for(int i=0; i<M; i++) {
        int n, m;
        cin >> n >> m;
        answer[n] += m;
    }
}

void solve(int curr) {
    for(int i=0; i<person[curr].size(); i++) {
        answer[person[curr][i]] += answer[curr];
        solve(person[curr][i]);
    }
}

int main()
{
    init();
    input();
    solve(1);
     for(int i=1; i<=N; i++) {
        cout << answer[i] << ' ';
    }
    
    return 0;
}
