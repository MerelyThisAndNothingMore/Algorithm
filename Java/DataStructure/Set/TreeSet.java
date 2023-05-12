package Java.DataStructure.Set;

import Java.DataStructure.BinaryTree.BinaryTree;
import Java.DataStructure.BinaryTree.bbst.RBTree;

/**
 * @author: MerelyThis
 * @since: 2023/4/27 周四
 * @description:
 */
public class TreeSet<E> implements ISet<E> {
    private RBTree<E> tree = new RBTree<>();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void claer() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element); // 红黑树自带去重
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inorder(new BinaryTree.Visitor<E>() {
            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
