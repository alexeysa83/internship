package com.andersenlab.aadamovich.explore_impl;

import com.andersenlab.aadamovich.DataStructureUsage;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

public class QueueImpl implements DataStructureUsage {

    @Override
    public void showDataStructureFeatures(String[] array) {
        classicQueueUsage(array);
        priorityQueueUsage(array);
    }

    private void classicQueueUsage(String[] array) {
        ArrayDeque<String> sample = new ArrayDeque<>();
        System.out.println("КЛАССИЧЕСКАЯ ОЧЕРЕДЬ (Queue->Deque):");
        System.out.println("Добавление (add/offer) и получение (element/peek) элементов -----");
        for (int i = 0; i < array.length; i++) {
            sample.offer(array[i]);
            System.out.printf("Стал в очереди = №%d Значение = %s Головной элемент = %s%n",
                    i + 1, array[i], sample.peek());
        }
        System.out.println("Удаление (remove/poll) элементов -----");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("Вышел из очереди = №%d Значение = %s%n", i + 1, sample.poll());
        }
        System.out.println("__________________");
    }

    private void priorityQueueUsage(String[] array) {
        PriorityQueue<String> sample = new PriorityQueue<>(Comparator.reverseOrder());
        System.out.println("ОЧЕРЕДЬ С ПРИОРИТЕТОМ (PriorityQueue) / Reversed natural order:");
        System.out.println("Добавление и получение элементов -----");
        for (String s : array) {
            sample.offer(s);
            System.out.printf("Элемент = %s добавлен Головной элемент = %s%n",
                    s, sample.peek());
        }
        System.out.println("Удаление (remove/poll) элементов -----");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("Вышел из очереди = №%d Значение = %s%n", i + 1, sample.poll());
        }
        System.out.println("__________________");
    }
}
