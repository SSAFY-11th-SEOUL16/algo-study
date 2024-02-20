import sys

def count_star(x,y):
    cnt = 0
    for i in range(x,L + x):
        for j in range(y,L + y):
            if maps[i][j] == 1:
                cnt += 1
    return cnt
input = sys.stdin.readline

N, M, L, K = map(int, input().rstrip().split())

maps = [[0 for _ in range(M + 1)] for _ in range(N + 1)]
visited =[[False for _ in range(M + 1)] for _ in range(N + 1)]
stars = []

max_cnt = 0

for _ in range(K):
    x, y = map(int, input().rstrip().split())
    if x + L >= N:
        x -= x+L-N + 1
    if y + L >= M:
        y -= y+L-N + 1
    stars.append((x, y))
    maps[x][y] = 1
    

for x, y in stars:
    print(x,y)
    for i in range(L):
        if not visited[x][y + i] and y + i < M :
            max_cnt = max(max_cnt, count_star(x, y + i))
            visited[x][y + i] = True

print(max_cnt)
        
