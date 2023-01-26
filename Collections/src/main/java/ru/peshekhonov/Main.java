package ru.peshekhonov;

import ru.peshekhonov.list.array.ArrayList;
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

        ArrayList<Float> floatArrayList = new ArrayList<>();
        System.out.println(floatArrayList);

        ArrayList<String> stringArrayList = new ArrayList<>(2);
        System.out.println(stringArrayList);

        stringArrayList.add("First");
        stringArrayList.add("Second");
        System.out.println(stringArrayList);

        stringArrayList.add(2, "Third");
        System.out.println(stringArrayList);
        stringArrayList.add("Fourth");
        System.out.println(stringArrayList);
        stringArrayList.add("Fifth");
        System.out.println(stringArrayList);

        floatArrayList.add(2f);
        floatArrayList.add(1, 3f);
        floatArrayList.add(4f);
        floatArrayList.add(0, 1f);
        System.out.println(floatArrayList);

        System.out.println(floatArrayList.get(0));
        System.out.println(floatArrayList.get(1));
        System.out.println(floatArrayList.get(2));
        System.out.println(floatArrayList.get(3));

        System.out.println(floatArrayList.remove(5f));
        System.out.println(floatArrayList);

        System.out.println(floatArrayList.remove(3f));
        System.out.println(floatArrayList);

        System.out.println(floatArrayList.remove(1));
        System.out.println(floatArrayList);

        System.out.println(floatArrayList.remove(0));
        System.out.println(floatArrayList);

        for (float value = 5f; value < 20f; value++) {
            floatArrayList.add(value);
            System.out.println(floatArrayList);
        }

        ArrayList arrayList = new ArrayList<>();
        System.out.println(arrayList);
        for (int i = -5; i < 6; i++) {
            arrayList.add(i);
        }
        System.out.println(arrayList);

        arrayList.add(0, -6);
        System.out.println(arrayList);

        System.out.println(arrayList.remove(4));
        System.out.println(arrayList);
        System.out.println(arrayList.remove(Integer.valueOf(4)));
        System.out.println(arrayList);
        System.out.println(arrayList.get(4));

        System.out.println(stringArrayList);
        System.out.println(floatArrayList);
    }
}
