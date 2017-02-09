package com.example.api;

/**
 * Created by LunaFlores on 2/8/17.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("genre")
public class Genre {
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private int id;


    public int getId() {
        return id;
    }


    public void setId(int id) {


        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
            "name='" + name + '\'' +
            ", id=" + id +
            '}';
    }
}
