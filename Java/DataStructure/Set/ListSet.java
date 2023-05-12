package Java.DataStructure.Set;

import Java.DataStructure.List.Interface.IList;
import Java.DataStructure.List.LinkedList.DoublyLinkedList;

/**
 * @author: MerelyThis
 * @since: 2023/4/27 周四
 * @description:
 */
public class ListSet<E> implements ISet<E> {

    private DoublyLinkedList<E> list = new DoublyLinkedList<E>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void claer() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        int index = list.indexOf(element);
        if (index == IList.ELEMENT_NOT_FOUND) { // 没有该元素
            list.add(element); // 没有就添加
        } else {
            list.set(index, element); // 已经有就替换
        }
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != IList.ELEMENT_NOT_FOUND) {
            list.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            visitor.visit(list.get(i));
        }
    }
}
