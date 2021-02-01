distance = [[0,0],[1,1], [2,2], [3,1], [4,2], [5,3], [6,2], [7,3], [8,4], [9,3],[10,4]]

def convert_to_distance(abs_val) :
    for i in distance :
        if (i[0] == abs_val) :
            return i[1]

def select_hand(number, left, right, hand) :
    abs_left = convert_to_distance(abs(number-left))
    abs_right = convert_to_distance(abs(number-right))
    
    if (abs_left > abs_right) :
        return "R"
    elif (abs_left < abs_right) :
        return "L"
    else : 
        return hand
    
def solution(numbers, hand):
    result = ""
    left_pos = 10
    right_pos = 12
    if (hand == "right") :
        hand = "R"
    else : 
        hand = "L"
        
    for number in numbers :
        if (number == 0) :
            number = 11
        if (number == 7 or number == 4 or number == 1) :
            result += "L"
            left_pos = number
        elif (number == 9 or number == 6 or number == 3) :
            result += "R"
            right_pos = number
        else :
            selected_hand = select_hand(number, left_pos, right_pos, hand)
            result += selected_hand
            if (selected_hand == "L") :
                left_pos = number
            else :
                right_pos = number
        
    return result