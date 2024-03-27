#include <iostream>
#include <cstring>
#include <map>
#include <set>
using namespace std;

const int SIZE = 5;
int N;
char puzzle[SIZE][SIZE];
bool visited[SIZE][SIZE] = {0,};
int dr[] = {-1,-1,-1,0,0,1,1,1};
int dc[] = {-1,0,1,-1,1,-1,0,1};
set <string> s;

struct Trie {
    map<char, Trie*> m;
    int u{};
    void push(string& str, int i) {
        if(i == (int)str.size()) u = 1;
        else {
            if(!m.count(str[i])) m[str[i]] = new Trie();
            m[str[i]]->push(str, i+1);
        }
    }
    int search(string str, int cr, int cc) {
        int res{};
        if(u) {
            res++;
            s.insert(str);
        }
        for(int i{}; i < 8; i++) {
            int r(cr + dr[i]), c(cc + dc[i]);
            if(r && c && r < SIZE && c < SIZE && m.count(puzzle[r][c]) && !visited[r][c]) {
                visited[r][c] = true;
                m[puzzle[r][c]]->search(str + puzzle[r][c], r, c);
                visited[r][c] = false;
            }
        }
        return res;
    }
};



void solve() {
    Trie* root(new Trie());
   string str;
  for(int i=0; i<str.length(); i++) {
      root->push(str[i], 0);
  }

    for(int i=0; i<SIZE; i++) {
        for(int j=0; j<SIZE; j++) {
            cin >> puzzle[i][j];
            
        }
    }
    
    for(int i=0; i<SIZE; i++) {
        for(int j=0; j<SIZE; j++) {
            if(root->m.count(puzzle[i][j])) {
                visited[i][j] = true;
                root->m[puzzle[i][j]]->search(str + puzzle[i][j], i, j);
                visited[i][j] = false;
            }
            
        }
    }
    cout << s.size();
}

int main() {
    solve();
    
    return 0;
}
