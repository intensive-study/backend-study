number = input()
n = len(number)

# 해당 위치까지 왔을 때 만들어질 수 있는 단어의 수
dp = [0 for _ in range(n)]
if int(number[0]):
    dp[0] = 1

for i in range(1, n):
    # 현재 자리 숫자가 0이 아니라면
    if int(number[i]):
        dp[i] += dp[i - 1]
    # 이전 자리 숫자가 0이 아니고 현재 자리 숫자와 합친 값이 알파벳 범위 내라면
    if int(number[i - 1]) and 1 <= int(number[i - 1:i + 1]) <= 26:
        if i - 2 < 0:
            dp[i] += 1
            continue
        dp[i] += dp[i - 2]
    dp[i] %= 1000000

print(dp[-1])