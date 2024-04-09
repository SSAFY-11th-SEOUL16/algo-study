import sys, heapq
from collections import deque
input = sys.stdin.readline

n, m, k = map(int, input().split())
person = [deque([]) for _ in range(m)]
for i in range(n):
    a, b = map(int, input().split())
    person[i % m].append((-a, -b, i % m, 1 if i == k else 0))
cnt = 0
pq = []
for i in range(m):
    if person[i]:
        heapq.heappush(pq, person[i][0])
while True:
    x = heapq.heappop(pq)
    person[x[2]].popleft()
    if x[3]:
        break
    else:
        cnt += 1
        if person[x[2]]:
            heapq.heappush(pq, person[x[2]][0])
print(cnt)