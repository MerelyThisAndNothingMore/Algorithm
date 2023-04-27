package Java.DataStructure.BinaryTree;

import Java.DataStructure.BinaryTree.printer.BinaryTreeInfo;
import Java.DataStructure.Queue.Queue;


/**
 * @author: MerelyThis
 * @since: 2023/4/25 周二
 * @description:
 */
public class BinaryTree<E> implements IBinaryTree<E>, BinaryTreeInfo {

    protected int size;
    protected Node<E> root;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void preorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        preorder(root, visitor);
    }

    @Override
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        inorder(root, visitor);
    }

    @Override
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        postorder(root, visitor);
    }

    @Override
    public void levelOrder(Visitor<E> visitor) {
        // 借助队列
        if (visitor == null || root == null) {
            return;
        }
        Queue<Node<E>> queue = new Queue<>();
        queue.enQueue(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.deQueue();
            if (visitor.visit(node.element)) {
                return;
            }
            if (node.left != null) {
                queue.enQueue(node.left);
            }
            if (node.right != null) {
                queue.enQueue(node.right);
            }
        }
    }

    @Override
    public int height() {
        return heightRecursion(root);
    }

    @Override
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new Queue<>();
        queue.enQueue(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.deQueue();
            if (leaf && !node.isLeaf()) { // 要求是叶子结点，但是当前节点不是叶子结点
                return false;
            }
            if (node.left != null) {
                queue.enQueue(node.left);
            } else if (node.right != null) {
                // node.left==null && node.right!=null
                return false;
            }
            if (node.right != null) {
                queue.enQueue(node.right);
            } else {
                // node.left==null && node.right==null
                // node.left!=null && node.right==null
                leaf = true; // 要求后面都是叶子节点
            }
        }
        return true;
    }

    protected void preorder(Node<E> node, Visitor<E> visitor) {
        // 根左右
        if (node == null || visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    protected void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        // 左
        inorder(node.left, visitor);
        // 根
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        // 右
        inorder(node.right, visitor);
    }

    protected void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        // 左
        postorder(node.left, visitor);
        // 右
        postorder(node.right, visitor);
        // 根
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 获取中序遍历前驱结点
     *
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        // 前驱结点只可能在左子树最右节点或其右子树最小结点为node
        if (node.left != null) {
            // 左子树不为空,则找到它的最右节点
            Node<E> p = node.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 找到一个节点，它的右子树最小结点为node
        while (node.parent != null && node.parent.left == node) {
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 获取中序遍历后继结点
     *
     * @param node
     * @return
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }

        // 右子树最左节点
        if (node.right != null) {
            Node<E> p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }

        return node.parent;
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent); // 默认返回一个通用节点
    }

    private int heightRecursion(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(heightRecursion(node.left), heightRecursion(node.right));
    }

    private int heightIteration() {
        // 借助层次遍历
        if (root == null) return 0;
        int levelSize = 1; // 存储每一层的元素数量
        int height = 0; // 树的高度
        Queue<Node<E>> queue = new Queue<>();
        queue.enQueue(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.deQueue();
            levelSize--;
            if (node.left != null) {
                queue.enQueue(node.left);
            }
            if (node.right != null) {
                queue.enQueue(node.right);
            }
            if (levelSize == 0) { // 即将要访问下一层
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    public static class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() { // 判断自己是不是左子树
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() { // 判断自己是不是右子树
            return parent != null && this == parent.right;
        }

        public Node<E> sibling() { // 返回兄弟节点
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentStr = "null";
        if (myNode.parent != null) {
            parentStr = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentStr + ")";
    }
}
