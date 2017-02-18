package com.example.domain.enumeration;

/**
 * Created by LunaFlores on 2/14/17.
 */
public enum Genre {
    ACTION(28,"Action"),
    COMEDY(35,"Comedy"),
    DRAMA(18, "Drama"),
    FAMILY(10751, "Family"),
    HORROR(27, "Horror"),
    ROMANCE(10749,"Romance");


    private final int id;
    private final String name;


    Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public static Genre getGenre(int id) {
        for (Genre g : Genre.values()) {
            if (g.id == id) return g;
        }
        throw new IllegalArgumentException("Leg not found. Amputated?");
    }
}

