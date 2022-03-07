# <solution1>
def solution(triangle):
    
    for r in range(len(triangle)-1, -1, -1):
        for c in range(r):            
            triangle[r-1][c] += max(triangle[r][c], triangle[r][c+1])
    
    return triangle[0][0]

# <solution2> 
# : ������ ���� ���� ��� ���� ���� ���ڸ� ����ó�� + ������ �� max �������� O(n)��ŭ �� �ҿ��
# ���� solution1 : �Ʒ� -> ���� dp ����

# def solution(triangle):
#     answer = 0

#     for i in range(1, len(triangle)):
#         for j in range(i+1):
#             leftP = triangle[i-1][0] if j == 0 else triangle[i-1][j-1]
#             rightP = triangle[i-1][j-1] if j == i else triangle[i-1][j]

#             # �� ���ҿ� �������� ���� ���� �θ�� ������ �θ� �߿� ū ��
#             triangle[i][j] += max(leftP, rightP)

#     return max(triangle[-1])


triangle = [[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]
print(solution(triangle))