from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    answer = float('inf')
    1,1,7,4
    moves = [[-1,0],[1,0],[0,-1],[0,1]]
    maps = [[0 for _ in range(102)] for _ in range(102)]
    
    tmp = []
    
    for r in rectangle:
        tmp.append([r[0]* 2,r[1] * 2,r[2] * 2,r[3] * 2])
    
    rectangle = tmp
    
    for r in rectangle:
        
        for i in range(r[0] + 1,r[2]):
            for j in range(r[1] + 1,r[3]):
                maps[i][j] = 2
        
        for i in range(r[0],r[2] + 1):
            if maps[i][r[1]] != 2:
                maps[i][r[1]] = 1
            if maps[i][r[3]]!= 2:
                maps[i][r[3]] = 1
            
        for i in range(r[1],r[3] + 1):
            if maps[r[0]][i] != 2:
                maps[r[0]][i] = 1
            if maps[r[2]][i]!= 2:
                maps[r[2]][i] = 1
        
    q = deque()
    
    q.append([characterX * 2, characterY * 2,0])
    while q:
        x, y ,cnt = q.pop()
        
        if x == itemX * 2 and y == itemY * 2:
            answer = min(answer, cnt)
            continue
        maps[x][y] = 2
        for move in moves:
            nx, ny = x + move[0], y + move[1]
            if maps[nx][ny] == 1:
                q.append([nx,ny,cnt+1])
                
        
    return answer // 2