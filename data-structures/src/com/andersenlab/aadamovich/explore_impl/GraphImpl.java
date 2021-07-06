package com.andersenlab.aadamovich.explore_impl;

import com.andersenlab.aadamovich.DataStructureUsage;
import com.andersenlab.aadamovich.MyGraph;

import java.util.Arrays;
import java.util.List;

public class GraphImpl implements DataStructureUsage {

    @Override
    public void showDataStructureFeatures(String[] array) {
        graphUsage(array);
    }

    public void printGraphVisual(String[] array) {
        System.out.println(array[0] + "-------" + array[1] + "------");
        System.out.println("  |        |      |");
        System.out.println("  |        |    " + array[2]);
        System.out.println("  |        |      |");
        System.out.println(array[3] + "------" + array[4] + "-----");
    }

    private void graphUsage(String[] array) {
        System.out.println("ПОПЫТКА РЕАЛИЗАЦИИ ГРАФА:");
        printGraphVisual(array);

        MyGraph sample = new MyGraph();
        Arrays.stream(array).forEach(sample::addVertex);
        sample.addEdge(array[0], array[1]);
        sample.addEdge(array[0], array[3]);
        sample.addEdge(array[1], array[2]);
        sample.addEdge(array[1], array[4]);
        sample.addEdge(array[2], array[4]);
        sample.addEdge(array[3], array[4]);

        System.out.println("Список смежных вершин -----");
        sample.printGraphAdjacencyList();

        System.out.println("Обход в глубину -----");
        String root = array[0];
        List<MyGraph.Vertex> verticesPath = sample.inDepthBypass(root);
        String delimiter = "";
        System.out.print("Обход из вершины: " + root + "\nПуть: ");
        for (MyGraph.Vertex vertex : verticesPath) {
            System.out.printf(delimiter + "%s", vertex);
            delimiter = " -> ";
        }
        System.out.println("\n__________________");
    }
}