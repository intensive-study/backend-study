# 물건 개수, 최대 무게
n, k = map(int, input().split())

arr = [[]]
for i in range(n):
    arr.append(list(map(int, input().split())))

dp = [[0 for _ in range(k + 1)] for _ in range(n + 1)]

for i in range(1, n + 1):
    for j in range(1, k + 1):
        if j >= arr[i][0]:
            dp[i][j] = max(arr[i][1] + dp[i - 1][j - arr[i][0]], dp[i - 1][j])
        else:
            dp[i][j] = dp[i - 1][j]

for i in dp:
    print(i)
print(max(dp[-1]))