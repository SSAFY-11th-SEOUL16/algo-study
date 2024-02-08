import sys
from collections import deque

input = sys.stdin.readline

N = int(input().rstrip())


maps = [0]
maps.extend(list(map(int, input().rstrip().split())))
now, target = map(int, input().rstrip().split())

q = deque()
q.append((now,0))
visited = [False for _ in range(N + 1)]

while q:
    now, cnt = q.pop()
    
    if maps[now] == 1:
        cnt += 1
        print(cnt)
        break
    
    if now == target:
        print(cnt)
        break
    visited[now] = True
    
    cnt += 1    
    for i in range(1,(target // maps[now]) + 1):
        n_now = now + maps[now] * i
        
        if n_now <= N and not visited[n_now]:
            q.append((n_now,cnt))
    
    n_now = 1
    i = 0
    while n_now >= 1:
        n_now = now - maps[now] * i
        i += 1
        if n_now >= 1 and not visited[n_now]:
            q.append((n_now,cnt))            
