n, k = map(int, input().split())
dp = [[0] * (k+1) for _ in range(n+1)] #(n+1) X (k+1) �迭 �ʱ�ȭ

#���� �ϳ��� ������ üũ
for i in range(1, n+1):
    w, v = map(int, input().split())
    for j in range(1, k+1):
        if j < w :
            # �ش� ��ǰ�� �賶 ���� ���Ժ��� Ŭ �� ���� �� �״��
            dp[i][j] = dp[i-1][j]
        else :
            # �賶�� �� ���Զ��, ���� ���� ���� ���� ������ �־��� �� ���� ��
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-w] + v)
