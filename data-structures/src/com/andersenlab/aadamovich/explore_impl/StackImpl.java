package com.andersenlab.aadamovich.explore_impl;

import com.andersenlab.aadamovich.DataStructureUsage;

import java.util.LinkedList;

public class StackImpl implements DataStructureUsage {

    @Override
    public void showDataStructureFeatures(String[] array) {
        stackUsage(array);
    }

    private void stackUsage (String [] array) {
        LinkedList<String> sample = new LinkedList<>();
        System.out.println("СТЕК (Deque or Stack):");
        System.out.println("Добавление (push/addFirst/offerFirst) и получение (peek/getFirst/peekFirst) элементов -----");
        for (int i = 0; i < array.length; i++) {
            sample.push(array[i]);
            System.out.printf("Добавлен в стек = №%d Значение = %s Головной элемент = %s%n",
                    i + 1, array[i], sample.peek());
        }
        System.out.println("Удаление (pop/removeFirst/pollFirst) элементов -----");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("Вышел из стека = №%d Значение = %s%n", i + 1, sample.pop());
        }
        System.out.println("__________________");

    }
}
