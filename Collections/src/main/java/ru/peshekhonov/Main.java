package ru.peshekhonov;

import ru.peshekhonov.list.linked.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.add("First");
        stringLinkedList.add("Second");

        LinkedList<Double> doubleLinkedList = new LinkedList<>();
        doubleLinkedList.add(1.0);
        doubleLinkedList.add(1, 2.0);
        doubleLinkedList.add(3.0);
        doubleLinkedList.add(4.0);
        System.out.println(doubleLinkedList);
        System.out.println(stringLinkedList);

        doubleLinkedList.add(0, 0.0);
        doubleLinkedList.add(3, 2.5);
        System.out.println(doubleLinkedList);

        System.out.println(doubleLinkedList.get(4));

        System.out.println(doubleLinkedList.remove());
        System.out.println(doubleLinkedList);

        System.out.println(doubleLinkedList.remove(2));
        System.out.println(doubleLinkedList);

        System.out.println(doubleLinkedList.remove(-1.0));
        System.out.println(doubleLinkedList);

        System.out.println(doubleLinkedList.remove(2.0));
        System.out.println(doubleLinkedList);
    }
}
