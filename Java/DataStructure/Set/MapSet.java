package Java.DataStructure.Set;

import Java.DataStructure.Map.IMap;
import Java.DataStructure.Map.TreeMap;

/**
 * @author: MerelyThis
 * @since: 2023/4/27 周四
 * @description: 通过Map实现，底层是红黑树
 */
public class MapSet<E> implements ISet<E> {

    private IMap<E, Object> map = new TreeMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void claer() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element, null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        map.traversal(new IMap.Visitor<E, Object>() {
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
