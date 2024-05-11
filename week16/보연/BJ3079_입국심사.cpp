#include <iostream>
#include <algorithm>

using namespace std;

typedef long long ll;

const int SIZE = 100001;
int N;
ll M;
int t[SIZE];

void input() {
    cin >> N >> M;
    for(int i=0; i<N; i++) {
        cin >> t[i];
    }
}

void solve() {
    sort(t, t + N);
    
    ll left = 1;
    ll right = t[N-1] * M;
    
    while(left <= right) {
        ll mid = (left + right) / 2;
        ll cnt = 0;
        for(int i=0; i<N; i++) {
            cnt += (mid / t[i]);
            if(cnt > M) break; //이미 초과했으므로 가지치기
        }
        
        //cout << left << " " << right << "=>" << cnt <<'\n';
        
        if(cnt >= M) {
            //가능한 수가 많음 -> 시간을 더 줄이기
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    
    cout << left << '\n';
    
}

int main(){
    input();
    solve();
    return 0;
}
