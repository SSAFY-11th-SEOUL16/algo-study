#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

typedef long long ll;
typedef pair<ll, ll> cll;

int N;
ll sum;
vector<cll> town;

bool cmp(cll a, cll b) {
    return a.first < b.first;

}

void input() {
    sum = 0;
    
    cin >> N;
    for(int i=0; i<N; i++) {
        ll X, A;
        cin >> X >> A;
        town.push_back({X, A});
        sum  += A;
    }
    sort(town.begin(), town.end(), cmp);
    
}

void printArr() {
    for(int i=0; i<N; i++) {
        cout << town[i].first <<" " << town[i].second << '\n';
    }
}

void solve() {
    
    ll curr = 0;
    for(int i=0; i<town.size(); i++) {
        curr += town[i].second;
        if(curr >= (sum + 1) /2) {
            cout << town[i].first;
            return;
        }
    }
}

// 128ms
// BJ2141 : 우체국
// 중앙값을 찾는 문제

int main()
{
    input();
    //printArr();
    solve();
    return 0;
}
