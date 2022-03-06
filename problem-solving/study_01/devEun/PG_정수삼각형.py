# <solution1>
def solution(triangle):
    
    for r in range(len(triangle)-1, -1, -1):
        for c in range(r):            
            triangle[r-1][c] += max(triangle[r][c], triangle[r][c+1])
    
    return triangle[0][0]

# <solution2> 
# : 위에서 부터 더할 경우 양쪽 끝에 숫자를 예외처리 + 마지막 열 max 수행으로 O(n)만큼 더 소요됨
# 따라서 solution1 : 아래 -> 위로 dp 수행

# def solution(triangle):
#     answer = 0

#     for i in range(1, len(triangle)):
#         for j in range(i+1):
#             leftP = triangle[i-1][0] if j == 0 else triangle[i-1][j-1]
#             rightP = triangle[i-1][j-1] if j == i else triangle[i-1][j]

#             # 각 원소에 더해지는 값은 왼쪽 부모와 오른쪽 부모 중에 큰 값
#             triangle[i][j] += max(leftP, rightP)

#     return max(triangle[-1])


triangle = [[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]
print(solution(triangle))