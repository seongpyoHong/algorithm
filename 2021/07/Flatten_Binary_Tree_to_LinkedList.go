func flatten(root *TreeNode)  {
    if (root == nil) {
        return
    }
    
    preOrderList := make([]*TreeNode, 0)
    visited := Stack{entries: make([]*TreeNode, 0)}
    visited.Push(root)    
    
    for {
        if (visited.IsEmpty()) {
            break
        }
        
        current := visited.Pop()
        preOrderList = append(preOrderList, current)
        
        if (current.Right != nil) {
            visited.Push(current.Right)
        }
        
        if (current.Left != nil) {
            visited.Push(current.Left)            
        }
    }
    
    idx := 0
    for ; idx < len(preOrderList) - 1; idx++ {
        curNode := preOrderList[idx]
        curNode.Left = nil
        curNode.Right = preOrderList[idx + 1]
    }
    
    preOrderList[idx].Left = nil
    preOrderList[idx].Right = nil
}

type Stack struct {
    entries []*TreeNode
}

func (s *Stack) Pop() *TreeNode{
    ret := s.entries[len(s.entries) - 1]
    s.entries = s.entries[:len(s.entries) - 1]
    
    return ret
}

func (s *Stack) Push(node *TreeNode) {
    s.entries = append(s.entries, node)
}

func (s Stack) IsEmpty() bool {
    return len(s.entries) == 0
}
