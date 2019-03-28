#29056kb, 60ms
part = 2
partNum = 0
partNumArr = []
arr = []

def searchPart(i, j) :
	global arr
	global cnt
	global part
	global partNum

	if (0 > i or i >= cnt or 0 > j or j >= cnt) :
		return False

		if arr[i][j] == 0 :
			arr[i][j] = -1
			return False

			elif arr[i][j] == 1 :
			partNum += 1
			arr[i][j] = part
			searchPart(i + 1, j)
			searchPart(i - 1, j)
			searchPart(i, j + 1)
			searchPart(i, j - 1)


			cnt = int(input())
			for i in range(cnt) :
				userInput = input()
				temp = []
				for a in userInput :
temp.append(int(a))
arr.append(temp)

for i in range(cnt) :
	for j in range(cnt) :
		if arr[i][j] == 1 :
			searchPart(i, j)
			if partNum != 0 :
				part += 1
				partNumArr.append(partNum)
				partNum = 0;

print(part - 2)
partNumArr.sort()
for num in partNumArr :
print(num)