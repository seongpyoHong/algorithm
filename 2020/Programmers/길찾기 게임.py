import sys
sys.setrecursionlimit(10**6)

class Tree:
    def __init__(self, dataList):
        self.data = max(dataList, key=lambda x:x[1])
        leftList = list(filter(lambda x: x[0]< self.data[0], dataList))
        rightList = list(filter(lambda x : x[0] > self.data[0], dataList))
        if (len(leftList) != 0):
            self.left = Tree(leftList)
        else :
            self.left = None
        
        if (len(rightList) != 0) :
            self.right = Tree(rightList)
        else :
            self.right = None
            
def traverse_preorder(node,ret):
    ret.append(node.data)
    if (node.left != None) :
        traverse_preorder(node.left, ret)
    
    if (node.right != None):
        traverse_preorder(node.right, ret)

def traverse_postorder(node, ret) :
    if (node.left != None) :
        traverse_postorder(node.left, ret)
    if (node.right != None) :
        traverse_postorder(node.right, ret)
    ret.append(node.data)
    
def solution(nodeinfo):
    answer = []
    root = Tree(nodeinfo)
    preorder_list = []
    postorder_list = []
    traverse_preorder(root, preorder_list)
    answer.append(list(map(lambda x: nodeinfo.index(x)+1, preorder_list)))
    traverse_postorder(root, postorder_list)
    answer.append(list(map(lambda x: nodeinfo.index(x)+1, postorder_list)))

    return answer