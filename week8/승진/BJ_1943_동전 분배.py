for _ in range(3):
    total = 0
    coins = [] 
   
    
    for _ in range(int(input())):
        C, N = map(int, input().split())
        total += C * N
        coins.append([C, N])

    
    if total % 2 == 1:
        print(0)
        continue

    total //= 2
    
    dp = [True] + [False] * total

    answer = 0
    for i in range(len(coins)):
        C, N = coins[i]
        
        
        for n in range(total, C-1, -1):
            if dp[n-C]:
                for j in range(N):
                    if n + C * j <= total:
                        dp[n + C * j] = True
                    else:
                        break
                        
        if dp[-1]:
            answer = 1
            break

    print(answer)