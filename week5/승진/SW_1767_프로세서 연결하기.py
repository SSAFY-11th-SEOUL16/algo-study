T = int(input())

def dfs(i,core):
    global g_cnt
    global max_core
    global start_pos

    print(i,core)

    temp = 0
    for m in maps:
        temp += m.count(2)
    if max_core == core:
        g_cnt = min(g_cnt, temp)
    elif max_core < core:
        max_core = core
        g_cnt = temp

    if i == len(start_pos):
        return
        
    
    

    for j in range(4):
        if not check(start_pos[i][0],start_pos[i][1],j):
            continue
        else:
            draw(start_pos[i][0],start_pos[i][1],j)
            dfs(i+1, core + 1)
            remove(start_pos[i][0],start_pos[i][1],j)
    dfs(i+1,core)

def check(x,y,move):
    while True:
        x, y = x + moves[move][0], y + moves[move][1]
        if x<0 or x>=N or y<0 or y>=N:
            return True
        if maps[x][y] != 0:
            return False

def draw(x,y,move):
    while True:
        x, y = x + moves[move][0], y + moves[move][1]
        if x<0 or x>=N or y<0 or y>=N:
            return
        maps[x][y] = 2
def remove(x,y,move):
    while True:
        x, y = x + moves[move][0], y + moves[move][1]
        if x<0 or x>=N or y<0 or y>=N:
            return
        maps[x][y] = 0

moves = [[1,0],[-1,0],[0,1],[0,-1]]

for tc in range(1, T+1):
    N = int(input())
    maps = []
    start_pos =[]
    g_cnt = 1000
    max_core = 0
    for i in range(N):
        temp = list(map(int, input().rstrip().split()))
        for j, t in enumerate(temp):
            if t == 1 and 0 < i < (N -1) and 0 < j < (N -1):
                start_pos.append((i,j))
        maps.append(temp)
    dfs(0,0)
    
    if g_cnt == 1000:
        g_cnt = 0

    print(f'#{tc} {g_cnt}')