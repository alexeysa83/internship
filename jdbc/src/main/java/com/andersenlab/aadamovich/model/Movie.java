package com.andersenlab.aadamovich.model;

import java.util.Objects;

public class Movie {
    private Integer id;
    private String name;
    private String genre;
    private boolean isCool;

    public Movie() {
    }

    public Movie(String name, String genre, boolean isCool) {
        this.name = name;
        this.genre = genre;
        this.isCool = isCool;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isCool() {
        return isCool;
    }

    public void setCool(boolean cool) {
        isCool = cool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return isCool == movie.isCool &&
                Objects.equals(id, movie.id) &&
                Objects.equals(name, movie.name) &&
                Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, isCool);
    }
}
