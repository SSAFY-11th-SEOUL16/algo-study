#include <iostream>

using namespace std;
typedef pair<int, int> ci;

int R, C, K, ans;
char board[6][6];
bool visited[6][6];
int dr[] = {-1,0,1,0};
int dc[] = {0,1,0,-1};

void input() {
    cin >> R >> C >> K;
    for(int i=0; i<R; i++) {
        for(int j=0; j<C; j++) {
            cin >> board[i][j];
        }
    }
}

void solve(ci curr, int cnt) {
    if(curr.first == 0 && curr.second == C-1) {
        if(cnt == K)
            ans++;
        return;
    }
    
    for(int i=0; i<4; i++) {
        int nr = curr.first + dr[i];
        int nc = curr.second + dc[i];
        if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
        if(visited[nr][nc]) continue;
        if(board[nr][nc] == 'T') continue;
        visited[nr][nc] = true;
        solve({nr, nc}, cnt + 1);
        visited[nr][nc] = false;
    }
}

int main()
{
    input();
    visited[R-1][0] = true;
    solve({R-1, 0}, 1);
    cout << ans;
    return 0;
}
