def comb(cnt, total, block_maps):
    global total_blocks, broken_blocks
 
    if total == total_blocks:
        broken_blocks = total
        return
 
    if cnt == 0:
        if total > broken_blocks:
            broken_blocks = total
        return
 

    for i in range(W):
        arr = [x[:] for x in block_maps]
        stack = set()                   
        blocks = 0                      
 
        for row in range(H):
            if arr[row][i]:
                stack.add((row, i))
                break
                
        while stack:
            row, col = stack.pop()
            if not arr[row][col]:
                continue
 
            blocks += 1
            for k in range(4):
                next_row, next_col = row, col
                for _ in range(arr[row][col] - 1):
                    next_row += dx[k]
                    next_col += dy[k]
                    if 0 <= next_row < H and 0 <= next_col < W:
                        stack.add((next_row, next_col))
            arr[row][col] = 0
 
        for y in range(W):
            idx = H - 1
            for x in range(H - 1, -1, -1):
                if not arr[x][y]:
                    continue
                arr[idx][y], arr[x][y] = arr[x][y], arr[idx][y]
                idx -= 1
 
        comb(cnt-1, total+blocks, arr)
 
T = int(input())
 
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
 
for test_case in range(1, T+1):
    N, W, H = map(int, input().split())
    block_maps = [list(map(int, input().split())) for _ in range(H)]
    total_blocks = 0
 
    for i in range(H):
        for j in range(W):
            if block_maps[i][j]:
                total_blocks += 1
 
    broken_blocks = 0
    comb(N, 0, block_maps)
 
    answer = total_blocks-broken_blocks
 
    print(f'#{test_case} {answer}')