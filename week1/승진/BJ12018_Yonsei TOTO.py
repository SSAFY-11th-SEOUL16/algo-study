import sys
input = sys.stdin.readline

n, m = map(int,input().rstrip().split())

result = 0

use_m = []

for _ in range(n):
    P, L = map(int,input().rstrip().split())
    tmp = list(map(int,input().rstrip().split()))
    person = P - L
    if person < 0 :
        use_m.append(1)
        continue
    tmp.sort()
    if tmp[person] > 36:
        continue
    use_m.append(tmp[person])
    
use_m.sort()
for um in use_m:
    if (m - um) < 0:
        break
    m -= um
    result += 1
print(result)