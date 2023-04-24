package Java.DataStructure.List.LinkedList;

import Java.DataStructure.List.Interface.BaseList;

/**
 * @author: MerelyThis
 * @since: 2023/4/24 周一
 * @description:
 */
public class SinglyLinkedList<E> extends BaseList<E> {

    private Node<E> head;


    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> old = getNode(index);
        E result = old.element;
        old.element = element;
        return result;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            head = new Node<E>(element, head);
        } else {
            Node<E> pre = getNode(index - 1);
            pre.next = new Node<E>(element, pre.next);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = head;
        if (index == 0) {
            head = node.next;
        } else {
            Node<E> pre = getNode(index - 1);
            node = pre.next;
            pre.next = node.next;
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = head;
            for (int i = 0; i < size; i++) {
                if (node.element == element) return i;
                node = node.next;
            }
        } else {
            Node<E> node = head;
            for (int i = 0; i < size; i++) {
                if (node.element.equals(element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    private Node<E> getNode(int index) {
        rangeCheck(index);
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node<E> {
        E element;
        Node<E> next;


        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("[size=").append(size).append(", ");
        Node<E> node = head;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(node.element);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
