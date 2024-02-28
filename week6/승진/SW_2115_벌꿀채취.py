from itertools import combinations

T = int(input())

for tc in range(1, T + 1):

    N, M, C = map(int, input().split())


    maps = []

    dp =[[0 for _ in range(N)] for _ in range(N)]


    for _ in range(N):
        maps.append(list(map(int, input().split())))
    

    for i in range(N):
        
        for j in range(0,N-M+1):
            max_num = 0
            for z in range(1,M+1):
                for combi in combinations(maps[i][j:j+M], z):
                    sum_num = 0
                    for c in combi:
                        sum_num += c*c
                    if sum(combi) <= C and sum_num >=max_num:
                        max_num = sum_num
            dp[i][j] = max_num
    
    max_value = 0
    for i,d in enumerate(dp):
        for j,n in enumerate(d):
            sum_num = n
            temp_arr = [0]
            temp_arr.extend(d[j+M:])
            temp_arr.extend(sum(dp[i+1:],[]))
            max_value = max(max_value,sum_num + max(temp_arr))
    
    print(f'#{tc} {max_value}')