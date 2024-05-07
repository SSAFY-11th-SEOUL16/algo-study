#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;

const int SIZE = 10001;
int N, T;
string str[SIZE];

void input() {
    cin >> N;
    for(int i=0; i<N; i++) {
        string tmp;
        cin >> tmp;
        str[i] = tmp;
    }
}

void solve() {
    sort(str, str+N);
    bool flag = false;
    for(int i=0; i<N-1; i++) {
        string curr = str[i];
        string next = str[i+1];
        //cout << curr << " : " << next << '\n';
        if(curr.length() > next.length()) {
            continue;
        }
        if(curr == next.substr(0, curr.length())) {
            flag = true;
            break;
        }
    }
    
    if(flag) {
        cout << "NO\n";
    } else {
        cout << "YES\n";
    }
}

int main() {
    cin >> T;
    for(int i=0; i<T; i++) {
        input();
        solve();
    }
    return 0;
}
