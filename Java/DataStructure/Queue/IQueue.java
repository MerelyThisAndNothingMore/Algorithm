package Java.DataStructure.Queue;

/**
 * @author: MerelyThis
 * @since: 2023/4/24 周一
 * @description:
 */
public interface IQueue<E> {

    void enQueue(E element);

    E deQueue();

    int size();

    void clear();

    E top();

    boolean isEmpty();
}
