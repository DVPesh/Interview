package ru.peshekhonov.list.linked;

import java.util.NoSuchElementException;

public class LinkedList<T> {

    private int size;
    private final Entry<T> header = new Entry<>();

    {
        header.previous = header;
        header.next = header;
    }

    private static class Entry<T> {
        T element;
        Entry<T> next;
        Entry<T> previous;

        Entry() {
        }

        Entry(T element, Entry<T> next, Entry<T> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    public boolean add(T element) {
        Entry<T> entry = new Entry<>(element, header, header.previous);
        entry.previous.next = entry;
        entry.next.previous = entry;
        size++;
        return true;
    }

    public void add(int index, T element) {
        Entry<T> entry = size == index ? header : getEntry(index);
        Entry<T> newEntry = new Entry<>(element, entry, entry.previous);
        newEntry.previous.next = newEntry;
        entry.previous = newEntry;
        size++;
    }

    public T get(int index) {
        return getEntry(index).element;
    }

    public T remove() {
        Entry<T> entry = header.next;
        if (entry == header) {
            throw new NoSuchElementException("Empty list");
        }
        T result = entry.element;
        removeEntry(entry);
        return result;
    }

    public T remove(int index) {
        Entry<T> entry = getEntry(index);
        T result = entry.element;
        removeEntry(entry);
        return result;
    }

    public boolean remove(T element) {
        for (Entry<T> entry = header.next; entry != header; entry = entry.next) {
            if (entry.element.equals(element)) {
                removeEntry(entry);
                return true;
            }
        }
        return false;
    }

    private void removeEntry(Entry<T> entry) {
        entry.previous.next = entry.next;
        entry.next.previous = entry.previous;
        entry.next = entry.previous = null;
        entry.element = null;
        size--;
    }

    private Entry<T> getEntry(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Entry<T> entry = header;

        if (index < (size >> 1)) {
            for (int i = 0; i <= index; i++)
                entry = entry.next;
        } else {
            for (int i = size; i > index; i--)
                entry = entry.previous;
        }

        return entry;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("LinkedList size=" + size + System.lineSeparator());
        for (Entry<T> entry = header.next; entry != header; entry = entry.next) {
            builder.append(entry.element.toString()).append(System.lineSeparator());
        }
        return builder.toString();
    }
}
