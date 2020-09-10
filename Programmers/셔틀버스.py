def convert_timetable(timetable) :
    ret = []
    for time in timetable:
        h,m = time.split(":")
        ret.append(int(h) * 60 + int(m))
    return ret

def convert_timestr(time) :
    h = time//60
    m = time%60
    if (h < 10) :
        h = "0" + str(h)
    if (m < 10 ) :
        m = "0" + str(m)
    return str(h) + ":" + str(m)

def solution(n, t, m, timetable):  
    timetable = sorted(convert_timetable(timetable))
    last_time = 540 + (n-1)*t
    for i in range(n) :
    
        if (m > len(timetable)) :
            return convert_timestr(last_time)
        elif (i == n-1) :
            if (timetable[0] > last_time):
                return convert_timestr(last_time)
            else : 
                return convert_timestr(timetable[m-1]-1)
        
        for j in range(m-1, -1, -1) :
            bus_time = 540 + i*t
            if (bus_time >= timetable[j]) :
                del timetable[j]