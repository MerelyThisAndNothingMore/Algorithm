package Java.DataStructure.BinaryTree.bbst;

import Java.DataStructure.BinaryTree.bst.BinarySearchTree;

import java.util.Comparator;

/**
 * @author: MerelyThis
 * @since: 2023/4/26 周三
 * @description: 平衡二叉树在添加和删除后需要主动调整平衡。
 */
public class BaseBBST<E> extends BinarySearchTree<E> implements IBBST<E> {

    public BaseBBST(Comparator<E> comparator) {
        super(comparator);
    }

    public BaseBBST() {
        this(null);
    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) { // grand是左子树
            grand.parent.left = parent;
        } else if (grand.isRightChild()) { // grand是右子树
            grand.parent.right = parent;
        } else { // grand是root节点
            root = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }

    protected void rotate(
            Node<E> r, // 子树的根节点
            Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f) {
        // 让d成为这棵子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        //b-c
        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}
