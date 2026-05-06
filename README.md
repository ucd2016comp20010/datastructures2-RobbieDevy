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


------------ TREES 1 ----------------
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

------------------- RECURSION --------------------
Q1 - ReverseArray(A, 0, 10)   (swap A[0] and A[10])
     swap 12 and 15  A = {15, 5, 19, 6, 11, 3, 9, 34, 2, 1, 12}
          ReverseArray(A, 1, 9)  (swap A[1] and A[9])
          swap 5 and 1 so A = {15, 1, 19, 6, 11, 3, 9, 34, 2, 5, 12}
               ReverseArray(A, 2, 8) (swap A[2] and A[8])
               swap 19 and 2 so A = {15, 1, 2, 6, 11, 3, 9, 34, 19, 5, 12}
                    ReverseArray(A, 3, 7) (swap A[3] and A[7])
                    swap 6 and 34 so A = {15, 1, 2, 34, 11, 3, 9, 6, 19, 5, 12}
                         ReverseArray(A, 4, 6) (swap A[4] and A[6])
                         swap 11 and 9 so A = {15, 1, 2, 34, 9, 3, 11, 6, 19, 5, 12}
                              ReverseArray(A, 5, 5) (stop)
                              return
                         return
                    return
               return
          return
     return

Q2 - F(5)
          F(4)
               F(3)
                    F(2)
                         F(1) -> 1
                         F(0) -> 0
                    return F(2) = 1 + 0 = 1
                    F(1) -> 1
               return F(3) = 1 + 1 = 2
               F(2)
                    F(1) -> 1
                    F(0) -> 0
               return F(2) = 1 + 0 = 1
          return F(4) = 2 + 1 = 3
               F(3)
                    F(2)
                         F(1) -> 1
                         F(0) -> 0
                    return F(2) = 1 + 0 = 1
                    F(1) -> 1
               return F(3) = 1 + 1 = 2
     return F(5) = 3 + 2 = 5


Q6(a) - 
function reverse()
    head <- reverseRecursive(head)
end function

function reverseRecursive(node)
    if node is null OR node.next is null then
        return node
    end if

    newHead <- reverseRecursive(node.next)
    node.next.next <- node
    node.next <- null
    return newHead
end function



---------------- HASH MAPS -------------
Question 3:
Computations
12: (3*12 + 5) % 11 = 41 % 11 = 8
44: (3*44 + 5) % 11 = 137 % 11 = 5
13: (3*13 + 5) % 11 = 44 % 11 = 0
88: (3*88 + 5) % 11 = 269 % 11 = 5
23: (3*23 + 5) % 11 = 74 % 11 = 8
94: (3*94 + 5) % 11 = 287 % 11 = 1
11: (3*11 + 5) % 11 = 38 % 11 = 5
39: (3*39 + 5) % 11 = 122 % 11 = 1
20: (3*20 + 5) % 11 = 65 % 11 = 10
16: (3*16 + 5) % 11 = 53 % 11 = 9
5: (3*5 + 5) % 11 = 20 % 11 = 9

0 : 13
1 : 94 -> 39
2 : empty
3 : empty
4 : empty
5 : 44 -> 88 -> 11
6 : empty
7 : empty
8 : 12 -> 23
9 : 16 -> 5
10: 20

Question 4:
Computations:
12: (312 + 5) % 19 = 41 % 19 = 3
44: (344 + 5) % 19 = 137 % 19 = 4
13: (313 + 5) % 19 = 44 % 19 = 6
88: (388 + 5) % 19 = 269 % 19 = 3
23: (323 + 5) % 19 = 74 % 19 = 17
94: (394 + 5) % 19 = 287 % 19 = 2
11: (311 + 5) % 19 = 38 % 19 = 0
39: (339 + 5) % 19 = 122 % 19 = 8
20: (320 + 5) % 19 = 65 % 19 = 8
16: (316 + 5) % 19 = 53 % 19 = 15
5: (3*5 + 5) % 19 = 20 % 19 = 1

0: 11
1: 5
2: 94
3: 12 -> 88
4: 44 
5: empty
6: 13
7: empty
8: 39 -> 20
9: empty
10: empty
11: empty
12: empty
13: empty
14: empty
15: 16
16: empty
17: 23
18: empty