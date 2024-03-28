#include <iostream>
#include <cstring>
#include <vector>
using namespace std;

const int SIZE = 201;
int N;
int child[SIZE];
int c[SIZE];

void input() {
    cin >> N;
    for(int i=0; i<N; i++) {
        cin >> child[i];
    }

}

void solve() {
    for(int i=0; i<N; i++) {
        int curr = child[i];
        for(int j=0; j<N; j++) {
            if(c[j] == 0) {
                c[j] = curr;
                break;
            }
            if(c[j] > curr) {
                c[j] = curr;
                break;
            }
            
        }
    }
    
    int cnt = 0;
    for(int i=0; i<N; i++) {
        if(c[i] == 0) break;
        cnt ++;
    }
    
    cout << N - cnt;
}

int main() {
    input();
    solve();
    
    return 0;
}
