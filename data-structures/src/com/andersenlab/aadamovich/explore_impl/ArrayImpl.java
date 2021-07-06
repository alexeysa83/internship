package com.andersenlab.aadamovich.explore_impl;

import com.andersenlab.aadamovich.DataStructureUsage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ArrayImpl implements DataStructureUsage {

    @Override
    public void showDataStructureFeatures(String[] array) {
        arrayStaticUsage(array);
        arrayDynamicUsage(array);
    }

    private void arrayStaticUsage(String[] array) {
        String[] sample = new String[array.length];
        System.arraycopy(array, 0, sample, 0, array.length);
        System.out.printf("СТАТИЧЕСКИЙ МАССИВ: %s%n", Arrays.toString(sample));
        System.out.println("Доступ по индексу (array_name[index]) -----");
        System.out.printf("Значение по индексу %d = %s%n", 2, sample[2]);
        System.out.println("Замена/вставка элемента по индексу (array_name[index]=value) ----");
        sample[2] = "new value";
        System.out.printf("После замены элемента по индексу 2: %s%n", Arrays.toString(sample));
        Arrays.sort(sample, Comparator.comparing(String::length));
        System.out.println("Сортировка и поиск в методах класса Arrays ----");
        System.out.printf("После сортировки по размеру символьной строки: %s%n", Arrays.toString(sample));
        System.out.println("__________________");
    }

    private void arrayDynamicUsage(String[] array) {
        ArrayList<String> sample = new ArrayList<>(Arrays.asList(array));
        System.out.println("ДИНАМИЧЕСКИЙ МАССИВ (ArrayList): " + sample);
        System.out.println("Вставка/удаление приводит к изменению длины массива и сдвигу элементов -----");
        sample.add("six");
        sample.add(2, "two and half");
        System.out.printf("После добавления элементов: sample.add(\"six\"); и sample.add(2, \"two and half\");%n%s%n", sample);
        sample.remove(2);
        System.out.printf("После удаления элементов: sample.remove(2););%n%s%n", sample);
        System.out.println("__________________");
    }
}
