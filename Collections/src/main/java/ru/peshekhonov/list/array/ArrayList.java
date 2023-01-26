package ru.peshekhonov.list.array;

import java.util.Arrays;

public class ArrayList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        elementData = (T[]) new Object[capacity];
    }

    public boolean add(T element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
        return true;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elementData[index];
    }

    public T remove(int index) {
        T result = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return result;
    }

    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                elementData[--size] = null;
                return true;
            }
        }
        return false;
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity < minCapacity) {
            T[] oldData = elementData;
            elementData = (T[]) new Object[oldCapacity + (oldCapacity < 2 ? 1 : oldCapacity >> 1)];
            System.arraycopy(oldData, 0, elementData, 0, size);
        }
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }
}
