import copy
def rotate(key):
    rotate_key = copy.deepcopy(key)
    
    y = len(key) - 1
    for i in range(len(key)):
        x = 0
        for j in range(len(key)):
            rotate_key[x][y]= key[i][j]
            x = x + 1
        y = y-1
    return rotate_key

def solution(key, lock):
    M = len(key)
    N = len(lock)
    
    # Initialize
    key_90 = rotate(key)
    key_180 = rotate(key_90)
    key_270 = rotate(key_180)
    key_list = [key, key_90, key_180, key_270]
    hole_cnt = 0
    for i in range(N):
        hole_cnt += lock[i].count(0)
    lock_plus = [[ -1 for _ in range(2*M+N)] for _ in range(2*M+N)]
    for i in range (M,M+N):
        for j in range (M, M+N):
            lock_plus[i][j] = lock[i-M][j-M]
    
    # make 4 direction keys
    for x_start in range(M+N) :
        for y_start in range(M+N) :
            for key in key_list :
                is_fit = True
                lock_hole_cnt = hole_cnt
                for x in range(M):
                    for y in range(M):
                        if (key[x][y] == 1 and lock_plus[x_start+x][y_start+y] == 1) :
                            is_fit = False
                            break
                        elif (key[x][y] == 1 and lock_plus[x_start+x][y_start+y] == 0) :
                            lock_hole_cnt -= 1
                    if (not is_fit):
                        break
                if (lock_hole_cnt == 0):
                    return True
                        
    return False