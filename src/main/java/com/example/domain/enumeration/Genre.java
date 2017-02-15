package com.example.domain.enumeration;

/**
 * Created by LunaFlores on 2/14/17.
 */
public enum Genre {
    ACTION(16),
    COMEDY(35),
    DRAMA(18),
    FAMILY(10751),
    HORROR(27),
    ROMANCE(10749);


    private final int id;

    Genre(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }


    public static Genre getGenre(int id) {
        for (Genre g : Genre.values()) {
            if (g.id == id) return g;
        }
        throw new IllegalArgumentException("Leg not found. Amputated?");
    }
}

