Starting repository for `Data Structures` COMP20280 2025-2026
LISTS
Q6 - A singly linked list is collection of nodes and each node stores a reference to the next node, whereas a 
circularly linked list doesn't end at the last node, it instead links back to the first node in the list.

Q7 - You might use a linked list over an array when you need a dynamic size, as the number of nodes in a linked list
is not fixed.

Q8 - Can be useful for implementing queues as the last node links back to the first node
     Any application where a pointer to any node serve as a handle to the whole list

QUEUES AND STACKS
Q2 - 
     Stack inStack
     Stack outStack

     enqueue(x):
          inStack.push(x)
     
     dequeue():
          if outStack is empty:
               if inStack is empty:
                    return Queue is empty
               
               while inStack is NOT empty:
                    outStack.push(inStack.pop())

               return outStack.pop()

Q3 - Stack A
     Stack B
     Stack C

     while C is not empty:
        A.push(C.pop())

    while A is not empty:
        B.push(A.pop())

    while B is not empty:
        C.push(B.pop())


------------ BINARY TREES ----------------
Q2 - 
     externalNodes(tree)
          if tree.isempty()
               return 0
          return externalCount(tree, tree.root)

     externalCount(T, p)
          if position is null
               return 0

          if tree.left(position) is null and tree.right(position) is null
               return 1
     
          return externalCount(tree, tree.left(position)) + externalCount(tree, tree.right(position))

Q3 - 
     leftExternalNodes(tree)
          if tree.isempty()
               return 0
          return countLeftExternal(tree, tree.root(), false)

     countLeftExternal(tree, position, isLeftChild)
          if position is null
               return 0

          if tree.left(position) is null and tree.right(position) is null:
               if isLeftChild is true
                    return 1
               else
                    return 0
          
          return countLeftExternal(tree, tree.left(position), true) + countLeftExternal(tree, tree.right(position), false)

Q4 - 
Preorder - 
E
 \
  X
   \
    A
     \
      M
       \
        F
         \
          U
           \
            N

Inorder - 
      M
     / \
    X   U
   / \ / \
  E  A F  N

Postorder - 
        N
       /
      U
     /
    F
   /
  M
 /
A
/
X
/
E


Q5 - 
countDescendants(tree, position):
    if position is null:
        return 0

    total = 0

    L = tree.left(p)
    R = tree.right(p)

    if L is not null:
        total = total + 1
        total = total + countDescendants(tree, L)

    if R is not null:
        total = total + 1
        total = total + countDescendants(tree, R)

    return total
