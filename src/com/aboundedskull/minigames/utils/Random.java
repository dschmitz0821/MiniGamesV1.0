package com.aboundedskull.minigames.utils;

import java.util.Collection;

public class Random {

    public static <E>E getRandomElement(Collection<E> collection){
        int num = (int) (Math.random() * collection.size());
        for (E e : collection)
            if (num-- < 0)
                return e;

        return collection.iterator().next();
    }

    public static <E>E getRandomElement(E[] array){
        if (array.length == 0)
            return null;

        int num = (int) (Math.random() * array.length);
        for (E e : array)
            if (num-- < 0)
                return e;

        return array[0];
    }
}
