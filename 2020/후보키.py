from itertools import combinations

def isUniqueKey(key, relation) :
    check = set()
    for row in relation :
        data = ""
        for i in range(len(key)) :
            data += row[key[i]]
        if (data in check) : 
            return False
        check.add(data) 
    return True

def solution(relation):
    columnSize = len(relation[0])
    columns = [i for i in range(columnSize)]
    combination = []
    for i in range(1, columnSize+1) :
        for j in list(combinations(columns, i)) :
            combination.append(j)
    
    uniqueKeys = []
    for key in combination :
        if (isUniqueKey(key, relation)) :
            uniqueKeys.append(key)
    
    minimumKeys = []
    for uniqueKey in uniqueKeys :
        isMinimumKey = True
        for minimumKey in minimumKeys :
            if (set(minimumKey).issubset(set(uniqueKey))) :
                isMinimumKey = False
                break
        
        if (isMinimumKey) :
            minimumKeys.append(uniqueKey)
    print(minimumKeys)
    return len(minimumKeys)