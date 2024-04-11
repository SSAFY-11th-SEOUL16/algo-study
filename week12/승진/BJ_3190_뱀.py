import sys
from collections import deque

input = sys.stdin.readline

N = int(input().rstrip())

maps = [[0 for _ in range(N)] for _ in range(N)]

K = int(input().rstrip())

for _ in range(K):
    x, y = map(int, input().split())

    maps[y-1][x-1] = 1

L = int(input().rstrip())

time_cnt = 0

commands = dict()
moves = [[1,0],[0,1],[-1,0],[0,-1]]


for _ in range(L):
    t, c = input().split()
    commands[int(t)] = c

snake = deque([(0,0)])

w = 0


while True:
   
    if time_cnt in commands:
        if commands[time_cnt] == 'D':
            w = (w + 1) % 4
        elif commands[time_cnt] == 'L':
            w = (w + 3) % 4

    x, y = snake[-1][0] + moves[w][0], snake[-1][1] + moves[w][1]
    time_cnt += 1
    if 0 <= x < N and 0 <= y < N and (x,y) not in snake:
        if maps[x][y] != 1:
            snake.popleft()
        else:
            maps[x][y] = 0
        snake.append((x,y))
    else:
        break


print(time_cnt)