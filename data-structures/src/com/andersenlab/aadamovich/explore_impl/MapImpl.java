package com.andersenlab.aadamovich.explore_impl;

import com.andersenlab.aadamovich.DataStructureUsage;

import java.util.HashMap;

public class MapImpl implements DataStructureUsage {

    @Override
    public void showDataStructureFeatures(String[] array) {
        mapUsage(array);
    }

    private void mapUsage(String[] array) {
        HashMap<String, Integer> sample = new HashMap<>();
        System.out.println("АССОЦИАТИВНЫЙ МАССИВ (Map -> HashMap):");
        System.out.println("Добавление (put) и получение (get) элементов -----");
        for (int i = 0; i < array.length; i++) {
            String currentElement = array[i];
            sample.put(currentElement, i + 1);
            System.out.printf("По ключу %s добавлен элемент %s%n", currentElement, sample.get(currentElement));
        }
        System.out.println("Порядок элементов в HashMap случайный -----");
        sample.forEach((key, value) -> System.out.printf("Ключ %s -> Значение %s%n", key, value));
        System.out.println("__________________");
    }
}
