from itertools import combinations 
import sys
input = sys.stdin.readline

N, K = map(int, input().split()) #�ܾ�� N, ���ܾ� K
# a:0, c:2, i:8, n:13, t:19 
contain = [0, 2, 8, 13, 19]
words = [input() for _ in range(N)]
all = set() #combination �� ���
ans = 0

#string to bitmask
for i in range(N):
    word = words[i]
    words[i] = 0
    for c in word[4:-4]:
        bitPos = ord(c)-ord('a')
        all.add(bitPos)
        words[i] |= 1 << bitPos
    
if K > len(all.difference(contain)) + 5:
    #�ʿ��� ���� �̻� ������ ���� ���, ��� �ܾ� ����
    ans = N
        
if K >= 5: #�ʼ� ���ڸ� �����ϵ���
    containBit = 0 #combi���� �����ϴ� �ʼ� ����
    for n in contain:
        containBit |= 1 << n
    
    for combi in combinations(all.difference(contain), K - 5):
        cnt = 0 #�ش� �������� ����� �ִ� �ܾ�
        compare = containBit
        for i in combi:
            compare |= 1 << i
            
        for word in words:
            if word & compare == word:
                cnt += 1
        
        ans = max(ans, cnt)
    
print(ans)