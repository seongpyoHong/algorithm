def check_collect(p) :
    s = []

    for i in p :
        if (len(s) == 0) :
            s.append(i)
        elif (i == ")" and s[-1] == "(") :
            s.pop()
        else :
            s.append(i)

    return len(s)    

def inverse_bracket(s):
    inverse_str = ""
    for i in s:
        if (i == "(") :
            inverse_str += ")"
        else :
            inverse_str += "("
    return inverse_str

def convert_bracket(p):
    result = ""
    left = 0
    right = 0
    u = ""
    v = ""
    if (len(p) == 0 or check_collect(p) == 0) :
        print(p)
        return p
    
    for index, value in enumerate(p) :
        if (value == "(") :
            left += 1
        else :
            right += 1
        
        if (left > 0 and right > 0 and left == right) :
            u = p[0:index+1]
            v = p[index+1: len(p)]
            break
            
    if (check_collect(u) == 0) :
        result += u+(convert_bracket(v))
        
    else :
        result += ("(" + convert_bracket(v) + ")" + inverse_bracket(u[1:-1]))
    
    return result
def solution(p):
        return convert_bracket(p)