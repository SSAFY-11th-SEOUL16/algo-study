def dfs(x, y, path, way):
    global cnt, i, j

    if way == 3 and x == i and y == j and len(path) > 2:
        cnt = max(cnt, len(path))
        return

    if 0 <= x < N and 0 <= y < N and cafe[x][y] not in path:
        new_path = path + [cafe[x][y]]
        
        # 직진
        nx, ny = x+dxy[way][0], y+dxy[way][1]
        dfs(nx, ny, new_path, way)

        # 꺾는 경우
        if way < 3:
            nx, ny = x+dxy[way+1][0], y+dxy[way+1][1]
            dfs(nx, ny, new_path, way+1)
        

T = int(input())

for t in range(1, T+1):
    N = int(input())
    cafe = [list(map(int, input().split())) for _ in range(N)]

    dxy = [(1, 1), (1, -1), (-1, -1), (-1, 1)]

    cnt = -1
    for i in range(N):
        for j in range(N):
            dfs(i, j, [], 0)
    
    print('#{} {}'.format(t, cnt))