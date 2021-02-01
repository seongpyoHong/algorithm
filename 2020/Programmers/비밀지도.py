def decode(num, n) :
    ret = ""
    y = ""
    while num > 0:
        y = str(num%2)+y
        num //= 2
    
    for i in y.zfill(n) :
        if (i == "0") :
            ret += " "
        else :
            ret += "#"
    return ret
def find_secret_map(str1, str2) :
    ret = ""
    for i in range(0, len(str1)):
        if (str1[i] == "#" or str2[i] == "#"):
            ret += "#"
        else : 
            ret += " "
    return ret

def solution(n, arr1, arr2):
    decode1 = []
    decode2 = []
    for i in arr1:
        decode1.append(decode(i,n))
    for j in arr2 :
        decode2.append(decode(j,n))
    
    ret = []
    for i in range(0,n) :
        ret.append(find_secret_map(decode1[i], decode2[i]))
            
    return ret