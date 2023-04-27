package Java.DataStructure.BinaryTree;

/**
 * @author: MerelyThis
 * @since: 2023/4/25 周二
 * @description:
 */
public interface IBinaryTree<E> {

    int size();

    boolean isEmpty();

    void clear();

    void preorder(Visitor<E> visitor);

    void inorder(Visitor<E> visitor);

    void postorder(Visitor<E> visitor);

    void levelOrder(Visitor<E> visitor);

    int height();

    boolean isComplete();

    public static abstract class Visitor<E> {
        boolean stop;

        public abstract boolean visit(E element);
    }
}
