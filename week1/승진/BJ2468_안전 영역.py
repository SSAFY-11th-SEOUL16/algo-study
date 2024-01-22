import sys
from collections import deque
input = sys.stdin.readline

def dfs(h):
    
    visited = [[False for _ in range(N)] for _ in range(N)]
    q = deque()
    
    result = 0
    
    for i in range(N):
        for j in range(N):
            if maps[i][j] <= h:
                continue
            if visited[i][j]:
                continue
            q.append([i,j])
    
            while q:
                x, y = q.pop()
        
                for move in moves:
                    nx = x + move[0]
                    ny = y + move[1]
            
                    if nx < 0 or ny < 0 or nx >= N or ny >= N or visited[nx][ny] or maps[nx][ny] <= h :
                        continue
                    q.append([nx,ny])
                    visited[nx][ny] = True
            result += 1
    return result

N = int(input().rstrip())
maps = []
max_h = 0


moves = [[-1,0],[0,1],[1,0],[0,-1]]

for _ in range(N):
    tmp = list(map(int,input().rstrip().split()))
    max_h = max(max_h,max(tmp))
    
    maps.append(tmp)

max_area = 0

for h in range(max_h):
    max_area = max(max_area,dfs(h))

print(max_area)

