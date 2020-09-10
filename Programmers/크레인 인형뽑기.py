def solution(board, moves):
    bucket = []
    for i in range(0,len(board)) :
        s = []
        for j in range(len(board)-1,-1,-1) :
            if (board[j][i] != 0) :
                s.append(board[j][i])
        bucket.append(s)
    
    result_bucket = []
    result = 0
    for i in moves :
        if (len(bucket[i-1]) != 0) : 
            item = bucket[i-1].pop()
            if (len(result_bucket) == 0 or result_bucket[-1] != item) :
                result_bucket.append(item)
            elif (result_bucket[-1] == item) :
                result_bucket.pop()
                result += 1;
    return result*2;