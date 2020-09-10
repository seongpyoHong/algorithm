def convert_to_microsec(t):
    h,m,s = t.split(":")    
    micro = int(h)*60*60*1000 + int(m)*60*1000 + int(s.split(".")[0])*1000 + int(s.split(".")[1])
    return micro


def get_traffic(duration, duration_list):
    max_traffic = 0
    for time in duration :
        start = time
        end = time + 1000
        count = 0
        for d in duration_list :
            if ((start <= d[0] and d[0] < end) or (start <= d[1] and d[1] < end)) :
                count += 1
        max_traffic = max(max_traffic, count);
    
    return max_traffic
def solution(lines):
    count_lst = []
    duration_lst = []
    for line in lines :
        end = convert_to_microsec(line.split(" ")[1])
        start = end - int(float(line.split(" ")[2][:-1]) * 1000) + 1
        duration = [start, end]    
        duration_lst.append(duration)
    
    duration_lst = sorted(duration_lst, key=lambda duration: duration[0])
    for duration in duration_lst :
        count_lst.append(get_traffic(duration, duration_lst))

    return  max(count_lst)