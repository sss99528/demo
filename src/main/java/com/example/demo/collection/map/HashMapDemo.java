package com.example.demo.collection.map;

import java.util.HashMap;

public class HashMapDemo {

    public static void main(String[] args) {

        HashMap<Object, Object> map = new HashMap<>(13);
        for (int i = 0; i < 100000; i++) {
            map.put(1,i+Math.random());
        }
    }
}
