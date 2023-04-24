package Java.DataStructure.Array;

public interface IArrayList<E> {

    public int size();

    public boolean isEmpty();

    public int indexOf(E element);

    public boolean contains(E element);

    public void add(E element);

    public void add(int index, E element);

    public E get(int index);

    public E set(int index, E element);

    public E remove(int index);

    public void clear();

    public void ensureCapacity(int capacity);
}
