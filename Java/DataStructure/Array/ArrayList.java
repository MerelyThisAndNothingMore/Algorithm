package Java.DataStructure.Array;

public class ArrayList<E> implements IArrayList<E> {

    private static final int DEFALULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    private int size = 0; // size of the array

    private E[] elements; // array elements

    public ArrayList(int capacity) {
        capacity = (capacity < DEFALULT_CAPACITY) ? DEFALULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(DEFALULT_CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) { // 对 null 进行处理
            for (int i = 0; i < size; i++) {
                if (elements[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element))
                    return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity)
            return;
        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i]; // 拷贝原数组元素到新数组
        }
        elements = newElements;
        System.out.println("size=" + oldCapacity + ", 扩容到了" + newCapacity);
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index); // 检查下标越界
        ensureCapacity(size + 1); // 确保容量够大

        for (int i = size - 1; i > index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element; // 复制
        size++;
    }

    @Override
    public void add(E element) {
        add(size, element);
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
    public E remove(int index) {
        rangeCheck(index);

        E old = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null; // 删除元素后, 将最后一位设置为null
        return old;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;

    }

    // 下标越界抛出的异常
    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    // 检查下标越界(不可访问或删除size位置)
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    // 检查add()的下标越界(可以在size位置添加元素)
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        // 打印形式为: size=5, [99, 88, 77, 66, 55]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (0 != i)
                string.append(", ");
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

}
