package Java.DataStructure.Stack;

import Java.DataStructure.List.ArrayList.ArrayList;
import Java.DataStructure.List.Interface.IList;

/**
 * @author: MerelyThis
 * @since: 2023/4/24 周一
 * @description: implement Stack by List.
 */
public class Stack<E> implements IStack<E> {

    private IList<E> list = new ArrayList<E>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E element) {
        list.add(element);
    }

    @Override
    public E pop() {
        return list.remove(list.size() - 1);
    }

    @Override
    public E top() {
        return list.remove(list.size() - 1);
    }
}
