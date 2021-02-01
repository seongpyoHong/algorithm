def solution(N, stages):
    analysis = []
    for i in range(0, N) :
        analysis.append([0,0])

    ret = []
    failed_cnt = 0
    total = len(stages)
    for i in range (1,N+1) :
        analysis[i-1][0] = total - failed_cnt
        analysis[i-1][1] = stages.count(i)
        failed_cnt += analysis[i-1][1]
        ret.append([i,analysis[i-1][0],analysis[i-1][1]])
        
    for i in ret:
        if i[1] == 0:
            i[1] =1
            
    sorted_stage = sorted(ret, key=lambda x: (x[2]/x[1], -x[0]), reverse=True)
    
    result = []
    for i in sorted_stage :
        result.append(i[0])
    return result