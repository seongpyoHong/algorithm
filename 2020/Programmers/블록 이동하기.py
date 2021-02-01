from collections import deque

def move(p1, p2, board):
    direction = [(1,0), (-1,0), (0,1), (0,-1)]
    ret = []

    #move
    for d in direction:
        if (board[p1[0]+d[0]][p1[1]+d[1]] == 0 board[p2[0]+d[0]][p2[1]+d[1]] == 0) :
            ret.append({(p1[0] + d[0], p1[1] + d[1]),(p2[0] + d[0], p2[1] + d[1])})

    rotate = [1,-1]
    # rotate vertical
    if (p1[0] == p2[0]) :
        for r in rotate:
            if (board[p1[0] + r][p1[1]] ==0 and board[p2[0] + r][p2[1]] == 0) :
                ret.append({(p1[0], p1[1]), (p1[0] + r, p1[1])})
                ret.append({(p2[0], p2[1]), (p2[0] + r, p2[1])})

    # rotate horizontal
    else :
        for r in rotate:
            if (board[p1[0]][p1[1] + r] ==0 and board[p2[0]][p2[1] + r] == 0) :
                ret.append({ (p1[0], p1[1]), (p1[0], p1[1] + r) })
                ret.append({(p2[0], p2[1]), (p2[0], p2[1] + r)})
    return ret
    
def solution(board) :
    n = len(board)

    # Initialize
    extend_board = [[1 for _ in range(n+2)] for _ in range(n+2)]
    for i in range(n):
        for j in range(n):
            extend_board[i+1][j+1] = board[i][j]
    
    q = deque()
    visited = []

    q.append([{(1,1),(1,2)} , 0])
    visited.append({(1,1),(1,2)})

    while (len(q) != 0) :
        points, dist = q.popleft()
        points = list(points)
        dist += 1

        for m in move(points[0], points[1], new_board):
            if (n,n) in m : return dist

            if not m in visited :
                q.append([m,dist])
                visited.append(m)