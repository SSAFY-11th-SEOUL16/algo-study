import sys
from itertools import combinations
import copy

def moves_v(tmp_maps):
    global vs
    global N
    global M
    q = []
    for v in vs:
        q.append(v)
    moves = [[1,0],[-1,0],[0,1],[0,-1]]

    while q:
        x, y = q.pop()
        
        for move in moves:
            nx, ny = x + move[0], y + move[1]
            if 0 <= nx < N and 0 <= ny < M and tmp_maps[nx][ny] == 0:
                q.append([nx,ny])
                tmp_maps[nx][ny] = 1
    
    return (N*M)-sum(sum(tmp_maps,[]))

input = sys.stdin.readline

N, M = map(int, input().split())

vs = []
blank = []
maps = []
for i in range(N):
    tmp = list(map(int, input().split()))
    for j,t in enumerate(tmp):
        if t == 2:
            vs.append([i, j])
            tmp[j] = 1
        elif t == 0:
            blank.append((i, j))
    maps.append(tmp)

combi = list(combinations(blank,3))

max_cnt = 0
for c in combi:

    for x, y in c:
        maps[x][y] = 1

    max_cnt = max(max_cnt,moves_v(copy.deepcopy(maps)))
    for x, y in c:
        maps[x][y] = 0

print(max_cnt)