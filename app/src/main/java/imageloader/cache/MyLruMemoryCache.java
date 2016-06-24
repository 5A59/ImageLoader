package imageloader.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zy on 16-5-19.
 *
 * 看了系统的LruCache源码之后 自己写一个
 *
 */
public class MyLruMemoryCache<K, V> {

    private LinkedHashMap<K, V> linkedHashMap;
    private int size;
    private int maxSize;

    public MyLruMemoryCache(int maxSize) {
        this.maxSize = maxSize;
        size = 0;
        linkedHashMap = new LinkedHashMap<>(0, 0.75f, true);
    }

    public V get(K key) {
        if (key == null){
            throw new NullPointerException("key is null");
        }
        V value = null;

        synchronized (this){
            value = linkedHashMap.get(key);
        }

        return value;
    }

    public V put(K key, V value) {
        if (key == null || value == null){
            throw new NullPointerException("key is null or value is null");
        }
        V pre = null;

        synchronized (this){
            pre = linkedHashMap.put(key, value);
            size += safeSizeOf(key, value);
            if (pre != null){
                size -= safeSizeOf(key, pre);
            }
        }
        if (pre != null){
            entryRemoved(key, pre);
        }

        trimToSize(maxSize);

        return pre;
    }

    public void trimToSize(int maxSize) {
        while (true){
            synchronized (this){
                if (size < 0 || linkedHashMap.isEmpty() && size > 0){
                    throw new IllegalStateException("size is not match to map");
                }

                if (size <= maxSize){
                    break;
                }
                LinkedHashMap.Entry<K, V> entry = null;
                Iterator<Map.Entry<K, V>> iterator = linkedHashMap.entrySet().iterator();
                while (iterator.hasNext()){
                    entry = iterator.next();
                    break;
                }

                if (entry == null){
                    break;
                }
                size -= safeSizeOf(entry.getKey(), entry.getValue());
                entryRemoved(entry.getKey(), entry.getValue());
            }
        }
    }

    private int safeSizeOf(K key, V value) {
        int res = sizeOf(key, value);
        if (res < 0){
            throw new IllegalStateException("size < 0");
        }
        return res;
    }

    protected void entryRemoved(K key, V value){

    }

    protected V create(K key) {
        return null;
    }

    protected int sizeOf(K key, V value) {
        return 0;
    }
}
