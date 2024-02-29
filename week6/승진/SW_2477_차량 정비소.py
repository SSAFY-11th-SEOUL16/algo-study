T = int(input())

for tc in range(1, 1+T):

    N, M, K, A, B = map(int, input().split())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))
    t = list(map(int, input().split()))
    t_idx = 0
    len_t = len(t)

    visitor_info = [[-1, -1] for _ in range(K)]

    reception_desks = [-1] * N
    repair_desks = [-1] * M

    reception_waiting_line = []
    repair_waiting_line = []

    time = 0

    flag = True
    while flag:
        for i in range(M):
            if repair_desks[i] != -1 and repair_desks[i][1] == 0:
                repair_desks[i] = -1
        
        for i in range(N):
            if reception_desks[i] != -1 and reception_desks[i][1] == 0:
                repair_waiting_line.append(reception_desks[i][0])
                reception_desks[i] = -1
        
        for i in range(t_idx, len_t):
            if t[i] == time:
                reception_waiting_line.append(i)
            else:
                t_idx = i
                break
        
        for i in range(M):
            if repair_desks[i] == -1 and repair_waiting_line:
                visitor_idx = repair_waiting_line.pop(0)
                repair_desks[i] = [visitor_idx, b[i]-1]
                visitor_info[visitor_idx][1] = i
            elif repair_desks[i] != -1:
                repair_desks[i][1] -= 1
        
        for i in range(N):
            if reception_desks[i] == -1 and reception_waiting_line:
                visitor_idx = reception_waiting_line.pop(0)
                reception_desks[i] = [visitor_idx, a[i]-1]
                visitor_info[visitor_idx][0] = i
            elif reception_desks[i] != -1:
                reception_desks[i][1] -= 1

        time += 1
        if time > K:
            for i in range(K):
                if visitor_info[i][1] == -1:
                    break
            else:
                flag = False

    answer = 0
    for i in range(K):
        if visitor_info[i][0] == A-1 and visitor_info[i][1] == B-1:
            answer += i+1

    if answer == 0:
        answer = -1

    print(f'#{tc} {answer}')