def update_position(x, y, move):
    nx = x + moves[move][0]
    ny = y + moves[move][1]
    
    if nx == 0 or ny == 0 or nx == N - 1 or ny == N - 1:
        v_0, v_1 = mis_set[(x, y)]
        v_0 //= 2
        v_1 *= -1
        return nx, ny, (v_0, v_1)
    else:
        return nx, ny, mis_set[(x, y)]

T = int(input())
for test_case in range(1, T + 1):
    N, M, K = map(int,input().rstrip().split(" "))
    mis_set = dict()
    moves = {1: (-1, 0), -1: (1, 0), 2: (0, -1), -2: (0, 1)}
    total_sum = 0
    
    for _ in range(K):
        x, y, c, m = map(int,input().rstrip().split(" "))
        if m == 2:
            m = -1
        elif m == 3:
            m = 2
        elif m == 4:
            m = -2
        mis_set[(x, y)] = (c, m)

    for i in range(M):
        tmp_set = dict()
        
        for (x, y), value in mis_set.items():
            nx, ny, updated_value = update_position(x, y, value[1])
            nkey = (nx, ny)
            
            if nkey in tmp_set:
                tmp_set[nkey].append(updated_value)
            else:
                tmp_set[nkey] = [updated_value]
                
                
        mis_set.clear()
        
        
        for k, v in tmp_set.items():
            if len(v) >= 2:
                s_v = sum(val[0] for val in v)
                tmp_tuple = (s_v, max(v)[1])
                mis_set[k] = tmp_tuple
            else:
                mis_set[k] = v[0]
        
        
    
    total_sum = sum(value[0] for value in mis_set.values())
    print(f'#{test_case} {total_sum}')