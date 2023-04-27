package Java.DataStructure.Queue;

import Java.DataStructure.List.Interface.IList;
import Java.DataStructure.List.LinkedList.DoublyLinkedList;

/**
 * @author: MerelyThis
 * @since: 2023/4/24 周一
 * @description: LinkedList implementation
 */
public class Queue<E> implements IQueue<E> {

    private IList<E> list = new DoublyLinkedList<>();

    @Override
    public void enQueue(E element) {
        list.add(element);
    }

    @Override
    public E deQueue() {
        return list.remove(0);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public E top() {
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
