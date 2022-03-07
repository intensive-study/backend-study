t = int(input())

dp = [[0, 0] for _ in range(41)]
dp[0] = [1, 0]
dp[1] = [0, 1]

for i in range(2, 41):
    a = dp[i - 1][0] + dp[i - 2][0]
    b = dp[i - 1][1] + dp[i - 2][1]
    dp[i] = [a, b]

for i in range(t):
    n = int(input())
    print(*dp[n])