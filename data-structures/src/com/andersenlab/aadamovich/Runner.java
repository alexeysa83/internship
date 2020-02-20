package com.andersenlab.aadamovich;

import com.andersenlab.aadamovich.explore_impl.*;

public class Runner {

    private static String[] testArray = {"one", "two", "three", "four", "five"};

    public static void main(String[] args) {
        DataStructureUsage usage = new ArrayImpl();
        usage.showDataStructureFeatures(testArray);

        usage = new QueueImpl();
        usage.showDataStructureFeatures(testArray);

        usage = new StackImpl();
        usage.showDataStructureFeatures(testArray);

        usage = new MapImpl();
        usage.showDataStructureFeatures(testArray);

        usage = new TreeImpl();
        usage.showDataStructureFeatures(testArray);

        usage = new GraphImpl();
        usage.showDataStructureFeatures(testArray);
    }
}
