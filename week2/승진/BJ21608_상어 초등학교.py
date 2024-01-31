import sys

def check_tmp_cnt(x, y):
    global N, maps

    
    moves = [[-1,0],[1,0],[0,-1],[0,1]]
    
    cnt = 0
    
    for move in moves:
        
        nx, ny = x + move[0], y + move[1]
        if 0 <= nx < N and 0 <= ny < N and maps[nx][ny] == -1:
            cnt += 1
        
    return cnt

def check_tmp_pos(x, y):
    global N, maps

    moves = [[-1,0],[1,0],[0,-1],[0,1]]
    
    arr = []
    
    for move in moves:
        nx, ny = x + move[0], y + move[1]
        if 0 <= nx < N and 0 <= ny < N and maps[nx][ny] == -1:
            arr.append([nx,ny])
    return arr

def like_friend_tmp(child):
    global N, maps, child_pos_set
    
    like_friends = child[1:]
    
    tmp_set = dict()
    
    for like_friend in like_friends:
        if like_friend in child_pos_set:
            tmp_arr = check_tmp_pos(child_pos_set[like_friend][0],child_pos_set[like_friend][1])
            for t_a in tmp_arr:
                pos = (t_a[0],t_a[1])
                if pos in tmp_set:
                    tmp_set[pos] += 1
                else:
                    tmp_set[pos] = 1
    
    return_arr = []
    
    if tmp_set:
        max_cnt = max(tmp_set.values())
        for k, v in tmp_set.items():
            if v == max_cnt:
                return_arr.append([k[0],k[1],v,check_tmp_cnt(k[0],k[1])])
    else:
        for i in range(N):
            for j in range(N):
                if maps[i][j] == -1:
                    return_arr.append([i,j,0,check_tmp_cnt(i,j)])
                    
    return_arr.sort(key= lambda x:(-x[2],-x[3],x[0],x[1]))
    return return_arr

input = sys.stdin.readline

N = int(input())

maps = [[-1 for _ in range(N)] for _ in range(N)]

childs = []

for _ in range(N*N):
    tmp = list(map(int,input().split()))
    childs.append(tmp)


child_pos_set = dict()

for i in range(N):
    for j in range(N):
        if check_tmp_cnt(i,j) == 4:
            maps[i][j] = childs[0][0]
            child_pos_set[childs[0][0]] = (i,j)
            break
    else:
        continue
    break

for child in childs[1:]:
    
    my_pos_arr = like_friend_tmp(child)
    child_pos_set[child[0]] = (my_pos_arr[0][0],my_pos_arr[0][1])
    maps[my_pos_arr[0][0]][my_pos_arr[0][1]] = child[0]
    
moves = [[-1,0],[1,0],[0,-1],[0,1]]

result_sum = []

for child in childs:
    x, y = child_pos_set[child[0]]
    like_set = dict()
    for c in child[1:]:
        like_set[c] = 1
    
    cnt = 0
    
    for move in moves:
        nx, ny = x + move[0], y + move[1]
        if 0 <= nx < N and 0 <= ny < N:
            if maps[nx][ny] in like_set:
                cnt += 1
    result_sum.append(cnt)

ans = 0
like_points = [0,1,10,100,1000]
for r_s in result_sum:
    ans += like_points[r_s]

print(ans)


