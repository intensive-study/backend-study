# 배열 크기, 곱하는 횟수
n, m = map(int, input().split())
origin = []
for _ in range(n):
    origin.append(list(map(int, input().split())))
for i in range(n):
    for j in range(n):
        origin[i][j] = origin[i][j] % 1000


# 배열 곱셈
def multiply(board1, board2):
    new_board = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            temp = 0
            for k in range(n):
                temp += board1[i][k] * board2[k][j]
            new_board[i][j] = temp % 1000
    return new_board


# 분할 정복
def dfs(idx):
    if idx == 1:
        return origin

    if idx % 2 == 1:
        # 3 / 2 = 1.5
        # 3 // 2 = 1
        new_board = dfs(idx // 2)
        new_board = multiply(new_board, new_board)
        return multiply(new_board, origin)

    else:
        new_board = dfs(idx // 2)
        return multiply(new_board, new_board)


result_board = dfs(m)
for i in range(n):
    for j in range(n):
        print(result_board[i][j], end=' ')
    print()