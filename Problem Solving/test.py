def get_min(x, y, z):
    if y == x + 1 and z == y + 1:
        return 0
    if y - x == 2 or z - y == 2:
        return 1
    return 2


def get_max(x, y, z):
    temp = max(y - x, z - y)
    return temp - 1


x, y, z = map(int, input().split())
ls = [x, y, z]
ls.sort()
x, y, z = ls
print(get_min(x, y, z))
print(get_max(x, y, z))