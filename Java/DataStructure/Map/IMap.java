package Java.DataStructure.Map;

/**
 * @author: MerelyThis
 * @since: 2023/4/27 周四
 * @description:
 */
public interface IMap<K, V> {
    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    void traversal(Visitor<K, V> visitor);

    public static abstract class Visitor<K, V> {
        boolean stop;

        public abstract boolean visit(K key, V value);
    }
}
