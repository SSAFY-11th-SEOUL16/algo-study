import heapq

def dijkstra(s):
    D = [float('inf')] * (N+1)
    D[s] = 0
    q = []								    
    heapq.heappush(q, (0, s))				
    while q:
        dist, now = heapq.heappop(q)		
        if D[now] >= dist:					
            for v, val in city[now]:		
                if dist + val < D[v]:		
                    D[v] = dist + val
                    heapq.heappush(q, (dist + val, v))
    return D

N, M, X = map(int, input().split())
city = [[] for _ in range(N+1)]
for _ in range(M):
    a, b, t = map(int, input().split())
    city[a].append([b, t])
ans = dijkstra(X)
ans[0] = 0
for i in range(1, N+1):
    if i != X:
        res = dijkstra(i)
        ans[i] += res[X]

print(max(ans))