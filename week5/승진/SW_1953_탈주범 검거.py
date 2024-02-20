import sys
from collections import deque
input = sys.stdin.readline

test_case = int(input().rstrip())

pipe = [[],[[1,2,5,6],[1,2,4,7],[1,3,4,5],[1,3,6,7]],[[1,2,5,6],[1,2,4,7],[],[]],[[],[],[1,3,4,5],[1,3,6,7]],[[1,2,5,6],[],[],[1,3,6,7]],[[],[1,2,4,7],[],[1,3,6,7]],[[],[1,2,4,7],[1,3,4,5],[]],[[1,2,5,6],[],[1,3,4,5],[]]]

moves = [[-1,0],[1,0],[0,-1],[0,1]]


for t in range(1,test_case+1):
    N, M, R, C, L = map(int, input().rstrip().split())
    maps = []
    for _ in range(N):
        maps.append(list(map(int, input().rstrip().split())))
    
    visited = [[0 for _ in range(M)] for _ in range(N)]

    q = deque()
    q.append((R,C,0))
    visited[R][C] = 1
    while q:
        x, y, cnt = q.popleft()
        if cnt == L:
            break
        visited[x][y] = 1
        for idx, move in enumerate(moves):
            nx, ny = x + move[0], y + move[1]
            if 0 <= nx < N and 0 <= ny < M and maps[nx][ny] != 0 and visited[nx][ny] == 0:
                if maps[nx][ny] in pipe[maps[x][y]][idx]:
                    q.append((nx, ny, cnt + 1))
        
    ans = 0
    for vs in visited:
        for v in vs:
            if v == 1:
                ans += 1

    print(f'#{t} {ans}')
