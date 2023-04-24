package Java.DataStructure.Stack;

/**
 * @author: MerelyThis
 * @since: 2023/4/24 周一
 * @description:
 */
public interface IStack<E> {

    void clear();

    boolean isEmpty();

    void push(E element);

    public E pop();

    public E top();
}
