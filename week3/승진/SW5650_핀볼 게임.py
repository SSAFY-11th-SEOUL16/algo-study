import sys
from collections import deque

input = sys.stdin.readline

T = int(input().rstrip())




q = []

moves = [[-1,0],[1,0],[0,-1],[0,1]]

change = [[],[5,3,0,5],[3,5,1,5],[2,5,5,1],[5,2,5,0],[5,5,5,5]]









for test_case in range(1, T + 1):
    max_cnt = 0
    N = int(input().rstrip())
    maps = [[5 for _ in range(N + 2)]]
    hole ={6:[],7:[],8:[],9:[],10:[]}
    for i in range(N):
        tmp = [5]
        tmp.extend(list(map(int, input().rstrip().split())))
        for j,t in enumerate(tmp):
            if 6 <= t <= 10:
                hole[t].append([i + 1, j])
        tmp.append(5)
        maps.append(tmp)
    
    maps.append([5 for _ in range(N + 2)])
    
    print(hole)
    for m in maps:
        print(m)
    
    for i in range(1, N + 1):
        for j in range(1, N + 1):
            if maps[i][j] == 0:
                q.append((i, j, 0,0))
                q.append((i, j, 1,0))
                q.append((i, j, 2,0))
                q.append((i, j, 3,0))
                while q:
                    x, y, z, cnt = q.pop()
                    while True:
                        x, y = x + moves[z][0], y + moves[z][1]
                        if x == i and y == j:
                            break
                        
                        if maps[x][y] == 5 or maps[x][y] == -1:
                            break
                        elif 1 <= maps[x][y] < 5:
                            
                            z = change[maps[x][y]][z]
                            if z == 5:
                                cnt *= 2
                                cnt += 1
                                break
                            cnt += 1
                        elif 6 <= maps[x][y] <= 10:
                            tmp = hole[maps[x][y]]
                            for arr in tmp:
                                if arr != [x,y]:
                                    x, y = arr
                                    break                
                    if maps[x][y] == 5:
                        cnt *= 2
                        cnt += 1
                    
                    max_cnt = max(max_cnt, cnt)
    print(f'#{test_case} {max_cnt}')