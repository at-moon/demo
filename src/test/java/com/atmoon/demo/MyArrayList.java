package com.atmoon.demo;

import java.util.Arrays;

/**
 * @author : zy
 */
public class MyArrayList {

    private int size;

    private Object[] elementData;

    public MyArrayList() {
        this.elementData = new Object[]{};
    }

    public boolean add(Object o) {
        if (elementData.length == 0) {
            elementData = new Object[10];
        } else if (elementData.length == size) {
            elementData = Arrays.copyOf(elementData, size + (size >> 1));
        }
        elementData[size++] = o;
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index) {
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[--size] = null;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

}
