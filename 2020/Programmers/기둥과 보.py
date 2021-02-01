def check(map) :
    for x,y,type in map :
        if (type == 0) :
            if (y == 0 or [x-1,y,1] in map or [x,y,1] in map or [x,y-1,0] in map) :
                continue
            else :
                return False
        else :
            if ([x,y-1,0] in map or [x+1, y-1,0] in map or ([x-1,y,1] in map and [x+1,y,1] in map)) : 
                continue
            else : 
                return False
    return True
def solution(n, build_frame):
    map = []
    ret = []
    for frame in build_frame :
        x,y,type,method = frame
        if (method == 1) :
            map.append([x,y,type])
            if (check(map)) :
                continue
            else : 
                map.remove([x,y,type])

        else :
            map.remove([x,y,type])
            if (check(map)) :
                continue
            else :
                map.append([x,y,type])
        
    map = sorted(map, key=lambda x:(x[0],x[1],x[2]))
    return map