package Java.DataStructure.BinaryTree.bst;

import Java.DataStructure.BinaryTree.IBinaryTree;

/**
 * @author: MerelyThis
 * @since: 2023/4/25 周二
 * @description:
 */
public interface IBinarySearchTree<E> extends IBinaryTree<E> {

    void add(E element);

    void remove(E element);

    boolean contains(E element);
}
