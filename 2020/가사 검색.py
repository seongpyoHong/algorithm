from collections import OrderedDict
wordByLength = OrderedDict()
wordByLengthInverse = OrderedDict()

def compareWord(query, word) :
    for i in range(len(query)) :
        if (query[i] != word[i] and query[i] != "?") :
            return False
        elif (query[i] == "?") :
            return True      
    return True

def countWord(query) : 
    cnt = 0
    if (len(query) in wordByLength.keys()) : 
        words = wordByLength[len(query)]
        for word in words :
            if (compareWord(query, word)) :
                cnt += 1
            elif (query[0] < word[0]) :
                break
        return cnt
    else :
        return 0

def compareWordInverse(query, word) :
    for i in range(len(query)-1,-1) :
        if (query[i] != word[i] and query[i] != "?") :
            return False
        elif (query[i] == "?") :
            return True      
    return True

def countWordInverse(query) : 
    cnt = 0
    if (len(query) in wordByLengthInverse.keys()) :
        words = wordByLengthInverse[len(query)]
        for word in words :
            if (compareWordInverse(query, word)) :
                cnt += 1
            elif (query[-1] < word[-1]) :
                break
    else : 
        return 0
    
def solution(words, queries):
    for word in words :
        length = len(word)

        # common order
        if (length in wordByLength.keys()) :
            wordByLength[length].append(word)
        else :
            wordByLength[length] = [word]
        
        # inverse order
        if (length in wordByLengthInverse.keys()) :
            wordByLengthInverse[length].append(word[::-1])
        else :
            wordByLengthInverse[length] = [word[::-1]]
    
    for v in wordByLength.values() :
        v.sort()
    
    for v in wordByLengthInverse.values() :
        v.sort()
    
    print(wordByLengthInverse)
    ret = []
    for query in queries :
        if (query[0] != "?") :
            ret.append(countWord(query))
        else : 
            ret.append(countWordInverse(query))
    return ret