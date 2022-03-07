import sys
input = sys.stdin.readline

global N, A

def divide(B):
    if B == 1: 
        return A
    
    X = divide(B//2) 
    
    if B & 1: 
        return multiply(multiply(X, X), A)
    else:
        return multiply(X, X)

def multiply(A1, A2):
    ret = [[0] * N for _ in range(N)]
        
    for i in range(N):
        for j in range(N):
            for k in range(N):
                ret[i][j] += A1[i][k] * A2[k][j]
            ret[i][j] %= 1000
                    
    return ret


N, B = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(N)]

result = divide(B)

if B == 1: #히든 케이스(A에 1000 이상의 값이 있는 경우) 처리
    for i in range(N):
        for j in range(N):
            result[i][j] %= 1000
            
for arr in result:
    print(*arr)