package Java.DataStructure.Set;

/**
 * @author: MerelyThis
 * @since: 2023/4/27 周四
 * @description:
 */
public interface ISet<E> {

    int size();

    boolean isEmpty();

    void claer();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E> {
        boolean stop;

        public abstract boolean visit(E element);
    }
}
