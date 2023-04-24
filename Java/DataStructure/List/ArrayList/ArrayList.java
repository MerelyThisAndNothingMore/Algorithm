package Java.DataStructure.List.ArrayList;

import Java.DataStructure.List.Interface.BaseList;

/**
 * @author: MerelyThis
 * @since: 2023/4/24 周一
 * @description:
 */
public class ArrayList<E> extends BaseList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] elements;

    public ArrayList(int capacity) { // 容量小于10一律扩充为10
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }


    @Override
    public void clear() {
        // 使用泛型数组后要注意内存管理
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {

        rangeCheck(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {

        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index); // 检查下标越界
        ensureCapacity(size + 1); // 确保容量够大

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null; // 删除元素后,将最后一位设置为null
        tryTrim();
        return old;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) { // 对 null 进行处理
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }


    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i]; // 拷贝原数组元素到新数组
        }
        elements = newElements;
        System.out.println("size=" + oldCapacity + ", 扩容到了" + newCapacity);
    }

    private void tryTrim(){
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity >> 1; // 缩容为一半
        if(size > newCapacity || newCapacity <= DEFAULT_CAPACITY) return;
        // 剩余空间很多
        E[] newElements = (E[]) new Object[newCapacity];
        for(int i = 0; i < size; i++){
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println("size=" + size + ", 从" + oldCapacity + "缩容为" + newCapacity);
    }
}
