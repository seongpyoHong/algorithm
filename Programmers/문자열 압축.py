def solution(s):
    res = 1001
    if (len(s) == 1 or len(s) == 2) :
        return len(s)

    for word_len in range(1,len(s)+1) :
        candidate = [s[j:j+word_len] for j in range(0, len(s), word_len)]
        word_cnt = 1
        compact_len = len(s)
        for i in range(0, len(candidate)-1) :
            if (candidate[i] == candidate[i+1]) :
                word_cnt += 1
                
            elif (word_cnt != 1):
                compact_len +=  (len(str(word_cnt)) - ((word_cnt-1)*word_len))        
                word_cnt = 1
                
        if (word_cnt != 1) :
            compact_len += (len(str(word_cnt)) - ((word_cnt-1)*word_len))
        
        res = min(res,compact_len)
            
    return res