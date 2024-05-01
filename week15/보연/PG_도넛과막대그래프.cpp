#include <cstring>
#include <string>
#include <vector>
#include <iostream>
using namespace std;

const int SIZE = 1000001;
int N;
vector<int> nodeList[SIZE];
bool visited[SIZE];
int donut, line, eight;
int in_degree[SIZE];
int out_degree[SIZE];

void dfs(int start, int curr) {
    if(out_degree[curr] == 0) {
        line++;
        return;
    }
    if(out_degree[curr] >= 2) {
        eight++;
        return;
    }
    if(visited[curr]) {
        donut++;
        return;
    }
    visited[curr] = true;
    
    for(int i=0; i<nodeList[curr].size(); i++) {
        int next = nodeList[curr][i];
        dfs(start, next);
    }
}

vector<int> solution(vector<vector<int>> edges) {
    vector<int> answer;
    N = edges.size();
    for(int i=0; i<edges.size(); i++) {
        int u = edges[i][0];
        int v = edges[i][1];
        
        nodeList[u].push_back(v);
        in_degree[v] ++;
        out_degree[u] ++;
    }
    int root = 0;
    for(int i=0; i<N; i++) {
        if(in_degree[i] == 0 && out_degree[i] >= 2) {
            root = i;
            break;
        }
    }
    
    memset(visited, 0, sizeof(visited));
    donut=0, line=0, eight=0;
    for(int i=0; i<nodeList[root].size(); i++) {
        dfs(nodeList[root][i], nodeList[root][i]);
    }
    answer.push_back(root);
    answer.push_back(donut);
    answer.push_back(line);
    answer.push_back(eight);

    return answer;
}