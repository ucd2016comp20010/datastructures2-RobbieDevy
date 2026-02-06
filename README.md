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


