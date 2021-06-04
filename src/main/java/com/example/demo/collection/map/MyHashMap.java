package com.example.demo.collection.map;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Serializable {

    private static final long serialVersionUID = 962498820763181265L;

    // 初始化大小
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    // 最大值
    static final int MAXIMUM_CAPACITY = 1073741824;
    // 默认负载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75F;
    //  链表变树临界值
    static final int TREEIFY_THRESHOLD = 8;
    // 树边链表临界值
    static final int UNTREEIFY_THRESHOLD = 6;
    // 树最大值
    static final int MIN_TREEIFY_CAPACITY = 64;
//    transient HashMap.Node<K, V>[] table;
    transient Set<Map.Entry<K, V>> entrySet;
    transient int size;
    transient int modCount;
    int threshold;
//    final float loadFactor;


    static final int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }

}
