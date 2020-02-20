package com.andersenlab.aadamovich;

import java.util.*;

public class MyGraph {

    private final HashMap<Vertex, List<Vertex>> vertices;

    public MyGraph() {
        vertices = new HashMap<>();
    }

    public void addVertex(String name) {
        Vertex vertex = new Vertex(name);
        if (!vertices.containsKey(vertex)) {
            vertices.putIfAbsent(vertex, new ArrayList<>());
        }
    }

    public void deleteVertex(String name) {
        Vertex vertexToDelete = new Vertex(name);
        vertices.values().forEach(e -> e.remove(vertexToDelete));
        vertices.remove(vertexToDelete);
    }

    public void addEdge(String name1, String name2) {
        Vertex vertex1 = new Vertex(name1);
        Vertex vertex2 = new Vertex(name2);
        vertices.get(vertex1).add(vertex2);
        vertices.get(vertex2).add(vertex1);
    }

    public void deleteEdge(String name1, String name2) {
        Vertex vertex1 = new Vertex(name1);
        Vertex vertex2 = new Vertex(name2);
        List<Vertex> neighborVertices1 = vertices.get(vertex1);
        neighborVertices1.remove(vertex2);
        List<Vertex> neighborVertices2 = this.vertices.get(vertex2);
        neighborVertices2.remove(vertex1);
    }

    public List<Vertex> inDepthBypass(String rootName) {
        final Vertex root = new Vertex(rootName);
        List<Vertex> visited = new ArrayList<>();
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Vertex nextVertex = stack.pop();
            if (!visited.contains(nextVertex)) {
                visited.add(nextVertex);
                vertices.get(nextVertex).forEach(stack::push);
            }
        }
        return visited;
    }

    public void printGraphAdjacencyList() {
        this.vertices.forEach((k, v) -> System.out.printf("Узел %s --> Соседи: %s%n", k, v));
    }

    public static class Vertex {
        private String name;

        public Vertex(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return name.equals(vertex.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
