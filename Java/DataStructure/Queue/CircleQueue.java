package Java.DataStructure.Queue;

/**
 * @author: MerelyThis
 * @since: 2023/4/25 周二
 * @description: ArrayList implementation
 */
public class CircleQueue<E> implements IQueue<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int front;
    private int size;
    private E elements[];


    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }


    @Override
    public void enQueue(E element) {
        ensureCapacity(size + 1);

        elements[index(size)] = element;
        size++;
    }

    @Override
    public E deQueue() {
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }

    @Override
    public E top() {
        return elements[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取第i + 1个队列元素的真实数组坐标
     *
     * @param i 该元素和front的距离
     * @return
     */
    private int index(int i) {
        return (front + i) % elements.length;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity)
            return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) { // 旧数组中元素移到新数组
            // newElements[i] = elements[(i + front) % elements.length];
            newElements[i] = elements[index(i)];
        }
        System.out.println("从" + oldCapacity + "扩容到" + newCapacity);
        elements = newElements;
        front = 0; // 重置front
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capcacity=").append(elements.length).append(" size=").append(size).append(" front=")
                .append(front).append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}
