package Java.DataStructure.BinaryTree.bbst;

import Java.DataStructure.BinaryTree.BinaryTree;
import Java.DataStructure.BinaryTree.bst.BinarySearchTree;

import java.util.Comparator;

/**
 * @author: MerelyThis
 * @since: 2023/4/26 周三
 * @description:
 */
public class AVLTree<E> extends BinarySearchTree<E> implements IBBST<E> {

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    public AVLTree() {
        this(null);
    }

    @Override
    protected void onAdded(Node<E> node) {
        while ((node = node.parent) != null) {
            // 新加入的节点height为0
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
                break;
            }
        }
    }

    @Override
    protected void onRemoved(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }


    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {//L
            if (node.isLeftChild()) {//LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            } else {//LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else {//R
            if (node.isLeftChild()) {//RL
                rotate(grand, grand, node.left, node, node.right, parent);
            } else {//RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
    }

    /**
     * 最终的效果：
     *      d
     *    b   f
     *     c e
     *
     * b、c、d、e、f为大小排序的2、3、4、5、6位。
     * r为根结点。
     */
    private void rotate(
            Node<E> r, // 子树的根节点
            Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f) {
        // 让d成为这颗子树的根结点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        // c为b的右子树
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        updateHeight(b);

        // e为f的左子树
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        updateHeight(f);

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
    }


    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() { // 更新高度
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (rightHeight > leftHeight) return right;
            // 高度一样则返回同方向的，左子节点则返回左，否则返回右
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }

}
