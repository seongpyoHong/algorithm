from collections import OrderedDict;
nameList = {}

def updateNameState(uid, name) :
    nameList[uid] = name
    
def convertMethod(method) :
    if (method == "Leave") :
        return "나갔습니다."
    else :
        return "들어왔습니다."
    
def solution(record):
    outList = []
    inOutList = []
    for line in record:
        method = line.split(" ")[0]
        uid = line.split(" ")[1]
        inOutList.append([method,uid])
            
        if (method == "Leave") :
            outList.append(uid)
        
        elif (method == "Enter" or method == "Change") :
            name = line.split(" ")[2]
            updateNameState(uid, name)    

    ret = []
    for inOut in inOutList :
        if (inOut[0] != "Change") :
            ret.append(nameList[inOut[1]] + "님이 " + convertMethod(inOut[0]))
                
    return ret