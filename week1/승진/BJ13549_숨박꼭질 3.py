import sys
from collections import deque

input = sys.stdin.readline

def bfs(x):
    q = deque([x])		# 다익스트라와 달리 가중치(시간) 없음!
    time = [-1] * MAX		# 중복방문을 위한 가중치행렬
    time[x] = 0

    while q:
        cx = q.popleft()
        if cx == K:
            return time[cx]

        for i in range(3):
            if i == 0:
                nx = cx - 1
            elif i == 1:
                nx = cx + 1
            else:
                nx = cx * 2

            if not 0 <= nx < MAX:	# 조건 검사 역시 다익스트라와 동일
                continue
            if time[nx] != -1 and time[nx] <= time[cx]:
                continue

            if i < 2:			# 한 칸씩 이동하는 경우 큐 뒤에 삽입
                q.append(nx)
                time[nx] = time[cx] + 1
            else:			# 순간이동하는 경우 큐 앞에 삽입
                q.appendleft(nx)
                time[nx] = time[cx]

MAX = 100001
N, K = map(int,input().rstrip().split())

print(bfs(N))