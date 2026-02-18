package project20280.tree;

import project20280.interfaces.BinaryTree;
import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract base class providing some functionality of the BinaryTree interface.
 * <p>
 * The following five methods remain abstract, and must be implemented
 * by a concrete subclass: size, root, parent, left, right.
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E>
        implements BinaryTree<E> {

    /**
     * Returns the Position of p's sibling (or null if no sibling exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the sibling (or null if no sibling exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> sibling(Position<E> p) {
        // TODO
        Position<E> parent = parent(p);
        if (parent == null) return null;          // p is root
        if (p == left(parent)) return right(parent);
        else return left(parent);
    }

    /**
     * Returns the number of children of Position p.
     *
     * @param p A valid Position within the tree
     * @return number of children of Position p
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public int numChildren(Position<E> p) {
        // TODO
        int count = 0;
        if (left(p) != null) count++;
        if (right(p) != null) count++;
        return count;
    }

    /**
     * Returns an iterable collection of the Positions representing p's children.
     *
     * @param p A valid Position within the tree
     * @return iterable collection of the Positions of p's children
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<>(2);    // max capacity of 2
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }

    /**
     * Adds positions of the subtree rooted at Position p to the given
     * snapshot using an inorder traversal
     *
     * @param p        Position serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        // TODO
        if (left(p) != null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if (right(p) != null)
            inorderSubtree(right(p), snapshot);
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in inorder.
     *
     * @return iterable collection of the tree's positions reported in inorder
     */
    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            inorderSubtree(root(), snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Returns an iterable collection of the positions of the tree using inorder traversal
     *
     * @return iterable collection of the tree's positions using inorder traversal
     */
    @Override
    public Iterable<Position<E>> positions() {
        return inorder();
    }

    /**
     * Returns the diameter of the tree (number of edges on the longest path
     * between any two nodes). For number of nodes on that path, add 1 (if non-empty).
     */
    public int diameter() {
        if (isEmpty()) return 0;
        return diameterHelper(root())[1];
    }

    /**
     * Returns int[]{height, diameter} for subtree rooted at p.
     * Height and diameter are measured in EDGES.
     *
     * Convention: null subtree has height = -1 and diameter = 0.
     * Therefore, a leaf node ends up with height = 0.
     */
    private int[] diameterHelper(Position<E> p) {
        if (p == null) return new int[]{-1, 0};

        int[] L = diameterHelper(left(p));
        int[] R = diameterHelper(right(p));

        int height = 1 + Math.max(L[0], R[0]);

        // longest path that goes through p (may use one side or both)
        int throughP = L[0] + R[0] + 2;

        int diameter = Math.max(Math.max(L[1], R[1]), throughP);

        return new int[]{height, diameter};
    }

    // EXTERNAL NODE COUNT
    public int externalNodes() {
        if (isEmpty()) return 0;
        return externalCount(root());
    }

    private int externalCount(Position<E> p) {
        Position<E> L = left(p);
        Position<E> R = right(p);

        if (L == null && R == null) return 1;

        int count = 0;
        if (L != null) count += externalCount(L);
        if (R != null) count += externalCount(R);
        return count;
    }
}

