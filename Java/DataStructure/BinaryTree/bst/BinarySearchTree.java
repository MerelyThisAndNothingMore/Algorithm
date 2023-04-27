package Java.DataStructure.BinaryTree.bst;

import Java.DataStructure.BinaryTree.BinaryTree;

import java.util.Comparator;

/**
 * @author: MerelyThis
 * @since: 2023/4/25 周二
 * @description:
 */
public class BinarySearchTree<E> extends BinaryTree<E> implements IBinarySearchTree<E> {

    private Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 这种情况下，E必须实现Comparable接口
     */
    public BinarySearchTree() {
        this(null);
    }

    @Override
    public void add(E element) {
        checkElementNotNull(element);

        if (root == null) {
            root = createNode(element, null);
            size++;
            onAdded(root);
            return;
        }
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        // 查找添加位置
        while (node != null) {
            parent = node;
            cmp = compareTo(node.element, element);
            if (cmp > 0) {
                // to left
                node = node.left;
            } else if (cmp < 0) {
                // to right
                node = node.right;
            } else {
                node.element = element;
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        if (cmp < 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        onAdded(newNode);
    }

    @Override
    public void remove(E element) {
        remove(getNode(element));
    }

    @Override
    public boolean contains(E element) {
        return getNode(element) != null;
    }

    protected void onAdded(Node<E> node){}

    protected void onRemoved(Node<E> node){}

    /**
     * - 叶子结点-直接删除
     * - 度为1-子结点代替
     * - 度为2
     * - 先用前驱结点/后继结点覆盖原节点值
     * - 删除对应前驱结点/后继结点
     * 由于度为2的点，其前驱/后继结点一定在左/右子树中，且没有右/左结点，可以转化为度为1的情况。
     */
    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }

        size--;

        // 后继结点一定在右子树中，且一定没有左结点
        if (node.hasTwoChildren()) {
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }

        // node的度为1
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node.parent.left == node) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) {
            root = null;
        } else {
            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }

        onRemoved(node);
    }

    private Node<E> getNode(E element) {
        checkElementNotNull(element);

        Node<E> node = root;
        // 二叉搜索树具有排序性
        while (node != null) {
            int cmp = compareTo(node.element, element);
            if (cmp > 0) {
                // node.element 更大，左子树
                node = node.left;
            } else if (cmp < 0) {
                // node.element 更小，右子树
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    private int compareTo(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void checkElementNotNull(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
