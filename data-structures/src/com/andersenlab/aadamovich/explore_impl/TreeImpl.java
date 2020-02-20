package com.andersenlab.aadamovich.explore_impl;

import com.andersenlab.aadamovich.DataStructureUsage;

import java.util.TreeMap;

public class TreeImpl implements DataStructureUsage {

    @Override
    public void showDataStructureFeatures(String[] array) {
        treeUsage(array);
    }

    private void treeUsage(String[] array) {
        TreeMap<String, Integer> sample = new TreeMap<>();
        System.out.println("АССОЦИАТИВНЫЙ МАССИВ НА ОСНОВЕ ДВОИЧНОГО ДЕРЕВА (TreeMap):");
        System.out.println("Операции с элементами get/put/remove за время log(n) -----");
        for (int i = 0; i < array.length; i++) {
            String currentElement = array[i];
            sample.put(currentElement, i + 1);
            System.out.printf("По ключу %s добавлен элемент %s%n", currentElement, sample.get(currentElement));
        }
        System.out.println("Элементы отсортированы в порядке, определенном в компараторе -----");
        System.out.printf("Текущий компаратор = %s%n", sample.comparator());
        sample.forEach((key, value) -> System.out.printf("Ключ %s -> Значение %s%n", key, value));
        System.out.println("__________________");
    }
}
