package main

import "fmt"

func main() {
	var truckWeights = []int{7,4,5,6}
	fmt.Println(solution(2, 10,truckWeights))
}

func solution(bridgeLength int, weight int, truckWeights []int) int {
	trucks := NewQueue(truckWeights)
	initBridge := make([]int, bridgeLength)
	bridge := NewQueue(initBridge)

	localSum := 0
	onBridgeCnt := 0
	time := 0
	for trucks.Length() > 0 {
		//Remove
		outTruck := bridge.Pop()
		localSum -= outTruck
		if outTruck > 0 {
			onBridgeCnt -= 1
		}

		// Append
		if localSum + trucks.Peek() <= weight && onBridgeCnt <= bridgeLength {
			currentTruck := trucks.Pop()
			localSum += currentTruck
			onBridgeCnt += 1
			bridge.Add(currentTruck)
		} else {
			bridge.Add(0)
		}

		time += 1
	}

	for localSum > 0 {
		outTruck := bridge.Pop()
		localSum -= outTruck
		time += 1
	}

	return time
}

type Queue struct {
	data []int
}

func (q Queue) Length() int {
	return len(q.data)
}

func (q *Queue) Pop() int {
	ret := q.data[0]
	q.data = q.data[1:]
	return ret
}

func (q* Queue) Add(in int) {
	q.data = append(q.data, in)
}

func (q Queue) Peek() int {
	return q.data[0]
}

func (q *Queue) MakeQueue(input []int) {
	for _,v := range input {
		q.data = append(q.data, v)
	}
}

func NewQueue(input []int) *Queue {
	queue := &Queue{data : []int{}}
	queue.MakeQueue(input)
	return queue
}

